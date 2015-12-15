package com.redditme;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.redditme.adapter.CommentAdapter;
import com.redditme.adapter.PostcardAdapter;
import com.redditme.model.SelectedSubmission;
import com.redditme.redditservice.AsyncRedditClient;
import com.redditme.redditservice.RedditService;
import com.redditme.utils.ThumbnailGenerator;

import net.dean.jraw.models.Submission;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Johannes on 08-12-2015.
 */
public class ThreadActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Activity threadActivity;

    private ThumbnailGenerator thumbnailGenerator;

    private Submission submission;
    private Toolbar mToolbar;
    private RecyclerView content;
    private CommentAdapter adapter;
    final RedditService redditClient = AsyncRedditClient.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        String subId = getIntent().getStringExtra("subId");
        redditClient.loadSubmissionById(subId);

//        Log.d(TAG, submission.getComments().get(0).getComment().getBody());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        this.threadActivity = (Activity) this;
        this.thumbnailGenerator = new ThumbnailGenerator(this);
        RelativeLayout rL = (RelativeLayout) findViewById(R.id.selected_submission_relative_layout);
        submission = redditClient.getSelectedSubmission();
        SelectedSubmission selectedSubmission = new SelectedSubmission(rL, submission, getApplicationContext());

        content = (RecyclerView) findViewById(R.id.rv_comments);
        content.setHasFixedSize(true);
        adapter = new CommentAdapter(submission.getComments(), getApplicationContext());
        content.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        content.setLayoutManager(llm);

    }

    Drawable drawable_from_url(String url, String src_name) throws
            java.net.MalformedURLException, java.io.IOException {
        return Drawable.createFromStream((java.io.InputStream)
                new java.net.URL(url).getContent(), src_name);
    }

    @Override
    public void startActivity(Intent intent) {
        if (TextUtils.equals(intent.getAction(), Intent.ACTION_VIEW)) {
            System.out.println("clicked");
            Drawable dr = null;
            try {
                thumbnailGenerator.execute(intent.getDataString()).get(12, TimeUnit.SECONDS);
                dr = thumbnailGenerator.gimmeTheThumbnail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } finally {
                if (dr == null) {
                    startActivity(intent, null);
                    return;
                }
            }

            AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

            View layout = inflater.inflate(R.layout.full_image,
                    (ViewGroup) findViewById(R.id.image_root));
            ImageView image = (ImageView) layout.findViewById(R.id.fullimageView);
            image.setImageDrawable(dr);
            imageDialog.setView(layout);
            imageDialog.setPositiveButton("sheet", new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }

            });


            imageDialog.create();
            imageDialog.show();

        } else {
            System.out.println("not clicked");
        }
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

        if (id == R.id.action_refresh) {
//            Here we need to refresh the current subreddit
            Toast.makeText(getApplicationContext(), "Refreshing", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
