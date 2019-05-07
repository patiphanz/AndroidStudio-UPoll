package com.patiphan.upoll;

public class ViewSingleItem {

    private String Poll_Title;

    public ViewSingleItem(String poll_title) {
        Poll_Title = poll_title;
    }

    public ViewSingleItem() {

    }

    public String getPoll_title() { return Poll_Title; }
}
