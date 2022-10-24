package com.utility;

        import com.lab5_data.Movie;

        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.SQLException;
        import java.sql.Statement;

public class  Database {
    public static void createTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String createQuery = "CREATE TABLE lab7 (\n" +
                "   key TEXT,\n" +
                "   id SERIAL PRIMARY KEY,\n" +
                "   name TEXT NOT NULL,\n" +
                "   coordinate_x FLOAT NOT NULL,\n" +
                "   coordinate_y INT,\n" +
                "   creationDate TEXT NOT NULL,\n" +
                "   oscarCount INT,\n" +
                "   goldenPalmCount INT,\n" +
                "   totalBoxOffice FLOAT,\n" +
                "   mpaaRating TEXT,\n" +
                "   namePerson TEXT NOT NULL,\n" +
                "   heightPerson INT,\n" +
                "   nationalityPerson TEXT,\n" +
                "   creator TEXT" +
                ");";
        statement.executeUpdate(createQuery);
        try {
            String delQuery = "DROP TABLE usersInfo;";
            statement.executeUpdate(delQuery);
        }
        catch (Exception ignored) {}
        createQuery = "CREATE TABLE usersInfo (\n" +
                "   login TEXT,\n" +
                "   password TEXT" +
                ");";
        statement.executeUpdate(createQuery);
    }

    public static void insertBD(Connection connection, Movie movie, String key, String login) throws SQLException {
        String insertQuery = "INSERT INTO lab7 (key, name, coordinate_x, coordinate_y, creationDate," +
                "oscarCount, goldenPalmCount, totalBoxOffice, mpaaRating, namePerson, heightPerson," +
                "nationalityPerson, creator) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, key);
        preparedStatement.setString(2, movie.getName());
        preparedStatement.setDouble(3, movie.getCoordinates().getX());
        preparedStatement.setLong(4, movie.getCoordinates().getY());
        preparedStatement.setString(5, movie.getCreationDate().toString());
        preparedStatement.setInt(6, movie.getOscarsCount());
        preparedStatement.setLong(7, movie.getGoldenPalmCount());
        preparedStatement.setDouble(8, movie.getTotalBoxOffice());
        preparedStatement.setString(9, movie.getMpaaRating().toString());
        preparedStatement.setString(10, movie.getOperator().getName());
        preparedStatement.setLong(11, movie.getOperator().getHeight());
        preparedStatement.setString(12, movie.getOperator().getNationality().toString());
        preparedStatement.setString(13,login);
        preparedStatement.execute();
    }

    public static boolean updateIntDB(Connection connection, Object obj, String field, int id) throws SQLException {
        Statement statement = connection.createStatement();
        int flag = statement.executeUpdate("UPDATE lab7 SET " + field + " = " + obj + " WHERE id = " + id + ";");
        return (flag > 0);
    }

    public static boolean updateStringDB(Connection connection, Object obj, String field, int id) throws SQLException {
        Statement statement = connection.createStatement();
        int flag = statement.executeUpdate("UPDATE lab7 SET " + field + " = " + "'" + obj + "'" + " WHERE id = " + id + ";");
        return (flag > 0);
    }

    public static void removeKey(Connection connection, String key) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM lab7 WHERE key = " + "'" + key + "'");
    }

    public static void removeId(Connection connection, int id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM lab7 WHERE id = " + "'" + id + "'");
    }
}

