package com.redditme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.collect.FluentIterable;
import com.redditme.R;

import net.dean.jraw.models.CommentNode;
import net.dean.jraw.models.TraversalMethod;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Johannes on 06-12-2015.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private CommentNode commentNode;
    private Context context;
    FluentIterable<CommentNode> iterable;

    public CommentAdapter(CommentNode commentNode, Context context) {
        this.commentNode = commentNode;
        this.context = context;
        this.iterable = commentNode.walkTree(TraversalMethod.BREADTH_FIRST);
    }

    @Override
    public int getItemCount() {
        return commentNode.getImmediateSize();
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment, viewGroup, false);
        CommentViewHolder pvh = new CommentViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(CommentViewHolder commentViewHolder, int position) {
        commentViewHolder.commentAuthor.setText(commentNode.get(position).getComment().getAuthor());
        commentViewHolder.commentRepliesCount.setText("Replies: " + commentNode.get(position).getChildren().size());
        commentViewHolder.commentDescription.setText(commentNode.get(position).getComment().getBody());
        Linkify.addLinks(commentViewHolder.commentDescription, Linkify.WEB_URLS);
    }

    private void dealwithurl(CommentViewHolder commentViewHolder, String comment) {
//            split string with spaces as delimitor
        String [] parts = comment.split("\\s+");
//            this is the string put back together to form HTML
        String putBackTogether = new String();
//            try to convert items into URL.
        for( String item : parts ) {
            try {
                URL url = new URL(item);
//                 If possible then replace with anchor...
                putBackTogether += "<font color=#cc0029>" + url + "</font>";
                System.out.println("this is a url: " + item);
            } catch (MalformedURLException e) {
                // If there was an URL that was not it!...
                putBackTogether += item + " ";
            }
        }
        commentViewHolder.commentDescription.setText(Html.fromHtml(putBackTogether));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView commentAuthor;
        TextView commentDescription;
        TextView commentRepliesCount;
        ImageView commentURLThumbnail;

        CommentViewHolder(View itemView) {
            super(itemView);
            commentAuthor = (TextView)itemView.findViewById(R.id.comment_author);
            commentDescription = (TextView)itemView.findViewById(R.id.comment_selftext);
            commentRepliesCount = (TextView)itemView.findViewById(R.id.comment_repliescount);
//            commentURLThumbnail = (ImageView)itemView.findViewById(R.id.post_thumbnail);
        }
    }
}
