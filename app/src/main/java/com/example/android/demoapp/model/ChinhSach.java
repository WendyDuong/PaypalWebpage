package com.example.android.demoapp.model;

import java.io.Serializable;

public class ChinhSach implements Serializable {
    private int id;
    private String tenchinhsach, noidung, tenchinhsachDE, noidungDE;

    public ChinhSach(int id, String tenchinhsach, String noidung, String tenchinhsachDE, String noidungDE) {
        this.id = id;
        this.tenchinhsach = tenchinhsach;
        this.noidung = noidung;
        this.tenchinhsachDE = tenchinhsachDE;
        this.noidungDE = noidungDE;
    }

    public String getNoidungDE() {
        return noidungDE;
    }

    public void setNoidungDE(String noidungDE) {
        this.noidungDE = noidungDE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenchinhsach() {
        return tenchinhsach;
    }

    public void setTenchinhsach(String tenchinhsach) {
        this.tenchinhsach = tenchinhsach;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getTenchinhsachDE() {
        return tenchinhsachDE;
    }

    public void setTenchinhsachDE(String tenchinhsachDE) {
        this.tenchinhsachDE = tenchinhsachDE;
    }
}
