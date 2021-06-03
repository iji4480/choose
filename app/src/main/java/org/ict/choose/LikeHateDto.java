package org.ict.choose;


import androidx.annotation.NonNull;

public class LikeHateDto {
    double like;
    String id;


    public LikeHateDto(double like, String id){
        this.like = like;
        this.id = id;
    }

    public double getLike(){
        return this.like;
    }

    public void setLike(double like) {
        this.like = like;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }



}
