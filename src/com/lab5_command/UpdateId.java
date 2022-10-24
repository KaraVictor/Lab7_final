package com.lab5_command;

import com.lab5_data.*;

import java.util.Scanner;

public class UpdateId {

    public void updateId(int id, Collection collection) {

        String newKey = "";
        for (String key : collection.collection.keySet()) {
            if (collection.collection.get(key).getId() == id) {
                newKey = key;
                break;
            }
        }
        if (newKey.equals("")) {
            System.out.println("нет элемента с таким id");
        }
        else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("введите имя поля, которое вы хотите изменить");
            System.out.println("доступные поля для редактирования: name, coordinates, oscar count, golden palm, total box" +
                            "office, mpaa rating, operator");
            while (true) {
                String[] string = scanner.nextLine().trim().toLowerCase().split(" ");
                boolean f = false;
                switch (string[0]) {
                    case "name": {
                        String name = Receiver.getName();
                        collection.collection.get(newKey).setName(name);
                        break;
                    }
                    case "coordinates": {
                        Coordinates coordinates = Receiver.getCoordinates();
                        collection.collection.get(newKey).setCoordinates(coordinates);
                        break;
                    }
                    case "oscar": {
                        Integer oscarCount = Receiver.getOscarCount();
                        collection.collection.get(newKey).setOscarsCount(oscarCount);
                        break;
                    }
                    case "golden": {
                        Long goldenPalm = Receiver.getGoldenPalm();
                        collection.collection.get(newKey).setGoldenPalmCount(goldenPalm);
                        break;
                    }
                    case "total": {
                        Double totalBoxOffice = Receiver.getTotalBoxOffice();
                        collection.collection.get(newKey).setTotalBoxOffice(totalBoxOffice);
                        break;
                    }
                    case "mpaa": {
                        MpaaRating mpaaRating = Receiver.getMpaaRating();
                        collection.collection.get(newKey).setMpaaRating(mpaaRating);
                        break;
                    }
                    case "operator": {
                        Person operator = Receiver.getOperator();
                        collection.collection.get(newKey).setOperator(operator);
                        return;
                    }
                    default:
                        f = true;
                }
                if (f) {
                    System.out.println("такого поля нет, введите еще раз:");
                } else {
                    System.out.println("элемент изменен");
                }
            }
        }
            System.out.println();
    }
}
