import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class MovieListFX {
    private ArrayList<MovieFX> movies;

    MovieListFX() {
        this.movies = new ArrayList<>();
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
        movie.addCountry(country);
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

    void saveList() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("Movies.dat", false));
            for (MovieFX movie : movies)
                out.writeObject(movie);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    void loadList(ObservableList<MovieFX> movies) {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("Movies.dat"));
            while (true) {
                MovieFX movie = (MovieFX) in.readObject();
                movies.add(movie);                //this calls the ObservableList.add, which calls updateOriginal for each movie ... bad!
            }
        } catch (FileNotFoundException e) {
            //on first start
        } catch (EOFException e){
           //no more objects to read
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null)
                    in.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        //return this.movies;
    }
}

