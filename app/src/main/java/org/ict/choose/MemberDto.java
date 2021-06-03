package org.ict.choose;

public class MemberDto {

    String nickName;
    String user;

    public MemberDto(String nickName, String user){
        this.nickName = nickName;
        this.user = user;
    }

    public String getNickName(){
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;

    }
    public String getUser(){
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;

    }

}
