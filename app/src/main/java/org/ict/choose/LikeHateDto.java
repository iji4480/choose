package org.ict.choose;

public class LikeHateDto {
    int like;
    String id;


    public LikeHateDto(int like, String id){
        this.like = like;
        this.id = id;
    }

    public int getLike(){
        return this.like;
    }

    public void setLike(int like) {
        this.like = like;
    }
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }



}
