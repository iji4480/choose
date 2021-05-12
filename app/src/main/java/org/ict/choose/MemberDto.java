package org.ict.choose;

public class MemberDto {

    String nickName;

    public MemberDto(String nickName){
        this.nickName = nickName;
    }

    public String getNickName(){
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;

    }
}
