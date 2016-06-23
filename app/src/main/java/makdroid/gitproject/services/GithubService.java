package makdroid.gitproject.services;

import makdroid.gitproject.model.GitHubResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Grzecho on 23.06.2016.
 */
public interface GithubService {

    @GET("search/repositories")
    Call<GitHubResponse> getReposByName(@Query("q") String q);

    @GET("search/users")
    Call<GitHubResponse> getUsersByName(@Query("q") String q);
}
