package xyz.hardliner.calc.utils;

public class Counter {

    private int counter = 0;

    public int increment() {
        counter += 1;
        return counter;
    }

    public int increment(int times) {
        counter += times;
        return counter;
    }

    public int get() {
        return counter;
    }

}
