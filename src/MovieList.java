import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class MovieList {
    private ArrayList<Movie> movies;
    private OutputStream out;
    private InputStream in;

    public MovieList() throws FileNotFoundException {
        this.movies = new ArrayList<>();
        this.out = new FileOutputStream("Movies.txt", true);
        this.in = new FileInputStream("Movies.txt");
    }

    public void addMovie(Movie movie){
        movies.add(movie);
    }

    public void printMovies() throws NoSuchFieldException, IOException {
       Iterator<Movie> list = movies.iterator();
       Movie current = null;
       StringBuilder output = new StringBuilder();
       while(list.hasNext()) {
           current = list.next();
           output.append("Title:\t\t" + current.getTitle() + "\n");
           if(current.getAlternateTitle() != null) {
               output.append("Alternate Title:\t" + current.getAlternateTitle() + "\n");
           }
           output.append("Director:\t" + current.getDirector() + "\n");
           output.append("Age:\t\t" + current.getAge() + "\n");
           if(current.getIndex() != null)
               output.append("Index:\t\t" + current.getIndex() + "\n");
           if(!current.getActors().isEmpty()) {
               output.append("Actors:");
               Iterator<String> actors = current.getActors().iterator();
               while(actors.hasNext()){
                   int i = 0;
                   if(i == 1)
                       output.append("\t");
                   else
                       i = 1;
                   output.append("\t\t" + actors.next() + "\n");
               }
           }
           if(!current.getGenres().isEmpty()) {
               output.append("Genres:");
               Iterator<String> genres = current.getGenres().iterator();
               while(genres.hasNext()){
                   int i = 0;
                   if(i == 1)
                       output.append("\t");
                   else
                       i = 1;
                   output.append("\t\t" + genres.next() + "\n");
               }
           }
           output.append("Year:\t\t" + current.getYear() + "\n");
           if(current.getBudget() != 0)
               output.append("Budget:\t\t" + current.getBudget() + "\n");
           if(current.isUncut())
             output.append("Uncut:\t\tuncut\n");
           else
               output.append("Uncut:\t\tcut\n");
           if(current.isConfiscated())
               output.append("\t\tConfiscated\n");
           if(current.isEnlisted())
               output.append(("\t\tEnlisted\n"));
           output.append("------------------------------------------------------------------\n\n");
           System.out.println(output.toString());
           out.write(output.toString().getBytes());
       }
       this.deleteList();
    }

    private void deleteList(){
        this.movies = new ArrayList<>();
    }

    public void readMovies(){

    }

    public void close() throws IOException {
        this.out.close();
        this.in.close();
    }
}
