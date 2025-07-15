package com.springboot.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolDemo2 {


    public static final Queue<Connection> connQueue = new LinkedList<>();

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);

        }
    }

    public static void main(String[] args) {

        Worker work = new SqlWorker();
        executeWork(2, 100, work);
    }

    private static void executeWork(int maxConn, int request, Worker work) {

        for (int i = 0; i < maxConn; i++) {
            Connection connection1 = createConnection1();
            connQueue.add(connection1);
        }
        AtomicInteger vv = new AtomicInteger();
        for (AtomicInteger j = new AtomicInteger(); j.get() < request; j.getAndIncrement()) {

            Runnable r = () -> submitTask(work, vv.incrementAndGet());
            new Thread(r).start();

        }
    }

    private static void submitTask(Worker work, int i) {

        boolean isDone = false;
        for (; !isDone; ) {
            if (!connQueue.isEmpty()) {
                Connection conn;
                //System.out.println(Thread.currentThread()+" Thread started working");
                synchronized (connQueue) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    conn = connQueue.poll();
                }
                if (conn != null) {
                    work.doWork(conn, i);
                    synchronized (connQueue) {
                        connQueue.add(conn);
                    }
                    //System.out.println("Thread end");
                    isDone = true;
                }
            } else {
                //System.out.println("all connection are busy... waiting");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static Connection createConnection1() {

        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
