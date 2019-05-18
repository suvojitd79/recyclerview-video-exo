package com.example.recyclerview_exoplayer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.util.Util;

public class ExoAdapter extends RecyclerView.Adapter<ExoViewHolder>{


    private final Media[] MEDIA_OBJECTS;

    public ExoAdapter(Media[] MEDIA_OBJECTS) {
        this.MEDIA_OBJECTS = MEDIA_OBJECTS;
    }

    @NonNull
    @Override
    public ExoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.box,viewGroup,false);
        return new ExoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExoViewHolder exoViewHolder, int i) {

        exoViewHolder.setImage(MEDIA_OBJECTS[i].getThumbnail());
        exoViewHolder.setProgressBar(View.VISIBLE);
        exoViewHolder.setText(MEDIA_OBJECTS[i].getTitle());

    }


    @Override
    public int getItemCount() {
        return MEDIA_OBJECTS.length;
    }

}
