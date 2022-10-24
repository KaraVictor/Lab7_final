package com.lab5_command;

import com.lab5_data.Collection;
import com.utility.ServerMessage;

public class Show{

    public void show(Collection collection, ServerMessage serverMessage) {

        if (collection.collection.size() == 0) {
            serverMessage.plusMessage("коллекция пуста" + "\n");
            serverMessage.plusMessage("___");
        }
        else {
            serverMessage.plusMessage("все элементы коллекции:" + "\n");
            for (String key : collection.collection.keySet()) {
                serverMessage.plusMessage(key + ":" + "\n");
                serverMessage.plusMessage(collection.collection.get(key).toString());
                serverMessage.plusMessage("___" + "\n");
            }
        }
    }
}
