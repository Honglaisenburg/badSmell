package com.honglai.org;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class TodoApp {
    private List<TodoItem> itemList;

    public TodoApp() {
        this.itemList = new ArrayList<>();
    }

    public TodoItem addItem(String someItem) {
        TodoItem newItem = TodoItem.newItem(someItem);
        this.itemList.add(newItem);
        return newItem;
    }

    public void finishItem(Integer id) {
        Optional<TodoItem> todoItem = this.itemList.stream()
            .filter(item -> Objects.equals(item.getId(), id))
            .findFirst();
        todoItem.ifPresent(TodoItem::finish);
        if (!todoItem.isPresent()) {
            throw new ItemNotFoundException(id);
        }
    }

    public List<TodoItem> getAllItems() {
        return this.itemList;
    }

    public List<TodoItem> getPendingItems() {
        return this.itemList.stream()
            .filter(item -> !item.isDone())
            .collect(Collectors.toList());
    }
}
