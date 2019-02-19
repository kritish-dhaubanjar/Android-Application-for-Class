package com.charoniv.jin.charon.datasource;

public class Resource {

    private String title, date, subject, filename, extension, url, type;
    private int sn;

    public Resource(String title, String date, String subject, String filename, String extension, String url, int sn, String type) {
        this.title = title;
        this.date = date;
        this.subject = subject;
        this.filename = filename;
        this.extension = extension;
        this.url = url;
        this.sn = sn;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public String getFilename() {
        return filename;
    }

    public String getExtension() {
        return extension;
    }

    public String getUrl() {
        return url;
    }

    public int getSn() {
        return sn;
    }
}
