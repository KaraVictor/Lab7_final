package com.lab5_data;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Movie implements Serializable {
    private long id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private Integer oscarsCount;
    private Long goldenPalmCount;
    private Double totalBoxOffice;
    private MpaaRating mpaaRating;
    private Person operator;
    private String creator;

    // class constructor
    public Movie(String name, Coordinates coordinates, LocalDateTime creationDate, Integer oscarsCount,
                 Long goldenPalmCount, Double totalBoxOffice, MpaaRating mpaaRating, Person operator) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.goldenPalmCount = goldenPalmCount;
        this.totalBoxOffice = totalBoxOffice;
        this.mpaaRating = mpaaRating;
        this.operator = operator;
    }

    public Movie(String name, Coordinates coordinates, Integer oscarsCount,
                 Long goldenPalmCount, Double totalBoxOffice, MpaaRating mpaaRating, Person operator) {
        this.name = name;
        this.coordinates = coordinates;
        this.oscarsCount = oscarsCount;
        this.goldenPalmCount = goldenPalmCount;
        this.totalBoxOffice = totalBoxOffice;
        this.mpaaRating = mpaaRating;
        this.operator = operator;
    }

    public void setCreator(String creator) { this.creator = creator; }

    public String getCreator() { return creator; }

    public void setCreationDate() {
        this.creationDate = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Integer getOscarsCount() {
        return oscarsCount;
    }

    public void setOscarsCount(Integer oscarsCount) {
        this.oscarsCount = oscarsCount;
    }

    public Long getGoldenPalmCount() {
        return goldenPalmCount;
    }

    public void setGoldenPalmCount(Long goldenPalmCount) {
        this.goldenPalmCount = goldenPalmCount;
    }

    public Double getTotalBoxOffice() {
        return totalBoxOffice;
    }

    public void setTotalBoxOffice(Double totalBoxOffice) {
        this.totalBoxOffice = totalBoxOffice;
    }

    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

        public Person getOperator() {
        return operator;
    }

    public void setOperator(Person operator) {
        this.operator = operator;
    }

    public String checkNull (Object check) {
        String res = "";
        if (check == null) {
            return res;
        }
        else {
            return String.valueOf(check);
        }
    }
    @Override
    public String toString() {
        return  "id = " + id + '\n' +
                "name = " + name + '\n' +
                "coordinates = " + "(" + coordinates.getX() + ", " + coordinates.getY() + ")" + '\n' +
                "creationDate = " + creationDate + '\n' +
                "oscarsCount = " + oscarsCount + '\n' +
                "goldenPalmCount = " + goldenPalmCount + '\n' +
                "totalBoxOffice = " + totalBoxOffice + '\n' +
                "mpaaRating = " + mpaaRating + '\n' +
                "name of operator = " + operator.getName() + ". height of operator = " + operator.getHeight() +
                ". nationality of operator = " + operator.getNationality() + '\n';
    }
}