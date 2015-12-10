package com.redditme;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.google.gson.Gson;
import com.redditme.activity.FragmentSidemenu;
import com.redditme.adapter.PostcardAdapter;
import com.redditme.fontawesome.DrawableAwesome;
import com.redditme.model.PostCard;
import com.redditme.redditservice.AsyncRedditClient;
import com.redditme.redditservice.RedditService;

import net.dean.jraw.models.Submission;
import net.dean.jraw.models.meta.SubmissionSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johannes on 08-12-2015.
 */
public class ThreadActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private Submission submission;
    private RecyclerView content;
    private PostcardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        ObjectMapper mapper = new ObjectMapper();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        String imback = getIntent().getStringExtra("theSubmission");
        JsonNode backAgain = null;
        try {
            backAgain = mapper.readTree(imback);
        } catch (IOException e) {
            e.printStackTrace();
        }

        submission = new Submission(backAgain);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RelativeLayout rL = (RelativeLayout) findViewById(R.id.cv_relative_layout);
        rL.setOnClickListener(null);
        TextView pc_title = (TextView) findViewById(R.id.post_title);
        pc_title.setText(submission.getTitle());
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

    private void setSubmission(Submission submission) {
        this.submission= submission;
    }
}
