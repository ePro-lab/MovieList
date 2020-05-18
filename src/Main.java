import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        try {
            MovieList list = new MovieList();
            Scanner scanner = new Scanner(System.in);
            String input, title, director, age, yn;
            int year;
            boolean uncut;
            Movie movie = null;
            boolean run = true;

            while(run) {
                System.out.println("1 New entry\n2 save\n3 Add information\n4 Exit\n");
                input = scanner.next();
                switch (input) {
                    case "1":
                        scanner.nextLine();
                        System.out.println("New movie entry...\n");
                        System.out.println("Enter title: ");
                        title = scanner.nextLine();
                        System.out.println("Enter director: ");
                        director = scanner.nextLine();
                        System.out.println("Enter age: ");
                        age = scanner.nextLine();
                        System.out.println("Enter Year");
                        year = scanner.nextInt();
                        System.out.println("Enter uncut (Y/N)");
                        yn = scanner.next();
                        uncut = yn.equals("Y") | yn.equals("y");
                        movie = new Movie(title, director, age, year, uncut);
                        list.addMovie(movie);
                        break;
                    case "2":
                        list.printMovies();

                        break;
                    case "3":
                        while(run) {
                            System.out.println("1 Add actors\n2 Add genres\n3 Add budget\n4 Add confiscated\n5 Add enlisted\n6 Back");
                            input = scanner.next();
                            scanner.nextLine();
                            switch (input) {
                                case "1":
                                    System.out.println("Enter actor Names separated by \",\"");
                                    movie.addActors(scanner.nextLine());
                                    break;
                                case "2":
                                    System.out.println("Enter genres separated by \",\"");
                                    movie.addGenres(scanner.nextLine());
                                    break;
                                case "3":
                                    System.out.println("Enter budget");
                                    movie.setBudget(scanner.nextInt());
                                    break;
                                case "4":
                                    System.out.println("Enter confiscated (Y/N)");
                                    input = scanner.next();
                                    yn = scanner.nextLine();
                                    movie.setConfiscated(yn.equals("Y") | yn.equals("y"));
                                    break;
                                case "5":
                                    System.out.println("Enter enlisted (Y/N)");
                                    input = scanner.next();
                                    yn = scanner.nextLine();
                                    movie.setEnlisted(yn.equals("Y") | yn.equals("y"));
                                    break;
                                case "6":
                                    run = false;
                                    break;
                                default:
                                    System.out.println("\"" + input + "\" not defined");
                                    break;
                            }
                        }
                        run = true;
                        break;
                    case "4":
                        run = false;
                        break;
                    default:
                        System.out.println("\"" + input + "\" not defined");
                        break;
                }

            }
            list.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
