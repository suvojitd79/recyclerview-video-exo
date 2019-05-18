package com.example.recyclerview_exoplayer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ui.PlayerView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ExoViewHolder extends RecyclerView.ViewHolder {

    private View parent;
    private ImageView imageView;
    private PlayerView playerView;
    private TextView textView;
    private ProgressBar progressBar;

    public ExoViewHolder(@NonNull View itemView) {
        super(itemView);
        this.parent = itemView;
        this.imageView = itemView.findViewById(R.id.thumbnail);
        this.playerView = itemView.findViewById(R.id.video);
        this.textView = itemView.findViewById(R.id.title);
        this.progressBar = itemView.findViewById(R.id.progressbar);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public View getParent() {
        return parent;
    }

    public PlayerView getPlayerView() {
        return playerView;
    }

    public void setImage(String uri) {

        Picasso.get()
                .load(uri)
                .into(this.imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                        progressBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError(Exception e) {


                    }
                });


    }

    public void setText(String text) {
        this.textView.setText(text);
    }

    public void setProgressBar(Integer isPlay) {
        this.progressBar.setVisibility(isPlay);
    }
}
