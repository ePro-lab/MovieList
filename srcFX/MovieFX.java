import java.io.Serializable;
import java.util.ArrayList;

public class MovieFX implements Serializable {
    private String title, alternateTitle, director, age, index, budget, year, confiscated, enlisted, duration, country;
    private ArrayList<String> actors, genres;
    private boolean uncut;
    private int entryNumber = 0;

    MovieFX(String title){
        this.title = title;
        this.actors = new ArrayList<>();
        this.genres = new ArrayList<>();
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getAlternateTitle() {
        return alternateTitle;
    }

    public void setAlternateTitle(String alternateTitle) {
        this.alternateTitle = alternateTitle;
    }

    String getDirector() {
        return director;
    }

    void setDirector(String director) {
        this.director = director;
    }

    String getAge() {
        return age;
    }

    void setAge(String age) {
        this.age = age;
    }

    String getIndex() {
        return index;
    }

    void setIndex(String index) {
        this.index = index;
    }

    ArrayList<String> getActors() {
        return actors;
    }

    void addActors(String actors) {
        String[] names = actors.split(",");
        for (String actor : names )
            this.actors.add(actor);
    }

    ArrayList<String> getGenres() {
        return genres;
    }

    void addGenres(String genres) {
        String[] names = genres.split(",");
        for (String genre : names)
            this.genres.add(genre);
    }

    String getBudget() {
        return budget;
    }

    void setBudget(String budget) {
        this.budget = budget;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    boolean isUncut() {
        return uncut;
    }

    public void setUncut(boolean uncut) {
        this.uncut = uncut;
    }

    String isConfiscated() {
        return confiscated;
    }

    void setConfiscated(String confiscated) {
        this.confiscated = confiscated;
    }

    String isEnlisted() {
        return enlisted;
    }

    void setEnlisted(String enlisted) {
        this.enlisted = enlisted;
    }

    public String getConfiscated() {
        return confiscated;
    }

    String getEnlisted() {
        return enlisted;
    }

    String getDuration() {
        return duration;
    }

    void setDuration(String duration) {
        this.duration = duration;
    }

    String getCountry() {
        return country;
    }

    void setCountry(String country) {
        this.country = country;
    }

    int getEntryNumber() {
        return entryNumber;
    }

    void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber;
    }
}
