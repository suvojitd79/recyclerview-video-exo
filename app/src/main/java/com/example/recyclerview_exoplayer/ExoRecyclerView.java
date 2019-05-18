package com.example.recyclerview_exoplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

public class ExoRecyclerView extends RecyclerView {


    private int current = -1;
    private LinearLayoutManager linearLayoutManager;
    private MainActivity mainActivity;


    public ExoRecyclerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ExoRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExoRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(final Context context) {

        mainActivity = (MainActivity) context;

        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                    //gives the attached layout-manager
                    linearLayoutManager = (LinearLayoutManager) getLayoutManager();


                    if (!recyclerView.canScrollVertically(-1)) {

                        current = 0;
                        Log.d(MainActivity.TAG, "top ");

                    } else if (!recyclerView.canScrollVertically(1)) {

                        current = linearLayoutManager.getItemCount() - 1;
                        Log.d(MainActivity.TAG, "bottom ");

                    } else {

                        current = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                        Log.d(MainActivity.TAG, "middle ");
                    }

                    mainActivity.playVideo(current);

                }


            }

        });


    }


}
