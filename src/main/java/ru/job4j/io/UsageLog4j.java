package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Vyacheslav";
        char firstNameWord = 'V';
        short age = 28;
        int id = 1111111111;
        byte groupId = 3;
        float weight = 75.5F;
        double height = 163.3;
        boolean married = true;
        long number = 82222833888189L;

        LOG.debug("Name: {}. First word: {}. Age: {}. Id: {}. Group id: {}. Weight: {}. Height: {}. Married: {}. Number: {}",
                name, firstNameWord, age, id, groupId, weight, height, married, number);
    }
}