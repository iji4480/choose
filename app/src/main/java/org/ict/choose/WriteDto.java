package org.ict.choose;

public class WriteDto {

    private String content;
    private String writer;

    public WriteDto(String content, String writer){
        this.content = content;
        this.writer = writer;
    }

    public String getContent(){
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;

    }
    public String getWriter(){
        return this.writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;

    }
}
