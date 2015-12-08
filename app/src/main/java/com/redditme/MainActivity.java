package com.redditme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.redditme.activity.FragmentSidemenu;
import com.redditme.activity.ItemFragment;
import com.redditme.adapter.PostcardAdapter;
import com.redditme.fontawesome.DrawableAwesome;
import com.redditme.model.PostCard;
import com.redditme.redditservice.AsyncRedditClient;
import com.redditme.redditservice.RedditService;

import net.dean.jraw.models.Submission;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements FragmentSidemenu.FragmentDrawerListener {

    private Toolbar mToolbar;
    private FragmentSidemenu sidemenuFragment;
    private List<PostCard> postCardList;
    private RecyclerView content;
    private PostcardAdapter adapter;

    final RedditService redditService = new AsyncRedditClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        redditService.authenticateUserless();
        this.postCardList = new ArrayList<PostCard>();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sidemenuFragment = (FragmentSidemenu) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        sidemenuFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        sidemenuFragment.setDrawerListener(this);

        content = (RecyclerView) findViewById(R.id.rv_postcards);
        content.setHasFixedSize(true);
        adapter = new PostcardAdapter(postCardList);
        content.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        content.setLayoutManager(llm);

        // display the first navigation drawer view on app launch
        displayView(0);
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

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        String[] labels = getResources().getStringArray(R.array.nav_drawer_labels);
        String title = labels[position];
        Fragment fragment = new ItemFragment();

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

//            populate postcardlist
            populatePostCardList(this.postCardList, position);
            adapter.notifyDataSetChanged();

//            set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    private void populatePostCardList(List<PostCard> postCardList, int subreddit) {
        if (!postCardList.isEmpty()) {
            postCardList.clear();
        }
        redditService.loadSubmissions();
        List<Submission> submissions = redditService.getCurrentSubmissions();
        for (int i = 0; i < submissions.size(); i++) {
            Submission subm = submissions.get(i);
            postCardList.add(new PostCard(subm.getTitle(), subm.getSelftext(), subm.getCommentCount(), new DrawableAwesome.DrawableAwesomeBuilder(getApplicationContext(), R.string.fa_file, 60).build()));
        }
    }
}
