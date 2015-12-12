package com.redditme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.redditme.adapter.PostcardAdapter;
import com.redditme.model.SelectedSubmission;
import com.redditme.model.SelectedSubmissionGenerator;
import com.redditme.redditservice.AsyncRedditClient;
import com.redditme.redditservice.RedditService;

import net.dean.jraw.models.Submission;

/**
 * Created by Johannes on 08-12-2015.
 */
public class ThreadActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private RecyclerView content;
    private PostcardAdapter adapter;
    final RedditService redditClient = AsyncRedditClient.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        String subId= getIntent().getStringExtra("subId");
        redditClient.loadSubmissionById(subId);

//        Log.d(TAG, submission.getComments().get(0).getComment().getBody());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RelativeLayout rL = (RelativeLayout) findViewById(R.id.selected_submission_relative_layout);
        rL.setOnClickListener(null);
        Submission submission = redditClient.getSelectedSubmission();
        SelectedSubmission selectedSubmission = new SelectedSubmissionGenerator(rL, submission).build();
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
}
