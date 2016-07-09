package makdroid.gitproject.services;

import makdroid.gitproject.model.GitHubResponse;
import makdroid.gitproject.model.Repo;
import makdroid.gitproject.model.RepoResponse;
import makdroid.gitproject.model.User;
import makdroid.gitproject.model.UserResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Grzecho on 23.06.2016.
 */
public interface GithubService {

    @GET("search/repositories")
    Call<GitHubResponse<Repo>> getReposByName(@Query("q") String q);

    @GET("search/users")
    Call<GitHubResponse<User>> getUsersByName(@Query("q") String q);
}
