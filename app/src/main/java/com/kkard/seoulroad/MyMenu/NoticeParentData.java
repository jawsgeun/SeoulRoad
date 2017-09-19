package com.kkard.seoulroad.MyMenu;

/**
 * Created by SuGeun on 2017-09-05.
 */

public class NoticeParentData {
    private int id;
    private String title;
    private String date;

    public NoticeParentData(int id, String title, String date) {
        this.title = title;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
