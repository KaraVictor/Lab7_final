package com.lab5_command;

import com.lab5_data.Collection;
import com.utility.ServerMessage;

public class MaxByTotalBoxOffice {

    public void maxByTotalBoxOffice (Collection collection, ServerMessage serverMessage) {
        double count = 0;
        String targetKey = "";
        for (String key : collection.collection.keySet()) {
            if (collection.collection.get(key).getTotalBoxOffice() > count) {
                count = collection.collection.get(key).getTotalBoxOffice();
                targetKey = key;
            }
        }
        if (count == 0) {
            serverMessage.plusMessage("нет таких элементов коллекции" + "\n");
            serverMessage.plusMessage("___");;
        }
        else {
            serverMessage.plusMessage(targetKey + ":" + "\n");
            serverMessage.plusMessage(collection.collection.get(targetKey).toString() + "\n");
            serverMessage.plusMessage("элемент выведен" + "\n");
            serverMessage.plusMessage("___");
        }
    }
}
