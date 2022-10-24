package com.lab5_command;

import com.lab5_data.Collection;

public class Info{

    public static String info(Collection collection) {
        return "информация о коллекции:" + "\n" + "тип коллекции: " + "LinkedHashMap" + "\n" +
                "тип содержимого коллекции: " + "Movies" + "\n" +
                "дата инициализации:" + collection.primitiveTime + "\n" +
                "количество предметов в коллекции:" + collection.collection.size() + "\n" + "___";
    }
}
