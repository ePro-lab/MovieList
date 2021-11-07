import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Movie implements Serializable {
    private String title, alternateTitle, director, age, index, budget, year, confiscated, enlisted, duration;
    private  final ArrayList<String> actors, genres, countries;
    private boolean uncut;
    private int entryNumber = 0;

    Movie(String title){
        this.title = title;
        this.actors = new ArrayList<>();
        this.genres = new ArrayList<>();
        this.countries = new ArrayList<>();
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
        this.actors.addAll(Arrays.asList(names));
    }

    ArrayList<String> getGenres() {
        return genres;
    }

    void addGenres(String genres) {
        String[] names = genres.split(",");
        this.genres.addAll(Arrays.asList(names));
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

    ArrayList<String> getCountry() {
        return countries;
    }

    void addCountry(String countries) {
        String[] names = countries.split(",");
        this.countries.addAll(Arrays.asList(names));
    }

    int getEntryNumber() {
        return entryNumber;
    }

    void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber;
    }
}
