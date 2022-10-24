package com.lab5_command;


import com.lab5_data.Collection;
import com.utility.ServerMessage;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ExecuteScript {

    String errorNames = "";

    public void setErrorNames(String errorNames) {
        this.errorNames = this.errorNames + errorNames + " ";
    }

    public void executeScript(Connection connection, String nameFile, Operations operations, Collection collection, ServerMessage serverMessage, File serverFile, String login) {
        String[] checkNames = errorNames.toLowerCase().trim().split(" ");
        for (String s: checkNames) {
            if (nameFile.equals(s)) {
                serverMessage.plusMessage("файл " + nameFile + " вызывает рекурсию, поэтому выполнение команды завершается");
                break;
            }
            else {
                setErrorNames(nameFile);
                try {
                    System.out.println("1");
                    InputStream is = new FileInputStream(nameFile);
                    Reader isr = new InputStreamReader(is);
                    StringBuilder string = new StringBuilder();
                    String[] command;
                    while (isr.ready()) {
                        char chr = (char) isr.read();
                        if (chr == '\n') {
                            command = string.toString().toLowerCase().trim().split(" ");
                            operations.operations(connection, command, operations, collection, serverMessage, serverFile, login);
                            string = new StringBuilder();
                        }
                        else {
                            string.append(chr);
                        }
                    }
                    if (!string.toString().equals("")) {
                        command = string.toString().toLowerCase().trim().split(" ");
                        operations.operations(connection, command, operations, collection, serverMessage, serverFile, login);
                    }
                    isr.close();
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
