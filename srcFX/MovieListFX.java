import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class MovieListFX {
    private ArrayList<MovieFX> movies;
    //private OutputStream out;
    //private InputStream in;

    MovieListFX() {
        this.movies = new ArrayList<>();
       /* try {
            this.out = new FileOutputStream("Movies.txt", true);
            this.in = new FileInputStream("Movies.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    //not used
    public void addMovie(String title,
                         String year, String duration, String genre,
                         String country, String director, String actor,
                         String age, String index, String enlisted,
                         String budget){
        MovieFX movie = new MovieFX(title);
        movie.setYear(year);
        movie.setDuration(duration);
        movie.addGenres(genre);
        movie.setCountry(country);
        movie.setDirector(director);
        movie.addActors(actor);
        movie.setAge(age);
        movie.setIndex(index);
        movie.setEnlisted(enlisted);
        movie.setBudget(budget);
        movies.add(movie);
    }

    void updateOriginal(ListChangeListener.Change<? extends MovieFX> change){
        ObservableList<? extends MovieFX> newList = change.getList();
        this.movies.clear();
        this.movies.addAll(newList);
    }

    ArrayList<MovieFX> getMovieList() {
        return this.movies;
    }

    //not used
/*    public void close() throws IOException {
        this.out.close();
        this.in.close();
    }

*/
}
