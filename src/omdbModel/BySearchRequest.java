package omdbModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class BySearchRequest extends Request{
    @JsonProperty("Search")
    private ArrayList<Search> search;
    @JsonProperty("totalResults")
    private String totalResults;
    @JsonProperty("Response")
    private String Response;

    public void setSearch(ArrayList<Search> search) {
        this.search = search;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public ArrayList<Search> getSearch() {
        return search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return Response;
    }
}
