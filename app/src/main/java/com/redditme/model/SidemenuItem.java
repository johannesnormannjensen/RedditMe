package com.redditme.model;

/**
 * Created by Johannes on 07-12-2015.
 */
public class SidemenuItem {
    private boolean showNotify;
    private String title;


    public SidemenuItem() {

    }

    public SidemenuItem(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}