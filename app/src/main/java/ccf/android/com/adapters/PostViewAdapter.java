package ccf.android.com.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ccf.android.com.R;
import ccf.android.com.viewHolders.PostViewHolder;

/**
 * Created by admin on 15/02/18.
 */

public class PostViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<PostUnit> data;
    String bodyText;


    public PostViewAdapter(Context context, ArrayList<PostUnit> arrayList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.unit_post, parent, false);
        PostViewHolder holder = new PostViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PostViewHolder postViewHolder = (PostViewHolder) holder;
        postViewHolder.onBind(data.get(position));



    }



    @Override
    public int getItemCount() {
        return data.size();
    }
}
