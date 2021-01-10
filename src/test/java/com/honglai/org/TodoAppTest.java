package com.honglai.org;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        List<String> todoListContentList = todoApp.printAllItems();
        assertThat(todoListContentList.size(), is(1));
        assertThat(todoListContentList.get(0), is("1. <someItem>"));
    }

    @Test
    public void should_add_item_continuously() {
        todoApp.addItem("firstItem");
        todoApp.addItem("secondItem");

        List<String> todoListContentList = todoApp.printAllItems();

        assertThat(todoListContentList.size(), is(2));
        assertThat(todoListContentList.get(0), is("1. <firstItem>"));
        assertThat(todoListContentList.get(1), is("2. <secondItem>"));
    }

    @Test
    public void should_finish_todo_item_successfully() {
        TodoItem addedItem = todoApp.addItem("firstItem");
        todoApp.finishItem(addedItem.getId());

        assertThat(todoApp.printAllItems().size(), is(1));
        assertThat(todoApp.printPendingItems().size(), is(0));
    }

    @Test
    public void should_finish_todo_task_in_a_normal_list_successfully() {
        todoApp.addItem("firstItem");
        TodoItem secondItem = todoApp.addItem("secondItem");
        todoApp.addItem("thirdItem");
        todoApp.finishItem(secondItem.getId());

        assertThat(todoApp.printAllItems().size(), is(3));
        assertThat(todoApp.printAllItems().get(0), is("1. <firstItem>"));
        assertThat(todoApp.printAllItems().get(1), is("3. <thirdItem>"));
        assertThat(todoApp.printAllItems().get(2), is("2. [DONE] <secondItem>"));

        assertThat(todoApp.printPendingItems().size(), is(2));
        assertThat(todoApp.printPendingItems().get(0), is("1. <firstItem>"));
        assertThat(todoApp.printPendingItems().get(1), is("3. <thirdItem>"));
    }

    @Test
    public void should_print_summary_successfully() {
        todoApp.addItem("firstItem");
        TodoItem secondItem = todoApp.addItem("secondItem");
        todoApp.addItem("thirdItem");
        todoApp.finishItem(secondItem.getId());

        assertThat(todoApp.printSummary(), is("Total: 3 items, 1 item done"));
    }

    @Test
    public void should_print_summary_successfully_when_nothing_is_done() {
        todoApp.addItem("firstItem");
        todoApp.addItem("secondItem");
        todoApp.addItem("thirdItem");

        assertThat(todoApp.printSummary(), is("Total: 3 items"));
    }

    @Test(expected = ItemNotFoundException.class)
    public void should_throw_exception_when_finish_id_is_not_exist() {
        todoApp.addItem("firstItem");
        todoApp.finishItem(100001);
    }
}