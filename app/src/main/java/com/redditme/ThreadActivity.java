package com.redditme;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.redditme.activity.FragmentSidemenu;
import com.redditme.adapter.PostcardAdapter;
import com.redditme.fontawesome.DrawableAwesome;
import com.redditme.model.PostCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johannes on 08-12-2015.
 */
public class ThreadActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    List<PostCard> postCardList;
    private RecyclerView content;
    private PostcardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.postCardList = new ArrayList<PostCard>();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        String gsonPostCard = getIntent().getStringExtra("postCard");
        Gson gS = new Gson();
        PostCard postCardObj = gS.fromJson(gsonPostCard, PostCard.class);
        populatePostCardList(postCardObj);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        content = (RecyclerView)findViewById(R.id.rv_postcards);
//        content.setHasFixedSize(true);
//        adapter = new PostcardAdapter(postCardList);
//        content.setAdapter(adapter);
//        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
//        content.setLayoutManager(llm);
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

        if(id == R.id.action_refresh){
//            Here we need to refresh the current subreddit
            Toast.makeText(getApplicationContext(), "Refreshing", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populatePostCardList(PostCard postcard) {
        this.postCardList.add(postcard);
    }
}
