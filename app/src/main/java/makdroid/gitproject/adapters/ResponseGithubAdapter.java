package makdroid.gitproject.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import makdroid.gitproject.R;
import makdroid.gitproject.model.Item;

/**
 * Created by Grzecho on 23.06.2016.
 */
public class ResponseGithubAdapter extends RecyclerView.Adapter<ResponseGithubAdapter.ViewHolder> {
    private List<Item> mItemCollection;

    public static final int USER = 0;
    public static final int REPO = 1;


    public ResponseGithubAdapter(List<Item> itemCollection) {
        this.mItemCollection = itemCollection;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == USER) {
            itemView = LayoutInflater.
                    from(parent.getContext()).inflate(R.layout.card_row_user, parent, false);
            return new UserViewHolder(itemView);
        } else {
            itemView = LayoutInflater.
                    from(parent.getContext()).inflate(R.layout.card_row_repo, parent, false);
            return new RepoHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return mItemCollection.size();
    }

    @Override
    public int getItemViewType(int position) {

        Item item = mItemCollection.get(position);

        return USER;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    final class UserViewHolder extends ViewHolder {

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    final class RepoHolder extends ViewHolder {

        public RepoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}