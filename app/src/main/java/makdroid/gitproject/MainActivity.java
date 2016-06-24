package makdroid.gitproject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import makdroid.gitproject.adapters.RecyclerItemClickListener;
import makdroid.gitproject.adapters.ResponseGithubAdapter;
import makdroid.gitproject.model.GitHubResponse;
import makdroid.gitproject.model.Item;
import makdroid.gitproject.services.GithubService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final int TRIGGER_SEARCH = 1;
    private final long SEARCH_TRIGGER_DELAY_IN_MS = 1000;
    private final String SEARCH_QUERY = "SEARCH_QUERY";

    @Inject
    GithubService githubService;

    @Bind(R.id.editTextSearch)
    EditText mEditTextSearch;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.pb_loading)
    ProgressBar mPbarLoading;

    private int mResponseCounts = 0;
    private ResponseGithubAdapter adapter;
    private List<Item> mItemListResponse = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeDependencyInjector();
        initEditTextSearch();
        initRecyclerView();
    }

    private void initializeDependencyInjector() {
        GitApplication application = (GitApplication) getApplication();
        application.getNetComponent().inject(this);
    }

    void initEditTextSearch() {
        mEditTextSearch.addTextChangedListener(searchWatcher);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext()) {
            @Override
            public void onItemClicked(MotionEvent e) {

            }
        });
        List<Item> mItemList = new ArrayList<>();
        adapter = new ResponseGithubAdapter(mItemList, getApplicationContext());
        mRecyclerView.setAdapter(adapter);
    }

    private final TextWatcher searchWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        public void afterTextChanged(Editable s) {
            Bundle b = new Bundle();
            handler.removeMessages(TRIGGER_SEARCH);
            Message msg = new Message();
            msg.what = TRIGGER_SEARCH;
            b.putString(SEARCH_QUERY, s.toString());
            msg.setData(b);
            handler.sendMessageDelayed(msg, SEARCH_TRIGGER_DELAY_IN_MS);
        }
    };

    private void requestJSON(String query) {
        if (TextUtils.isEmpty(query)) {
            adapter.clearItems();
        } else {
            showProgressBar();
            Call<GitHubResponse> getRepos = githubService.getReposByName(query);
            getRepos.enqueue(new Callback<GitHubResponse>() {
                @Override
                public void onResponse(Call<GitHubResponse> call, Response<GitHubResponse> response) {
                    GitHubResponse jsonResponse = response.body();
                    mItemListResponse.addAll(jsonResponse.items);
                    mResponseCounts++;
                    Log.v("success Repo", response.message());
                    checkReponseCounts();
                }

                @Override
                public void onFailure(Call<GitHubResponse> call, Throwable t) {
                    Log.v("Error", t.getMessage());
                }
            });

            Call<GitHubResponse> getUsers = githubService.getUsersByName(query);
            getUsers.enqueue(new Callback<GitHubResponse>() {
                @Override
                public void onResponse(Call<GitHubResponse> call, Response<GitHubResponse> response) {
                    GitHubResponse jsonResponse = response.body();
                    mItemListResponse.addAll(jsonResponse.items);
                    mResponseCounts++;
                    Log.v("success User", response.message());
                    checkReponseCounts();
                }

                @Override
                public void onFailure(Call<GitHubResponse> call, Throwable t) {
                    Log.v("Error", t.getMessage());
                }
            });
        }
    }

    private void checkReponseCounts() {
        Log.v("checkReponseCounts", "checkReponseCounts " + mResponseCounts);
        if (mResponseCounts == 2) {
            mResponseCounts = 0;
            if (mItemListResponse.size() > 0) {
                Collections.sort(mItemListResponse);
                adapter.clearItems();
                adapter.addItems(mItemListResponse);
                mItemListResponse.clear();
            }
            hideProgressBar();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == TRIGGER_SEARCH) {
                Bundle data = msg.getData();
                requestJSON(data.getString(SEARCH_QUERY));
            }
        }
    };

    private void showProgressBar() {
        this.mPbarLoading.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        this.mPbarLoading.setVisibility(View.GONE);
    }

}
