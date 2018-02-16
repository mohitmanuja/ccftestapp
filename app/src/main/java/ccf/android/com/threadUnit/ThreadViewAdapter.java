package ccf.android.com.threadUnit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ccf.android.com.R;
import ccf.android.com.util.Util;

/**
 * Created by admin on 15/02/18.
 */

public class ThreadViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ThreadUnit> data;
    String bodyText;


    public ThreadViewAdapter(Context context, ArrayList<ThreadUnit> arrayList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.unit_thread, parent, false);
        ThreadViewHolder holder = new ThreadViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ThreadViewHolder threadViewHolder = (ThreadViewHolder) holder;
        ThreadUnit threadUnit = data.get(position);
        bodyText = threadUnit.getPostBody();
        threadViewHolder.bodyText.setText(getBody(bodyText));
        threadViewHolder.username.setText(threadUnit.getUsername());
        threadViewHolder.dateText.setText(threadUnit.getDate());
        Glide.with(context).load(threadUnit.getPostAvatar()).into(threadViewHolder.imageProfile);
        if (findAttachment(bodyText)!=0) {
            Glide.with(context).load(Util.IMAGE_URL_PART1 + findAttachment(bodyText) + Util.IMAGE_URL_PART2).placeholder(R.drawable.loading).thumbnail(0.1f).into(threadViewHolder.bodyImage);
            threadViewHolder.bodyImage.setVisibility(View.VISIBLE);
        }


    }

    String getBody(String input) {
        String ans = input.replaceAll("\\[ATTACH=full\\][0-9]*\\[/ATTACH\\]", "");
        return ans;
    }
    int findAttachment(String input) {
        int attachmentId =0;
        Pattern MY_PATTERN = Pattern.compile("\\[ATTACH=full\\][0-9]*\\[/ATTACH\\]");
        Matcher m = MY_PATTERN.matcher(input);
        if (m.find()) {
            attachmentId = Integer.parseInt(m.group(0).replaceAll("[^0-9]", ""));

        }

        return attachmentId;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
