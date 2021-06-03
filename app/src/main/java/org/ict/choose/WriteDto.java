package org.ict.choose;

import java.util.Dictionary;

public class WriteDto {

    private String contents;
    private String writer;
    private double like;
    private double hate;

    public WriteDto(String contents, String writer, double like, double hate){

        this.contents = contents;
        this.writer = writer;
        this.like = like;
        this.hate = hate;
    }



    public String getContents(){
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;


    }
    public String getWriter(){
        return this.writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;

    }
    public double getLike() {
        return like;
    }

    public void setLike(double like) {
        this.like = like;
    }

    public double getHate() {
        return hate;
    }

    public void setHate(double hate) {
        this.hate = hate;
    }
}
