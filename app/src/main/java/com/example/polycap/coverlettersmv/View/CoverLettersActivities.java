package com.example.polycap.coverlettersmv.View;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.polycap.coverlettersmv.Presenter.AppContext;
import com.example.polycap.coverlettersmv.Presenter.YouTubeAdapter;
import com.example.polycap.coverlettersmv.Presenter.YouTubedata;
import com.example.polycap.coverlettersmv.Presenter.YouTubepresenter;
import com.example.polycap.coverlettersmv.R;
import com.example.polycap.coverlettersmv.model.VideoItem;

import java.util.List;

public class CoverLettersActivities extends ActionBarActivity implements YouTubedata, SwipeRefreshLayout.OnRefreshListener, NavigationView.OnNavigationItemSelectedListener {

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

        youTubepresenter = new YouTubepresenter(this);
        youTubepresenter.searchOnYoutube(getString(R.string.mindvalleyCL));
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe2Refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        setDrawerLayout();
        setRecyclerView();


    }

    private void setDrawerLayout(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mActionBarToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setRecyclerView(){
        adapter = new YouTubeAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(AppContext.getContext(), 1, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void youtubeData(List<VideoItem> searchResults) {
        adapter.setSearchResults(searchResults);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRefresh() {
        youTubepresenter.searchOnYoutube(getString(R.string.mindvalleyCL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
