package makdroid.gitproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import makdroid.gitproject.model.GitHubResponse;
import makdroid.gitproject.services.GithubService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Inject
    GithubService githubService;

    @Bind(R.id.editTextSearch)
    EditText mEditTextSearch;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeDependencyInjector();
        initEditTextSearch();
    }

    private void initializeDependencyInjector() {
        GitApplication application = (GitApplication) getApplication();
        application.getNetComponent().inject(this);
    }

    void initEditTextSearch() {
        mEditTextSearch.addTextChangedListener(searchWatcher);
    }

    private final TextWatcher searchWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {

        }
    };

    private void requestJSON() {
        Call<GitHubResponse> getRepos = githubService.getReposByName("memapplication");
        getRepos.enqueue(new Callback<GitHubResponse>() {
            @Override
            public void onResponse(Call<GitHubResponse> call, Response<GitHubResponse> response) {
                GitHubResponse jsonResponse = response.body();
                Log.d("success", response.message());
            }

            @Override
            public void onFailure(Call<GitHubResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

        Call<GitHubResponse> getUsers = githubService.getUsersByName("gylopl");
        getUsers.enqueue(new Callback<GitHubResponse>() {
            @Override
            public void onResponse(Call<GitHubResponse> call, Response<GitHubResponse> response) {
                GitHubResponse jsonResponse = response.body();
                Log.d("success", response.message());
            }

            @Override
            public void onFailure(Call<GitHubResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
