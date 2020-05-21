import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UI extends Application {

    private static int entries = 0;
    private static int offset = 0;
    private static boolean loading = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("MovieList");
        BorderPane root = new BorderPane();


        HBox hbox = new HBox();
        root.setTop(hbox);
        Label label = new Label("Look at: ");
        hbox.getChildren().add(label);
        Button btn = new Button();
        btn.setText("Title");
        hbox.getChildren().add(btn);
        btn = new Button();
        btn.setText("Director");
        hbox.getChildren().add(btn);
        btn = new Button();
        btn.setText("Year");
        hbox.getChildren().add(btn);
        btn = new Button();
        btn.setText("Length");
        hbox.getChildren().add(btn);
        btn = new Button();
        btn.setText("Age restriction");
        hbox.getChildren().add(btn);
        btn = new Button();
        btn.setText("Genres");
        hbox.getChildren().add(btn);
        btn = new Button();
        btn.setText("Index");
        hbox.getChildren().add(btn);
        btn = new Button();
        btn.setText("Actors");
        hbox.getChildren().add(btn);
        label = new Label("search");
        hbox.getChildren().add(label);
        TextField tf = new TextField();
        hbox.getChildren().add(tf);

        //list
        VBox list = new VBox();
        root.setCenter(list);

        Text entryNumber = new Text();

        MovieListFX movieList = new MovieListFX();
        ObservableList<MovieFX> movies = FXCollections.observableArrayList(movieList.getMovieList());
        movies.addListener((ListChangeListener) change -> {
            if(change.next() && change.wasAdded() && !loading){
                list.getChildren().add(createEntry(movies.get(movies.size() - 1), movies, primaryStage));
                entryNumber.setText("Number of entries: " + (entries-offset));
            }else
                if(change.wasRemoved()) {
                    offset++;
                   //reload
                }
        });
        movies.addListener(movieList::updateOriginal);

        BorderPane bottom = new BorderPane();
        root.setBottom(bottom);
        entryNumber.setText("Number of entries: " + (entries-offset));
        bottom.setLeft(entryNumber);
        btn = new Button("+");
        btn.setOnAction(e ->
                addMovie(primaryStage, movies));
        bottom.setRight(btn);


       /* TableView table = new TableView();
        table.setEditable(false);
        TableColumn titleCol = new TableColumn("Title");
        TableColumn directorCol = new TableColumn("Director");
        TableColumn yearCol = new TableColumn("Year");
        TableColumn uncutCol = new TableColumn("Uncut");
        TableColumn ageRestCol = new TableColumn("Age Restriction");

        ObservableList<Movie> movies = FXCollections.observableArrayList(
                new Movie("Bad Taste","Peter Jackson", "SPIO/JK strafrechtlich unbedenklich", 1987, true),
                new Movie("Bad Taste","Peter Jackson", "SPIO/JK strafrechtlich unbedenklich", 1987, true),
                new Movie("Bongobingo","Peter Sahningen", "FSK 6", 1999, true)
        );
        titleCol.setCellValueFactory(
                new PropertyValueFactory<Movie, String>("title")
        );
        directorCol.setCellValueFactory(
                new PropertyValueFactory<Movie, String>("director")
        );
        yearCol.setCellValueFactory(
                new PropertyValueFactory<Movie, Integer>("year")
        );
        uncutCol.setCellValueFactory(
                new PropertyValueFactory<Movie, Boolean>("uncut")
        );
        ageRestCol.setCellValueFactory(
                new PropertyValueFactory<Movie, String>("age")
        );

        table.getColumns().addAll(titleCol, directorCol, yearCol, uncutCol, ageRestCol);
        root.setCenter(table);

        table.setItems(movies);

        HBox hbox = new HBox();
        root.setBottom(hbox);
        Button btn = new Button();
        btn.setText("+");
        btn.setStyle("-fx-font-size:40");
        btn.setMinSize(100,100);
        hbox.getChildren().add(btn);
*/


        primaryStage.setScene((new Scene(root, 1000, 800)));

        primaryStage.setOnShowing(e->{
            movieList.loadList(movies);
            for(MovieFX movie : movieList.getMovieList()) {
                movie.setEntryNumber(0); //has to be reset here to count the entries properly
                list.getChildren().add(createEntry(movie, movies, primaryStage));
                entryNumber.setText("Number of entries: " + (entries-offset));
            }
            loading = !loading;
        });
        primaryStage.show();

        primaryStage.setOnCloseRequest(e-> movieList.saveList());
    }

    private void addMovie(Stage primaryStage, ObservableList<MovieFX> movieList) {
        Stage addMovieStage = new Stage();
        addMovieStage.setTitle("Add movie");
        addMovieStage.initModality(Modality.WINDOW_MODAL);
        addMovieStage.initOwner(primaryStage);


        GridPane root = new GridPane();

        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        //size column 1
        ColumnConstraints column = new ColumnConstraints(50, 50, 300);
        column.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(column);
        //size column 2
        column = new ColumnConstraints(150, 150, 300);
        column.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(column);
        //size column 3
        column = new ColumnConstraints(50, 50, 300);
        column.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(column);
        //size column 4
        column = new ColumnConstraints(150, 150, 300);
        column.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(column);
        //size column 5
        column = new ColumnConstraints(80, 80, 300);
        column.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(column);
        //size column 6
        column = new ColumnConstraints(150, 150, 300);
        column.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(column);
        //size column 7
        column = new ColumnConstraints(50, 50, 300);
        column.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(column);
        //size column 8
        column = new ColumnConstraints(150, 150, 300);
        column.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(column);

        //title
        Label lTitle = new Label("Title");
        GridPane.setHalignment(lTitle, HPos.RIGHT);
        GridPane.setConstraints(lTitle, 0, 0);
        root.getChildren().add(lTitle);
        TextField tTitle = new TextField();
        GridPane.setConstraints(tTitle, 1, 0);
        GridPane.setColumnSpan(tTitle, 7);
        root.getChildren().add(tTitle);
        //first column
        //year
        Label lYear = new Label("Year");
        GridPane.setHalignment(lYear, HPos.RIGHT);
        GridPane.setConstraints(lYear, 0, 1);
        root.getChildren().add(lYear);
        TextField tYear = new TextField();
        GridPane.setConstraints(tYear, 1, 1);
        root.getChildren().add(tYear);
        //duration
        Label lDuration = new Label("Duration");
        GridPane.setHalignment(lDuration, HPos.RIGHT);
        GridPane.setConstraints(lDuration, 0, 2);
        root.getChildren().add(lDuration);
        TextField tDuration = new TextField();
        GridPane.setConstraints(tDuration, 1, 2);
        root.getChildren().add(tDuration);
        //genres
        Label lGenre = new Label("Genre*");
        GridPane.setHalignment(lGenre, HPos.RIGHT);
        GridPane.setConstraints(lGenre, 0, 3);
        root.getChildren().add(lGenre);
        TextField tGenre = new TextField();
        GridPane.setConstraints(tGenre, 1, 3);
        root.getChildren().add(tGenre);
        //second column
        //country
        Label lCountry = new Label("Country*");
        GridPane.setHalignment(lCountry, HPos.RIGHT);
        GridPane.setConstraints(lCountry, 2, 1);
        root.getChildren().add(lCountry);
        TextField tCountry = new TextField();
        GridPane.setConstraints(tCountry, 3, 1);
        root.getChildren().add(tCountry);
        //director
        Label lDirector = new Label("Director");
        GridPane.setHalignment(lDirector, HPos.RIGHT);
        GridPane.setConstraints(lDirector, 2, 2);
        root.getChildren().add(lDirector);
        TextField tDirector = new TextField();
        GridPane.setConstraints(tDirector, 3, 2);
        root.getChildren().add(tDirector);
        //actor
        Label lActor = new Label("Actor*");
        GridPane.setHalignment(lActor, HPos.RIGHT);
        GridPane.setConstraints(lActor, 2, 3);
        root.getChildren().add(lActor);
        TextField tActor = new TextField();
        GridPane.setConstraints(tActor, 3, 3);
        root.getChildren().add(tActor);
        //third column
        //age restriction
        Label lAge = new Label("Age restriction");
        GridPane.setHalignment(lAge, HPos.RIGHT);
        GridPane.setConstraints(lAge, 4, 1);
        root.getChildren().add(lAge);
        TextField tage = new TextField();
        GridPane.setConstraints(tage, 5, 1);
        root.getChildren().add(tage);
        //index
        Label lIndex = new Label("Index");
        GridPane.setHalignment(lIndex, HPos.RIGHT);
        GridPane.setConstraints(lIndex, 4, 2);
        root.getChildren().add(lIndex);
        TextField tIndex = new TextField();
        GridPane.setConstraints(tIndex, 5, 2);
        root.getChildren().add(tIndex);
        //enlisted
        Label lEnlisted = new Label("Enlisted");
        GridPane.setHalignment(lEnlisted, HPos.RIGHT);
        GridPane.setConstraints(lEnlisted, 4, 3);
        root.getChildren().add(lEnlisted);
        TextField tEnlisted = new TextField();
        GridPane.setConstraints(tEnlisted, 5, 3);
        root.getChildren().add(tEnlisted);
        //fourth column
        //budget
        Label lBudget = new Label("Budget");
        GridPane.setHalignment(lBudget, HPos.RIGHT);
        GridPane.setConstraints(lBudget, 6, 1);
        root.getChildren().add(lBudget);
        TextField tBudget = new TextField();
        GridPane.setConstraints(tBudget, 7, 1);
        root.getChildren().add(tBudget);

        Button btn = new Button("save");
        btn.setOnAction(e -> {
            MovieFX newMovie = new MovieFX(tTitle.getText());
            newMovie.setYear(tYear.getText());
            newMovie.setDuration(tDuration.getText());
            newMovie.addGenres(tGenre.getText());
            newMovie.setCountry(tCountry.getText());            //only one String, not multiple countries => no search ##########################################################################
            newMovie.setDirector(tDirector.getText());
            newMovie.addActors(tActor.getText());
            newMovie.setAge(tage.getText());
            newMovie.setIndex(tIndex.getText());
            newMovie.setEnlisted(tEnlisted.getText());
            newMovie.setBudget(tBudget.getText());
            movieList.add(newMovie);
            /*    added to original - movieList was MovieListFX
            movieList.addMovie(tTitle.getText(),
                    tYear.getText(), tDuration.getText(), tGenre.getText(),
                    tCountry.getText(), tDirector.getText(), tActor.getText(),
                    tage.getText(), tIndex.getText(), tEnlisted.getText(),
                    tBudget.getText());

             */
            addMovieStage.close();
        });
        btn.setMinSize(150, 30);
        GridPane.setConstraints(btn, 7, 6);
        root.getChildren().add(btn);

        Text endText = new Text("* seperate by \",\"");
        GridPane.setConstraints(endText, 0, 7);
        root.getChildren().add(endText);

        addMovieStage.setScene(new Scene(root, 925, 230));
        addMovieStage.show();
    }

    private void editMovie(MovieFX movie, Stage primaryStage, ObservableList<MovieFX> movieList, HBox entry) {
        Stage editMovieStage = new Stage();
        editMovieStage.setTitle("Edit movie");
        editMovieStage.initModality(Modality.WINDOW_MODAL);
        editMovieStage.initOwner(primaryStage);


        GridPane root = new GridPane();

        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        //size column 1
        ColumnConstraints column = new ColumnConstraints(50, 50, 300);
        column.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(column);
        //size column 2
        column = new ColumnConstraints(150, 150, 300);
        column.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(column);
        //size column 3
        column = new ColumnConstraints(50, 50, 300);
        column.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(column);
        //size column 4
        column = new ColumnConstraints(150, 150, 300);
        column.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(column);
        //size column 5
        column = new ColumnConstraints(80, 80, 300);
        column.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(column);
        //size column 6
        column = new ColumnConstraints(150, 150, 300);
        column.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(column);
        //size column 7
        column = new ColumnConstraints(50, 50, 300);
        column.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(column);
        //size column 8
        column = new ColumnConstraints(150, 150, 300);
        column.setHgrow(Priority.NEVER);
        root.getColumnConstraints().add(column);

        //title
        Label lTitle = new Label("Title");
        GridPane.setHalignment(lTitle, HPos.RIGHT);
        GridPane.setConstraints(lTitle, 0, 0);
        root.getChildren().add(lTitle);
        TextField tTitle = new TextField();
        tTitle.setText(movie.getTitle());
        GridPane.setConstraints(tTitle, 1, 0);
        GridPane.setColumnSpan(tTitle, 7);
        root.getChildren().add(tTitle);
        //first column
        //year
        Label lYear = new Label("Year");
        GridPane.setHalignment(lYear, HPos.RIGHT);
        GridPane.setConstraints(lYear, 0, 1);
        root.getChildren().add(lYear);
        TextField tYear = new TextField();
        tYear.setText(movie.getYear());
        GridPane.setConstraints(tYear, 1, 1);
        root.getChildren().add(tYear);
        //duration
        Label lDuration = new Label("Duration");
        GridPane.setHalignment(lDuration, HPos.RIGHT);
        GridPane.setConstraints(lDuration, 0, 2);
        root.getChildren().add(lDuration);
        TextField tDuration = new TextField();
        tDuration.setText(movie.getDuration());
        GridPane.setConstraints(tDuration, 1, 2);
        root.getChildren().add(tDuration);
        //genres
        Label lGenre = new Label("Genre*");
        GridPane.setHalignment(lGenre, HPos.RIGHT);
        GridPane.setConstraints(lGenre, 0, 3);
        root.getChildren().add(lGenre);
        TextField tGenre = new TextField();
        tGenre.setText(movie.getGenres().toString());
        GridPane.setConstraints(tGenre, 1, 3);
        root.getChildren().add(tGenre);
        //second column
        //country
        Label lCountry = new Label("Country*");
        GridPane.setHalignment(lCountry, HPos.RIGHT);
        GridPane.setConstraints(lCountry, 2, 1);
        root.getChildren().add(lCountry);
        TextField tCountry = new TextField();
        tCountry.setText(movie.getCountry());
        GridPane.setConstraints(tCountry, 3, 1);
        root.getChildren().add(tCountry);
        //director
        Label lDirector = new Label("Director");
        GridPane.setHalignment(lDirector, HPos.RIGHT);
        GridPane.setConstraints(lDirector, 2, 2);
        root.getChildren().add(lDirector);
        TextField tDirector = new TextField();
        tDirector.setText(movie.getDirector());
        GridPane.setConstraints(tDirector, 3, 2);
        root.getChildren().add(tDirector);
        //actor
        Label lActor = new Label("Actor*");
        GridPane.setHalignment(lActor, HPos.RIGHT);
        GridPane.setConstraints(lActor, 2, 3);
        root.getChildren().add(lActor);
        TextField tActor = new TextField();
        tActor.setText(movie.getActors().toString());
        GridPane.setConstraints(tActor, 3, 3);
        root.getChildren().add(tActor);
        //third column
        //age restriction
        Label lAge = new Label("Age restriction");
        GridPane.setHalignment(lAge, HPos.RIGHT);
        GridPane.setConstraints(lAge, 4, 1);
        root.getChildren().add(lAge);
        TextField tage = new TextField();
        tage.setText(movie.getAge());
        GridPane.setConstraints(tage, 5, 1);
        root.getChildren().add(tage);
        //index
        Label lIndex = new Label("Index");
        GridPane.setHalignment(lIndex, HPos.RIGHT);
        GridPane.setConstraints(lIndex, 4, 2);
        root.getChildren().add(lIndex);
        TextField tIndex = new TextField();
        tIndex.setText(movie.getIndex());
        GridPane.setConstraints(tIndex, 5, 2);
        root.getChildren().add(tIndex);
        //enlisted
        Label lEnlisted = new Label("Enlisted");
        GridPane.setHalignment(lEnlisted, HPos.RIGHT);
        GridPane.setConstraints(lEnlisted, 4, 3);
        root.getChildren().add(lEnlisted);
        TextField tEnlisted = new TextField();
        tEnlisted.setText(movie.getEnlisted());
        GridPane.setConstraints(tEnlisted, 5, 3);
        root.getChildren().add(tEnlisted);
        //fourth column
        //budget
        Label lBudget = new Label("Budget");
        GridPane.setHalignment(lBudget, HPos.RIGHT);
        GridPane.setConstraints(lBudget, 6, 1);
        root.getChildren().add(lBudget);
        TextField tBudget = new TextField();
        tBudget.setText(movie.getBudget());
        GridPane.setConstraints(tBudget, 7, 1);
        root.getChildren().add(tBudget);

        Button btn = new Button("save");
        btn.setOnAction(e -> {
            movie.setTitle(tTitle.getText());
            movie.setYear(tYear.getText());
            movie.setDuration(tDuration.getText());
            movie.addGenres(tGenre.getText());
            movie.setCountry(tCountry.getText());            //only one String, not multiple countries => no search ##########################################################################
            movie.setDirector(tDirector.getText());
            movie.addActors(tActor.getText());
            movie.setAge(tage.getText());
            movie.setIndex(tIndex.getText());
            movie.setEnlisted(tEnlisted.getText());
            movie.setBudget(tBudget.getText());
            movieList.remove(movie.getEntryNumber()-1);
            movieList.add(movie);
            entry.getChildren().clear();                      //replace
            editMovieStage.close();
        });
        btn.setMinSize(150, 30);
        GridPane.setConstraints(btn, 7, 6);
        root.getChildren().add(btn);

        Text endText = new Text("* seperate by \",\"");
        GridPane.setConstraints(endText, 0, 7);
        root.getChildren().add(endText);

        editMovieStage.setScene(new Scene(root, 925, 230));
        editMovieStage.show();

    }

    private HBox createEntry(MovieFX movie, ObservableList<MovieFX> movies, Stage primaryStage) {
        if (movie.getEntryNumber() == 0) {
            entries++;
            movie.setEntryNumber(entries);
        }
        //one entry
        HBox entry = new HBox();
        entry.setSpacing(30);
        if (entries % 2 == 1)
            entry.setStyle("-fx-background-color: #FFE9A3;");
        else
            entry.setStyle("-fx-background-color: #FFF6CF;");
        //title
        Label title = new Label(movie.getTitle());
        title.setStyle("-fx-font-size: 20px;" +
                "-fx-font-weight: bold");
        title.setMinWidth(40);
        title.setMinHeight(40);
        entry.getChildren().add(title);
        //second column - year, length, genres
        VBox entryInfo = new VBox();
        entryInfo.getChildren().add(new Label("Year: \t" + movie.getYear()));
        entryInfo.getChildren().add(new Label("Duration: \t" + movie.getDuration()));
        entryInfo.getChildren().add(new Label("Genres: \t" + movie.getGenres()));
        entry.getChildren().add(entryInfo);
        //thirs column - country, director, age restriction
        entryInfo = new VBox();
        entryInfo.getChildren().add(new Label("Country: \t" + movie.getCountry()));
        entryInfo.getChildren().add(new Label("Director: \t" + movie.getDirector()));
        entryInfo.getChildren().add(new Label("Actor: \t" + movie.getActors()));
        entry.getChildren().add(entryInfo);
        //fourth column - age restriction, index, confiscated
        entryInfo = new VBox();
        entryInfo.getChildren().add(new Label("Age restriction: " + movie.getAge()));
        entryInfo.getChildren().add(new Label("Index:\t\t  " + movie.getIndex()));
        entryInfo.getChildren().add(new Label("Enlisted:\t\t  no" + movie.getEnlisted()));
        entry.getChildren().add(entryInfo);
        //fifth column - budget
        entryInfo = new VBox();
        entryInfo.getChildren().add(new Label("Budget: " + movie.getBudget()));
        entry.getChildren().add(entryInfo);
        //sixt column - edit, delete
        entryInfo = new VBox();
        Button btn = new Button("edit");
        btn.setOnAction(e -> editMovie(movie, primaryStage, movies, entry));
        entryInfo.getChildren().add(btn);
        btn = new Button("x");
        btn.setOnAction( e-> {
            movies.remove(movie);
            entry.getChildren().clear();
        });
        entryInfo.getChildren().add(btn);
        entry.getChildren().add(entryInfo);

        return entry;
    }
}
