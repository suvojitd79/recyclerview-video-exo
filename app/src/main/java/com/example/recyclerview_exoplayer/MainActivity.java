package com.example.recyclerview_exoplayer;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.HttpUrl;

public class MainActivity extends AppCompatActivity implements PlayListener{

    @BindView(R.id.recyclerView)
    ExoRecyclerView exoRecyclerView;

    private int current = -1;
    private ExoViewHolder previous = null;

    //media-player
    private SimpleExoPlayer simpleExoPlayer;

    private Media[] media = Utils.MEDIA_OBJECTS;
    private ExoAdapter exoAdapter;
    private LinearLayoutManager linearLayoutManager;

    protected static final String TAG = "tag";


    //current
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeMedia(); //initialize the player
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        exoAdapter = new ExoAdapter(media);
        exoRecyclerView.setLayoutManager(linearLayoutManager);
        exoRecyclerView.setAdapter(exoAdapter);


        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

                switch (playbackState) {

                    case Player.STATE_BUFFERING:
                        progressBar.setVisibility(View.VISIBLE);

                    case Player.STATE_READY:
                        progressBar.setVisibility(View.GONE);

                    case Player.STATE_ENDED:
                        simpleExoPlayer.seekTo(0);
                }

            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });



    }


    public LinearLayoutManager getLinearLayoutManager(){

        return this.linearLayoutManager;

    }


    @Override
    public void playVideo(int position) {

        if (current!=position) {

            hideVideo(previous);

            ExoViewHolder exoViewHolder = (ExoViewHolder) exoRecyclerView.findViewHolderForLayoutPosition(position);
            previous = exoViewHolder;
            progressBar = exoViewHolder.getProgressBar();
            progressBar.setVisibility(View.VISIBLE);
            //Toast.makeText(this,String.valueOf(position),Toast.LENGTH_SHORT).show();
            MediaSource mediaSource = buildsource(Uri.parse(media[position].getMedia_url()));
            PlayerView playerView = exoViewHolder.getPlayerView();
            playerView.setVisibility(View.VISIBLE);
            playerView.setPlayer(simpleExoPlayer);
            simpleExoPlayer.setPlayWhenReady(true);
            simpleExoPlayer.seekTo(0);
            playerView.setUseController(false);
            current = position; //update the current

        }

    }


    private void hideVideo(ExoViewHolder exoViewHolder){

        if (exoViewHolder!= null){

            progressBar.setVisibility(View.GONE);
            exoViewHolder.getPlayerView().setVisibility(View.GONE);

        }

    }


    private MediaSource buildsource(Uri uri){

        return
               new ExtractorMediaSource.Factory
                        (new DefaultHttpDataSourceFactory("media"))
                    .createMediaSource(uri);
    }


    private void initializeMedia(){

        simpleExoPlayer = ExoPlayerFactory
                .newSimpleInstance(new DefaultRenderersFactory(this)
                    , new DefaultTrackSelector(),new DefaultLoadControl());

    }



    @Override
    protected void onPause() {
        super.onPause();
        releaseMedia();
    }


    private void releaseMedia(){

        if (simpleExoPlayer!=null){

            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }

    }







}
