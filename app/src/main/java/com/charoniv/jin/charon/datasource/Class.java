package com.charoniv.jin.charon.datasource;

public class Class {

    private String start, end, type, subject, instructor;

    public Class(String start, String end, String type, String subject, String instructor) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.subject = subject;
        this.instructor = instructor;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getType() {
        return type;
    }

    public String getSubject() {
        return subject;
    }

    public String getInstructor() {
        return instructor;
    }
}
