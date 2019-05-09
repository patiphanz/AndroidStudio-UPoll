package com.patiphan.upoll;

public class ViewSingleItem {

    private String Poll_Title;

    public ViewSingleItem(String poll_title) {
        Poll_Title = poll_title;
    }

    public ViewSingleItem() {

    }

    public void setPoll_title(String poll_title) { Poll_Title = poll_title; }

    public String getPoll_title() { return Poll_Title; }
}
