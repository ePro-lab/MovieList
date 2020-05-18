import java.util.ArrayList;

public class Movie {
    private String title, alternateTitle, director, age, index;
    private ArrayList<String> actors, genres;
    private int budget = 0, year;
    private boolean uncut, confiscated, enlisted;

    public Movie(String title, String director, String age, int year, boolean uncut){
        this.title = title;
        this.director = director;
        this.age = age;
        this.year = year;
        this.uncut = uncut;
        this.actors = new ArrayList<>();
        this.genres = new ArrayList<>();
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
        for(int i=0; i < names.length; i++)
            this.actors.add(names[i]);
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void addGenres(String genres) {
        String[] names = genres.split(",");
        for(int i=0; i < names.length; i++)
            this.genres.add(names[i]);
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public boolean isUncut() {
        return uncut;
    }

    public void setUncut(boolean uncut) {
        this.uncut = uncut;
    }

    public boolean isConfiscated() {
        return confiscated;
    }

    public void setConfiscated(boolean confiscated) {
        this.confiscated = confiscated;
    }

    public boolean isEnlisted() {
        return enlisted;
    }

    public void setEnlisted(boolean enlisted) {
        this.enlisted = enlisted;
    }
}
