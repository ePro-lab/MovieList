package model;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class MovieList {
    private final ArrayList<Movie> movies;

    public MovieList() {
        this.movies = new ArrayList<>();
    }

    //not used
    public void addMovie(String title,
                         String year, String duration, String genre,
                         String country, String director, String actor,
                         String age, String index, String enlisted,
                         String budget){
        Movie movie = new Movie(title);
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

    public void updateOriginal(ListChangeListener.Change<? extends Movie> change){
        ObservableList<? extends Movie> newList = change.getList();
        this.movies.clear();
        this.movies.addAll(newList);
    }

    public ArrayList<Movie> getMovieList() {
        return this.movies;
    }

    public void setMovieList(Collection<Movie> movies){
        this.movies.clear();
        this.movies.addAll(movies);
    }

    public void saveList() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("Movies.dat", false));
            for (Movie movie : movies)
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

    public void loadList(ObservableList<Movie> movies) {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("Movies.dat"));
            while (true) {
                Movie movie = (Movie) in.readObject();
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

