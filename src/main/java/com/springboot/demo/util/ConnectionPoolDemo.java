package com.springboot.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolDemo {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);

        }
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        //withoutConnectioPool();
        ArrayBlockingQueue<Connection> queue = (ArrayBlockingQueue<Connection>) getConnections(10);
        for (AtomicInteger i = new AtomicInteger(); i.get() <1000; ) {
        Runnable rr = ()-> {
                if (true || !queue.isEmpty()) {
                    i.getAndIncrement();
                    Connection poll = null;
                    try {
                        poll = queue.take();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        Thread.sleep(20);
                        executeQuery(poll);
                        queue.put(poll);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }else {
                    try {
                        //System.out.println("queue is empty wait..");


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            };
        new Thread(rr).start();
        }


    }

    private static Queue<Connection> getConnections(int min) throws SQLException, ClassNotFoundException {


        Queue<Connection> queue = new ArrayBlockingQueue<>(min+5);
        for (int i = 0; i <min; i++) {
            Connection connection = createConnection();
            queue.add(connection);
        }
        return queue;
    }

    private static void withoutConnectioPool() {
        System.out.println("ConnectionPoolDemo.main");
        long  st =System.currentTimeMillis();
        for (int i=0;i<99;i++) {
          Runnable r = ()->{
              try {
                  Connection conn = createConnection();
                  executeQuery(conn);
                  conn.close();
              } catch (Exception e) {
                      throw new RuntimeException(e);
              }

          };
            new Thread(r).start();
        }
        //   System.out.println("done in "+(System.currentTimeMillis()-st)+" ms");
    }

    private static void executeQuery(Connection conn) throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery("Select now();");
        resultSet.next();
        System.out.println("select called "+resultSet.getString(1));
    }

    private static Connection createConnection() throws ClassNotFoundException, SQLException {



        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");

    }


}
