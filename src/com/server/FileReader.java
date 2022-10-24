package com.server;

import com.lab5_data.Collection;
import com.lab5_data.Movie;
import com.utility.Database;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;


public class FileReader {

    public void fileReader(Connection connection, Collection collection, File file) {
        try {
            Statement statement = connection.createStatement();
            InputStream is = new FileInputStream(file);
            Reader isr = new InputStreamReader(is);
            StringBuilder string = new StringBuilder();
            String[] command;
            while (isr.ready()) {
                char chr = (char) isr.read();
                if (chr == '\n') {
                    command = string.toString().trim().split(",");
                    string = new StringBuilder();
                    Movie movie = new Movie(FileReceiver.getName(command[1]),
                            FileReceiver.getCoordinates(command[2].replace("(", "").replace(")", "").split(";")),
                            LocalDateTime.parse(command[3]), FileReceiver.getOscarCount(command), FileReceiver.getGoldenPalm(command),
                            FileReceiver.getTotalBoxOffice(command), FileReceiver.getMpaaRating(command), FileReceiver.getOperator(command));
                    Database.insertBD(connection, movie, command[0], "server");
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM lab7" + " WHERE key = " + "'" + command[0] + "'" + ";");
                    if (resultSet.next()) {
                        movie.setId(resultSet.getInt("id"));
                    }
                    movie.setCreator("server");
                    collection.collection.put(command[0], movie);
                }
                else {
                    string.append(chr);
                }
            }
            if (!string.toString().equals("")) {
                command = string.toString().trim().split(",");
                string = new StringBuilder();
                Movie movie = new Movie(FileReceiver.getName(command[1]),
                        FileReceiver.getCoordinates(command[2].replace("(", "").replace(")", "").split(";")),
                        LocalDateTime.parse(command[3]), FileReceiver.getOscarCount(command), FileReceiver.getGoldenPalm(command),
                        FileReceiver.getTotalBoxOffice(command), FileReceiver.getMpaaRating(command), FileReceiver.getOperator(command));
                Database.insertBD(connection, movie, command[0], "server");
                ResultSet resultSet = statement.executeQuery("SELECT * FROM lab7" + " WHERE key = " + "'" + command[0] + "'" + ";");
                if (resultSet.next()) {
                    movie.setId(resultSet.getInt("id"));
                }
                movie.setCreator("server");
                collection.collection.put(command[0], movie);
            }
            isr.close();
            System.out.println("___");
        }
        catch (FileNotFoundException ignored) { } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File processing error");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}