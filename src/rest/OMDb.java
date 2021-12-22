package rest;

import app.GUI;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import javafx.stage.Stage;
import model.Movie;
import omdbModel.BySearchRequest;
import omdbModel.ByTitleSearchRequest;
import omdbModel.Request;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class OMDb {
    private static String key;

    public static void setKey(String key) {
        OMDb.key = key;
    }

    public static Request search(ArrayList<String> args, Stage callingStage){
        try {
            //first request for number of fitting entries if year is empty
            if(args.get(1).equals("")) {
                URIBuilder uriBuilder = new URIBuilder()
                        .setScheme("http")
                        .setHost("www.omdbapi.com/")
                        .addParameter("apikey", key)
                        .addParameter("s", args.get(0));

                HttpRequest request = HttpRequest.newBuilder(uriBuilder.build())
                        .GET()
                        .build();

                HttpClient client = HttpClient.newBuilder().build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                ObjectMapper objectMapper = new ObjectMapper();
                final InjectableValues.Std injectableValues = new InjectableValues.Std();
                injectableValues.addValue("status", response.statusCode());
                objectMapper.setInjectableValues(injectableValues);

                try {
                    BySearchRequest sbr = objectMapper.readValue(response.body(), BySearchRequest.class);
                    if(sbr.getTotalResults().equals("1"))
                        return searchFittingTitle(sbr.getSearch().get(0).getTitle(), sbr.getSearch().get(0).getYear());

                    Movie m = GUI.requestResultList(sbr.getSearch(), callingStage);
                    return searchFittingTitle(m.getTitle(), m.getYear());

                }catch (UnrecognizedPropertyException e){
                    if(response.body().contains("Too many results"))
                        return new Request(413);
                    if(response.body().contains("Movie not found!"))
                        return new Request(404);
                }catch (NullPointerException ignored){
                    //if no title is chosen / window closed
                }
                System.out.println(response.body());
            } else
                return searchFittingTitle(args.get(0), args.get(1));

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new Request(-1); //on window close
    }

    public static Request searchFittingTitle(String title, String year){
        try {
            URIBuilder builder = new URIBuilder()
                    .setScheme("http")
                    .setHost("www.omdbapi.com/")
                    .addParameter("apikey", key)
                    .addParameter("t", title)
                    .addParameter("y", year);

            HttpRequest request = HttpRequest.newBuilder(builder.build())
                    .GET()
                    .build();

            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response =client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            final InjectableValues.Std injectableValues = new InjectableValues.Std();
            injectableValues.addValue("status", response.statusCode());
            objectMapper.setInjectableValues(injectableValues);

            try{
                return objectMapper.readValue(response.body(), ByTitleSearchRequest.class);
            }catch (UnrecognizedPropertyException e){
                if(response.body().contains("Too many results"))
                    return new Request(413);
                if(response.body().contains("Movie not found!"))
                    return new Request(404);
                e.printStackTrace();
            }
            System.out.println(response);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new Request(-1);
    }
}
