package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    int inputSize;
    int outputSize;

    public T poll() {
        if (inputSize == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (outputSize == 0) {
            while (inputSize != 0) {
                output.push(input.pop());
                inputSize--;
                outputSize++;
            }
        }
        T value = output.pop();
        outputSize--;
        if (outputSize < 0) {
            throw new NoSuchElementException();
        }
        return value;
    }

    public void push(T value) {
        input.push(value);
        inputSize++;
    }
}