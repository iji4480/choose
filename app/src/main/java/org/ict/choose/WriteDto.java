package org.ict.choose;

public class WriteDto {

    private String contents;
    private String writer;

    public WriteDto(String contents, String writer){
        this.contents = contents;
        this.writer = writer;
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
}
