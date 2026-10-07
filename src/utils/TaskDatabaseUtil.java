package utils;

import data.local.database.TaskDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskDatabaseUtil {
    public static void createTables() {
        try(Connection connection = TaskDatabase.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "CREATE TYPE status AS ENUM('PENDING','FINISHED','CANCELED');" +
                    "CREATE TABLE IF NOT EXISTS Task (" +
                         "id SERIAL PRIMARY KEY," +
                         "name VARCHAR(50)," +
                         "description VARCHAR(255)," +
                         "current_status status" +
                        ");"
            )) {

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
