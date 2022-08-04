package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class TodoServer {

    static class Object {

        String type;
        String task;

        public Object(String type, String task) {
            this.type = type;
            this.task = task;
        }
    }

    private int port;
    private Todos todos;


    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() {
        System.out.println("Starting server at " + port + "...");

        try (ServerSocket serverSocket = new ServerSocket(port);) {

            while (true) {
                try (
                        Socket clientSocket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    final String clientTask = in.readLine();

                    Object object = new Gson().fromJson(clientTask, Object.class);

                    String type = object.type;
                    String task = object.task;

                    if (type.equals("ADD")) {
                        todos.addTask(task);
                    } else {
                        if (type.equals("REMOVE")) {
                            todos.removeTask(task);
                        }
                    }

                    out.println(String.format(todos.getAllTasks()));
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }

    }
}