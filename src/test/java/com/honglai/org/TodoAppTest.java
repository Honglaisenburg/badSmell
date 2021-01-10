package com.honglai.org;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class TodoAppTest {

    private TodoApp todoApp;

    @Before
    public void setUp() {
        this.todoApp = new TodoApp();
    }

    @Test
    public void should_add_item_successfully() {
        todoApp.addItem("someItem");
        assertThat(todoApp.getAllItems().size(), is(1));
        assertThat(todoApp.getAllItems().get(0).getId(), is(1));
        assertThat(todoApp.getAllItems().get(0).getName(), is("someItem"));
    }

    @Test
    public void should_add_item_continuously() {
        todoApp.addItem("firstItem");
        todoApp.addItem("secondItem");

        assertThat(todoApp.getAllItems().size(), is(2));

        assertThat(todoApp.getAllItems().get(0).getId(), is(1));
        assertThat(todoApp.getAllItems().get(0).getName(), is("firstItem"));

        assertThat(todoApp.getAllItems().get(1).getId(), is(2));
        assertThat(todoApp.getAllItems().get(1).getName(), is("secondItem"));
    }

    @Test
    public void should_finish_todo_item_successfully() {
        TodoItem addedItem = todoApp.addItem("firstItem");
        todoApp.finishItem(addedItem.getId());

        assertThat(todoApp.getAllItems().size(), is(1));
        assertThat(todoApp.getPendingItems().size(), is(0));
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_throw_exception_when_finish_id_is_not_exist() {
        todoApp.addItem("firstItem");
        todoApp.finishItem(100001);
    }
}