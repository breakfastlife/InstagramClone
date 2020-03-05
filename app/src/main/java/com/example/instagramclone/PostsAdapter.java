package com.example.instagramclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> post) {
        this.context = context;
        this.posts = post;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvUserName;
        private TextView tvDesription;
        private ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDesription = itemView.findViewById(R.id.tvDescription);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            ivImage = itemView .findViewById(R.id.ivImage);
        }

        public void bind(Post post) {
            //bind post elements
            tvDesription.setText(post.getDescription());
            tvUserName.setText(post.getUser().getUsername());
            if(post.getImage() != null) {
                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
            }
        }
    }
}
