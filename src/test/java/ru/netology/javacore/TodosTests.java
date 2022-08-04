package ru.netology.javacore;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

public class TodosTests {
    Todos todos = new Todos();

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test completed");
    }

    @Test
    public void addTaskTest() {
        String task = "A";
        ArrayList<String> list = new ArrayList<>();
        list.add(task);

        todos.addTask(task);

        Assertions.assertTrue(list.equals(todos.getListTasks()));
    }

    @Test
    public void removeTaskTest() {
        String task = "A";
        ArrayList<String> list = new ArrayList<>();
        list.remove(task);

        todos.removeTask(task);

        Assertions.assertTrue(list.equals(todos.getListTasks()));
    }

    @Test
    public void getAllTasksTest() {
        ArrayList<String> list = new ArrayList<>();
        String task = "A";
        String task1 = "B";
        list.add(task);
        list.add(task1);

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + " ");
        }
        String expected = sb.toString();

        todos.addTask("A");
        todos.addTask("B");
        String result = todos.getAllTasks();

        Assertions.assertEquals(expected, result);

    }
}
