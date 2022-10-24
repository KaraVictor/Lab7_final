package com.lab5_command;

import com.lab5_data.Collection;
import com.utility.Database;
import com.utility.ServerMessage;

import java.sql.Connection;
import java.sql.SQLException;

public class RemoveKey {

    public void removeKey(Connection connection, String key, Collection collection, ServerMessage serverMessage, String login) throws SQLException {

        if (collection.collection.containsKey(key)) {
            if (collection.collection.get(key).getCreator().equals(login)) {
                Database.removeKey(connection, key);
                collection.collection.remove(key);
                serverMessage.plusMessage("элемент удалён");
            }
            else {
                serverMessage.plusMessage("вы не можете удалить данный элемент" + "\n");
            }
        }
        else {
            serverMessage.plusMessage("нет элемента с таким ключом" + "\n");
        }
    }
}
