package makdroid.gitproject.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Grzecho on 23.06.2016.
 */
public interface GithubService {

    @GET("search/repositories")
    Call<Object> getRepoByName(@Query("q") String q);
}
