package com.honglai.org;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TodoApp {
    private List<TodoItem> itemList;
    private final AtomicInteger idCounter;
    private PersistManager persistManager;

    public TodoApp() {
        this.itemList = new ArrayList<>();
        this.idCounter = new AtomicInteger(1);
        this.persistManager = new PersistManager();
    }

    public TodoItem addItem(String someItem) {
        TodoItem newItem = TodoItem.newItem(idCounter.getAndIncrement(), someItem);
        this.itemList.add(newItem);
        persistManager.insert(newItem);
        return newItem;
    }

    public void finishItem(Integer id) {
        Optional<TodoItem> todoItem = this.itemList.stream()
            .filter(item -> Objects.equals(item.getId(), id))
            .findFirst();
        if (!todoItem.isPresent()) {
            throw new ItemNotFoundException(id);
        }
        todoItem.ifPresent(item -> {
            item.finish();
            persistManager.update(id, item);
        });
    }

    public List<String> printAllItems() {
        return this.itemList.stream()
            .sorted(Comparator.comparing(TodoItem::sortOrder))
            .map(TodoItem::print)
            .collect(Collectors.toList());
    }

    public List<String> printPendingItems() {
        return this.itemList.stream()
            .filter(item -> !item.isDone())
            .map(TodoItem::print)
            .collect(Collectors.toList());
    }

    public String printSummary() {
        return String.format("Total: %s%s", getTotalItemSummary(), getDoneItemSummary());
    }

    private String getDoneItemSummary() {
        long doneItemCount = this.itemList.stream()
            .filter(TodoItem::isDone).count();
        return doneItemCount == 0L ? "" : String.format(", %s %s done", doneItemCount, printUnit(doneItemCount));
    }

    private String getTotalItemSummary() {
        long totalItemCount = this.itemList.size();
        return String.format("%s %s", totalItemCount, printUnit(totalItemCount));
    }

    private String printUnit(long count) {
        return count > 1 ? "items" : "item";
    }
}
