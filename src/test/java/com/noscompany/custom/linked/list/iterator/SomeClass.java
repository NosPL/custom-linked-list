package com.noscompany.custom.linked.list.iterator;

public class SomeClass<T> {
    private int invocationCount = 0;

    public void consume(T t) {
        invocationCount++;
    }

    public int getInvocationCount() {
        return invocationCount;
    }
}
