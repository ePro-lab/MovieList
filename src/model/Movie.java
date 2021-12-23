package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Movie implements Serializable {
    private String title, alternateTitle, director, age, index, budget, year, confiscated, enlisted, duration;
    private  final ArrayList<String> actors, genres, countries;
    private boolean uncut;
    private int entryNumber = 0;

    public Movie(@JsonProperty("title") String title){
        this.title = title;
        this.actors = new ArrayList<>();
        this.genres = new ArrayList<>();
        this.countries = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlternateTitle() {
        return alternateTitle;
    }

    public void setAlternateTitle(String alternateTitle) {
        this.alternateTitle = alternateTitle;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void addActors(String actors) {
        String[] names = actors.split(",");
        this.actors.addAll(Arrays.asList(names));
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void addGenres(String genres) {
        String[] names = genres.split(",");
        this.genres.addAll(Arrays.asList(names));
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean isUncut() {
        return uncut;
    }

    public void setUncut(boolean uncut) {
        this.uncut = uncut;
    }

    public String isConfiscated() {
        return confiscated;
    }

    public void setConfiscated(String confiscated) {
        this.confiscated = confiscated;
    }

    public String isEnlisted() {
        return enlisted;
    }

    public void setEnlisted(String enlisted) {
        this.enlisted = enlisted;
    }

    public String getConfiscated() {
        return confiscated;
    }

    public String getEnlisted() {
        return enlisted;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public ArrayList<String> getCountry() {
        return countries;
    }

    public void addCountry(String countries) {
        String[] names = countries.split(",");
        this.countries.addAll(Arrays.asList(names));
    }

    public int getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber;
    }
}
