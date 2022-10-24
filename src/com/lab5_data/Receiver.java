package com.lab5_data;

import java.util.Scanner;

public class Receiver {

    // the class is needed in order to receive fields from the console

    private static final Scanner scanner = new Scanner(System.in);

    public static String getName() {
        while (true) {
            System.out.println("Enter movie name:");
            String name = scanner.nextLine().trim();
            if (name.length() != 0) {
                return name;
            }
            System.out.println("Invalid name, please re-enter the name:");
        }
    }

    public static Coordinates getCoordinates() {
        System.out.println("Enter movie coordinates:");
        while (true) {
            String[] string = scanner.nextLine().trim().split(" ");
            if(string.length == 2) {
                try {
                    return new Coordinates(Double.parseDouble(string[0]), Long.parseLong(string[1])); // написать комментарии
                }
                catch (NumberFormatException ignored) { }
            }
            System.out.println("Invalid coordinates, please re-enter the coordinates:");
        }
    }

    public static Integer getOscarCount() {
        System.out.println("Enter Oscar count: ");
        while (true) {
            String[] string = scanner.nextLine().trim().split(" ");
            if (string.length == 1) {
                if (string[0].equals("")) {
                    return null;
                }
                try {
                    int number = Integer.parseInt(string[0]);
                    if (number > 0) return number;
                }
                catch (NumberFormatException ignored) { }
            }
            System.out.println("Invalid Oscar count, please re-enter the Oscar count:");
        }
    }

    public static Long getGoldenPalm() {
        System.out.println("Введите количество Золотых пальмовых ветвей: ");
        while (true) {
            String[] string = scanner.nextLine().trim().split(" ");
            if (string.length == 1) {
                if (string[0].equals("")) {
                    return null;
                }
                try {
                    long number = Long.parseLong(string[0]);
                    if (number > 0) return number;
                }
                catch (NumberFormatException ignored) { }
            }
            System.out.println("Вы ввели неверное количество Золотых пальмовых ветвей: ");
        }
    }

    public static Double getTotalBoxOffice() {
        System.out.println("Введите кассовые сборы фильма: ");
        while (true) {
            String[] string = scanner.nextLine().trim().split(" ");
            if (string.length == 1) {
                if (string[0].equals("")) {
                    return null;
                }
                try {
                    double number = Double.parseDouble(string[0]);
                    if (number > 0) return number;
                }
                catch (NumberFormatException ignored) { }
            }
            System.out.println("Вы ввели неверные кассовые сборы: ");
        }
    }

    public static MpaaRating getMpaaRating() {
        System.out.println("Выберете возрастное ограничение фильма: ");
        for (MpaaRating climate : MpaaRating.values()) {
            System.out.println(climate);
        }
        while (true) {
            String string = scanner.nextLine().trim();
            if (string.equals("")) {
                return null;
            }
            if (MpaaRating.contains(string)) {
                return MpaaRating.valueOf(string);
            }
            System.out.println("Вы ввели неверной возрастной рейтинг фильма: ");
        }
    }

    public static Person getOperator(){
        String name;
        System.out.println("Введите имя оператора: ");
        while (true) {
            String string = scanner.nextLine().trim();
            if (string.length() != 0) {
                name = string;
                break;
            }
            System.out.println("Вы введи неверное имя, введите ещё раз: ");
        }
        Country nationality;
        System.out.println("Выберете страну: ");
        for (Country country : Country.values()) {
            System.out.println(country);
        }
        while (true) {
            String string = scanner.nextLine().trim();
            if (string.equals("")) {
                nationality = null;
                break;
            }
            if (Country.containsCountry(string)) {
                nationality = Country.valueOf(string);
                break;
            }
            System.out.println("Вы ввели неверную страну: ");
        }
        long height;
        System.out.println("Введите рост оператора: ");
        while (true) {
            String string = scanner.nextLine().trim();
            if (string.equals("")) {
                return new Person(name, null, nationality);
            }
            if (string.length() != 0) {
                try {
                    height = Long.parseLong(string);
                    if (height > 0) {
                        return new Person(name, height, nationality);
                    }
                }
                catch (NumberFormatException ignored) { }
            }
            System.out.println("Неверный рост, введите ещё раз: ");
        }
    }
}
