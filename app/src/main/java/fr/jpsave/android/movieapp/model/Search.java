package fr.jpsave.android.movieapp.model;

public class Search {

    private Movie[] Search;
    private String Response;
    private String Error;

    public Movie[] getSearch() {
        return Search;
    }

    public void setSearch(Movie[] search) {
        Search = search;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }
}
