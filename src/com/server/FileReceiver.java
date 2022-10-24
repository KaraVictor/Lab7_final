package com.server;

import com.lab5_data.Coordinates;
import com.lab5_data.Country;
import com.lab5_data.MpaaRating;
import com.lab5_data.Person;

public class FileReceiver {

    public static String getName(String name) {
        if (name.length() != 0) {
            return name;
        }
        System.out.println("Invalid name");
        return null;
    }

    public static Coordinates getCoordinates(String[] coordinates) {
        if (coordinates.length == 2) {
            try {
                return new Coordinates(Double.parseDouble(coordinates[0]), Long.parseLong(coordinates[1]));
            }
            catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
        System.out.println("Invalid coordinates");
        System.out.println(coordinates[0] + coordinates[1]);
        return null;
    }

    public static Integer getOscarCount(String[] oscarCount) {
        try {
            if (oscarCount[4].equals("")) {
                return 0;
            }
            else  {
                int number = Integer.parseInt(oscarCount[4]);
                if (number > 0) return number;
            }
        }
        catch (NumberFormatException ignored) { }
        System.out.println("Invalid Oscar count");
        return null;
    }

    public static Long getGoldenPalm(String[] goldenPalm) {
        try {
            if (goldenPalm[5].equals("")) {
                return 0L;
            }
            else {
                long number = Long.parseLong(goldenPalm[5]);
                if (number > 0) {
                    return number;
                }
            }
        }
        catch (NumberFormatException ignored) { }
        System.out.println("Invalid Golden palm");
        return null;
    }

    public static Double getTotalBoxOffice(String[] totalBoxOffice) {
        try {
            if (totalBoxOffice[6].equals("")) {
                return 0D;
            }
            else {
                double number = Double.parseDouble(totalBoxOffice[6]);
                if (number > 0) return number;
            }
        }
        catch (NumberFormatException ignored) { }
        System.out.println("Invalid total box office");
        return null;
    }

    public static MpaaRating getMpaaRating(String[] mpaaRating) {
        if (mpaaRating[7].equals("")) {
            return null;
        }
        else {
            if (MpaaRating.contains(mpaaRating[7])) {
                return MpaaRating.valueOf(mpaaRating[7]);
            }
        }
        System.out.println("Invalid MpaaRating");
        return null;
    }

    public static Person getOperator(String[] operator){
        String name;
        if (operator[8].length() != 0) {
            name = operator[8];
        }
        else {
            System.out.println("Invalid name");
            return null;
        }
        Country nationality;
        if (Country.containsCountry(operator[10])) {
            nationality = Country.valueOf(operator[10]);
        }
        else {
            System.out.println("Invalid nationality");
            return null;
        }
        if (operator[9].length() != 0) {
            try {
                long height = Long.parseLong(operator[9]);
                if (height > 0) {
                    return new Person(name, height, nationality);
                }
            }
            catch (NumberFormatException ignored) { }
        }
        System.out.println("Invalid height");
        return null;
    }
}
