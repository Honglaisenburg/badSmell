package com.honglai.org;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoItem {
    private static AtomicInteger idCounter = new AtomicInteger(1);
    private Integer id;
    private String name;
    private ItemStatus status;

    private TodoItem(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.status = ItemStatus.PENDING;
    }

    public static TodoItem newItem(String someItem) {
        return new TodoItem(idCounter.getAndIncrement(), someItem);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void finish() {
        this.status = ItemStatus.DONE;
    }

    public boolean isDone() {
        return Objects.equals(this.status, ItemStatus.DONE);
    }
}
