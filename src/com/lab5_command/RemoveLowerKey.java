package com.lab5_command;

import com.lab5_data.Collection;
import com.utility.Database;
import com.utility.ServerMessage;

import java.sql.Connection;
import java.sql.SQLException;

public class RemoveLowerKey {

    public void removeLowerKey(Connection connection, String key, Collection collection, ServerMessage serverMessage, String login) throws SQLException {

        if (collection.collection.isEmpty()) {
            serverMessage.plusMessage("коллекция пуста" + "\n");
            serverMessage.plusMessage("___");
        }
        else {
            String[] check = collection.collection.keySet().toArray(new String[0]);
            for (String i : check) {
                if (key.compareTo(i) > 0  && collection.collection.get(key).getCreator().equals(login)) {
                    Database.removeKey(connection, i);
                    collection.collection.remove(i);
                }
            }
            serverMessage.plusMessage("все элементы коллекции, ключ которых меньше " + key +
                    ", удалены из коллекции" + "\n");
            serverMessage.plusMessage("___");
        }
    }
}

