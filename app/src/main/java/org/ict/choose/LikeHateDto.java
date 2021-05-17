package org.ict.choose;

public class LikeHateDto {
    String like;
    String hate;

    public LikeHateDto(String like, String hate){
        this.like = like;
        this.hate = hate;
    }

    public String getLike(){
        return this.like;
    }

    public void setContent(String like) {
        this.like = like;

    }
    public String getHate(){
        return this.hate;
    }

    public void setWriter(String hate) {
        this.hate = hate;

    }


}
