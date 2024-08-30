package ru.job4j;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CrptApi {

    private final HttpClient httpClient;
    private final Gson gson;
    private final int requestLimit;
    private final ScheduledExecutorService scheduler;
    private final BlockingQueue<Instant> requestQueue;
    private final long intervalMillis;

    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new GsonBuilder().create();
        this.requestLimit = requestLimit;
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.requestQueue = new LinkedBlockingQueue<>(requestLimit);
        this.intervalMillis = timeUnit.toMillis(1); // Продолжительность интервала в миллисекундах

        scheduler.scheduleAtFixedRate(() -> {
            Instant now = Instant.now();
            requestQueue.removeIf(timestamp -> now.toEpochMilli() - timestamp.toEpochMilli() > intervalMillis);
        }, intervalMillis, intervalMillis, TimeUnit.MILLISECONDS);
    }

    public synchronized void createDocument(Document document, String signature) throws Exception {
        if (requestQueue.size() >= requestLimit) {
            Instant firstRequestTime = requestQueue.peek();
            if (firstRequestTime != null) {
                long sleepTime = intervalMillis - (Instant.now().toEpochMilli() - firstRequestTime.toEpochMilli());
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            }
        }

        requestQueue.add(Instant.now());

        String jsonBody = gson.toJson(document);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://ismp.crpt.ru/api/v3/lk/documents/create"))
                .header("Content-Type", "application/json")
                .header("Signature", signature)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }

    public void shutdown() {
        scheduler.shutdown();
    }

    public static void main(String[] args) {
        CrptApi crptApi = new CrptApi(TimeUnit.MINUTES, 5);

        Document document = crptApi.new Document(
                "1234567890",
                "doc123",
                "DRAFT",
                "LP_INTRODUCE_GOODS",
                true,
                "1234567890",
                "1234567890",
                "2024-01-01",
                "MANUFACTURE"
        );

        String signature = "exampleSignature";

        try {
            for (int i = 0; i < 7; i++) { // Попробуем отправить 7 запросов, чтобы проверить ограничение
                System.out.println("Отправка запроса #" + (i + 1));
                crptApi.createDocument(document, signature);
                System.out.println("Запрос #" + (i + 1) + " отправлен.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            crptApi.shutdown();
        }
    }

    public class Document {
        private String participantInn;
        private String docId;
        private String docStatus;
        private String docType;
        private boolean importRequest;
        private String ownerInn;
        private String producerInn;
        private String productionDate;
        private String productionType;

        public Document(String participantInn, String docId, String docStatus, String docType, boolean importRequest,
                        String ownerInn, String producerInn, String productionDate, String productionType) {
            this.participantInn = participantInn;
            this.docId = docId;
            this.docStatus = docStatus;
            this.docType = docType;
            this.importRequest = importRequest;
            this.ownerInn = ownerInn;
            this.producerInn = producerInn;
            this.productionDate = productionDate;
            this.productionType = productionType;
        }
    }
}
