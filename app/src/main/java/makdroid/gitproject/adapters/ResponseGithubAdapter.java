package makdroid.gitproject.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
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
        Item item = mItemCollection.get(position);
        if (holder.getItemViewType() == USER) {
            UserViewHolder viewHolder = (UserViewHolder) holder;
            viewHolder.userName.setText(item.login);
        } else {
            RepoHolder viewHolder = (RepoHolder) holder;
            viewHolder.repoName.setText(item.name);
        }
    }

    @Override
    public int getItemCount() {
        return mItemCollection.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        Item item = mItemCollection.get(position);
        if (TextUtils.isEmpty(item.name))
            viewType = USER;
        else
            viewType = REPO;
        return viewType;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    final class UserViewHolder extends ViewHolder {
        @Bind(R.id.user_name)
        TextView userName;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    final class RepoHolder extends ViewHolder {
        @Bind(R.id.repo_name)
        TextView repoName;

        public RepoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void clearItems() {
        int size = this.mItemCollection.size();
        if (size > 0) {
            this.mItemCollection.clear();
            this.notifyItemRangeRemoved(0, size);
        }
    }

    public void addItems(List<Item> items) {
        this.mItemCollection.addAll(items);
        this.notifyItemRangeInserted(0, items.size());
    }

}