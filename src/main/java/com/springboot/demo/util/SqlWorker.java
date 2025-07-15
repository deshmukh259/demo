package com.springboot.demo.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlWorker implements Worker<Connection> {
    @Override
    public boolean doWork(Connection connection, int j) {

        try {
            ResultSet resultSet = connection.createStatement().executeQuery("Select now()");
            resultSet.next();
            System.out.println(j + "--" + "Time now " + resultSet.getString(1));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
