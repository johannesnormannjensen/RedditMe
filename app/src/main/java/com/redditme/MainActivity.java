package com.redditme;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.redditme.tasks.LoginAnonymusImpl;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.oauth.OAuthException;
public class MainActivity extends AppCompatActivity {

    private RedditClient redditClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");
        TextView updateButton = (TextView) findViewById(R.id.update);
        updateButton.setTypeface(font);
        TextView newThreadButton = (TextView) findViewById(R.id.newThread);
        newThreadButton.setTypeface(font);
        TextView sideBarButton = (TextView) findViewById(R.id.sideBar);
        sideBarButton.setTypeface(font);

        // Authenticating anonymus user
        try {
            redditClient = new LoginAnonymusImpl().getAuthenticatedRedditClient();
            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show(); // REMOVE LATER
        } catch (OAuthException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "failure", Toast.LENGTH_SHORT).show();
            /*
            * IMPLEMENT MATERIAL DESIGN ERROR MESSAGE HERE
            */
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

        return super.onOptionsItemSelected(item);
    }
}
