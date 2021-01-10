package com.honglai.org;

import java.util.Objects;

public class TodoItem {
    private Integer id;
    private String name;
    private ItemStatus status;

    private TodoItem(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.status = ItemStatus.PENDING;
    }

    public static TodoItem newItem(Integer id, String someItem) {
        return new TodoItem(id, someItem);
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

    public String print() {
        String statusString = isDone() ? String.format("[%s] ", this.status.name()) : "";
        return String.format("%s. %s<%s>", this.id, statusString, this.name);
    }

    public int sortOrder() {
        return status.ordinal();
    }
}
