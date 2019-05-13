package com.patiphan.upoll;

public class ViewUList {
    private String Poll_Title;

    public ViewUList(String poll_title) {
        Poll_Title = poll_title;
    }

    public ViewUList() {

    }

    public void setPoll_title(String poll_title) { Poll_Title = poll_title; }

    public String getPoll_title() { return Poll_Title; }
}
