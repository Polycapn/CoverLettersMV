package com.example.polycap.coverlettersmv.View;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.polycap.coverlettersmv.Presenter.AppContext;
import com.example.polycap.coverlettersmv.Presenter.YouTubeAdapter;
import com.example.polycap.coverlettersmv.Presenter.YouTubedata;
import com.example.polycap.coverlettersmv.Presenter.YouTubepresenter;
import com.example.polycap.coverlettersmv.R;
import com.example.polycap.coverlettersmv.model.VideoItem;

import java.util.List;

public class CoverLettersActivities extends ActionBarActivity implements YouTubedata, SwipeRefreshLayout.OnRefreshListener {

    Toolbar mActionBarToolbar;

    private YouTubeAdapter adapter;
    private RecyclerView recyclerView;
    YouTubepresenter youTubepresenter;
    protected SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_letters_activities);
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("MindValley CoverLetters");
        setSupportActionBar(mActionBarToolbar);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe2Refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        adapter = new YouTubeAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(AppContext.getContext(),2, LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);

        youTubepresenter = new YouTubepresenter(this);
        youTubepresenter.searchOnYoutube(getString(R.string.mindvalleyCL));

    }

    @Override
    public void youtubeData(List<VideoItem> searchResults) {
        adapter.setSearchResults(searchResults);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        youTubepresenter.searchOnYoutube(getString(R.string.mindvalleyCL));
    }
}
