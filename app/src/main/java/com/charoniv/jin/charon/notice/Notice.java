package com.charoniv.jin.charon.notice;

public class Notice {

    private int _id;
    private String info, date;

    public Notice(int _id, String info, String date) {
        this._id = _id;
        this.info = info;
        this.date = date;
    }

    public int get_id() {
        return _id;
    }

    public String getInfo() {
        return info;
    }

    public String getDate() {
        return date;
    }
}
