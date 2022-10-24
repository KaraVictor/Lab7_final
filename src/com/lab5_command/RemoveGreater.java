package com.lab5_command;

import com.lab5_data.Collection;
import com.utility.ServerMessage;

import java.sql.Connection;
import java.sql.SQLException;

public class RemoveGreater {

    public void removeGreater (Connection connection, Collection collection, String key, ServerMessage serverMessage, String login) throws SQLException {
        RemoveGreaterKey removeGreaterKey = new RemoveGreaterKey();
        removeGreaterKey.removeGreaterKey(connection, key, collection, serverMessage, login);
    }
}

