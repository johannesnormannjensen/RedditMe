package com.redditme.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.redditme.MainActivity;
import com.redditme.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Johannes on 10-12-2015.
 */
public class ThumbnailGenerator extends AsyncTask <String, Void, String> {

    private Drawable thumbnail = null;
    private Context context;

    public ThumbnailGenerator (Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String sThumbnailURL = params[0];
        if (sThumbnailURL != null) {
            try {
                if(!sThumbnailURL.isEmpty()) {
                    URL thumbnailURL = new URL(sThumbnailURL);
                    InputStream is = (InputStream)thumbnailURL.getContent();
                    this.thumbnail = Drawable.createFromStream(is, "src name");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            thumbnail = context.getResources().getDrawable(R.drawable.reddit_standard);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

    }

    public Drawable gimmeTheThumbnail() {
        return this.thumbnail;
    }
}
