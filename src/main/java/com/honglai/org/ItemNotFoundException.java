package com.honglai.org;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Integer id) {
        super(String.format("Item with id [%s] is not found", id));
    }
}
