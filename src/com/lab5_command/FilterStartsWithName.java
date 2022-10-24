package com.lab5_command;

import com.lab5_data.Collection;
import com.utility.ServerMessage;

public class FilterStartsWithName {

    public void filterStartsWithName (Collection collection, String targetSymbol, ServerMessage serverMessage) {
        String currentSymbol;
        String[] symbols;
        if (collection.collection.isEmpty()) {
            serverMessage.plusMessage("коллекция пуста" + "\n");
            serverMessage.plusMessage("___");
        }
        else {
            for (String key : collection.collection.keySet()) {
                symbols = collection.collection.get(key).getName().split("");
                currentSymbol = String.valueOf(symbols[0]);
                if (currentSymbol.toLowerCase().equals(targetSymbol)) {
                    serverMessage.plusMessage(key + ":" + "\n");
                    serverMessage.plusMessage(collection.collection.get(key).toString() + "\n");
                    serverMessage.plusMessage("___");
                }
            }
            serverMessage.plusMessage("отображены все такие элементы коллекции" + "\n");
            serverMessage.plusMessage("___");
        }
    }
}
