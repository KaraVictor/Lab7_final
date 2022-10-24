package com.lab5_command;

import com.lab5_data.Collection;
import com.lab5_data.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {

    public void save(Collection collection, File file) {
        try {
            FileWriter writer = new FileWriter(file);
            for (String key : collection.collection.keySet()) {
                Movie movie = collection.collection.get(key);
                writer.write(key + "," + movie.getName() + "," + "(" + movie.getCoordinates().getX()
                        + ";" + movie.getCoordinates().getY() + ")" + "," +
                        movie.getCreationDate() + "," + movie.checkNull(movie.getOscarsCount()) + ","
                        + movie.checkNull(movie.getGoldenPalmCount())  + "," +
                        movie.checkNull(movie.getTotalBoxOffice()) + "," + movie.checkNull(movie.getMpaaRating())
                        + "," + movie.getOperator().getName() + "," +
                        movie.checkNull(movie.getOperator().getHeight())
                        + "," + movie.checkNull(movie.getOperator().getNationality()) + "\n");
            }

            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
