package com.lab5_command;


import com.lab5_data.Collection;
import com.utility.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class Clear {

    public void clear(Connection connection, Collection collection, String login) throws SQLException {
        String[] check = collection.collection.keySet().toArray(new String[0]);
        for (String i : check) {
            if (collection.collection.get(i).getCreator().equals(login)) {
                Database.removeKey(connection, i);
                collection.collection.remove(i);
            }
        }
    }
}
