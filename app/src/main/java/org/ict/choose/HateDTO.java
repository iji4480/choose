package org.ict.choose;

public class HateDTO {
    String id;
    int hate;
    public HateDTO(int hate, String id){
        this.hate = hate;
        this.id = id;
    }

    public int getHate(){
        return this.hate;
    }

    public void setHate(int hate) {
        this.hate = hate;

    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
