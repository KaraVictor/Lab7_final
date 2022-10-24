package com.lab5_command;

import com.lab5_data.Collection;
import com.utility.ServerMessage;

import java.util.Objects;

public class FilterLessThanOscarsCount {

    public void filterLessThanOscarsCount(Collection collection, int targetCount, ServerMessage serverMessage) {
        int currentCount;
        if (collection.collection.isEmpty()) {
            serverMessage.plusMessage("коллекция пуста" + "\n");
            serverMessage.plusMessage("___");
        }
        else {
            String[] check = collection.collection.keySet().toArray(new String[0]);
            for (String key : check) {
                String res = String.valueOf(collection.collection.get(key).getOscarsCount());
                if (Objects.equals(res, "null")) {
                    currentCount = 0;
                }
                else {
                    currentCount = collection.collection.get(key).getOscarsCount();
                }
                if (currentCount < targetCount) {
                    serverMessage.plusMessage(key + ":" + "\n");
                    serverMessage.plusMessage(collection.collection.get(key).toString() + "\n");
                    serverMessage.plusMessage("___" + "\n");
                }
            }
            serverMessage.plusMessage("отображены все такие элементы коллекции" + "\n");
            serverMessage.plusMessage("___");
        }
    }

}
