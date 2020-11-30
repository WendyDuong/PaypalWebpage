package com.example.android.demoapp.model;

public class Banner {
    private int id;
    private String tenbanner, anhbanner;

    public Banner(int id, String tenbanner, String anhbanner){
        this.id = id;
        this.tenbanner = tenbanner;
        this.anhbanner = anhbanner;

    }

    public String getTenbanner() {
        return tenbanner;
    }

    public void setTenbanner(String tenbanner) {
        this.tenbanner = tenbanner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnhbanner() {
        return anhbanner;
    }

    public void setAnhbanner(String anhbanner) {
        this.anhbanner = anhbanner;
    }
}
