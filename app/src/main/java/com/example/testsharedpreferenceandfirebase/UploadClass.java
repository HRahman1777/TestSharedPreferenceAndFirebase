package com.example.testsharedpreferenceandfirebase;

public class UploadClass {
    private String satt ;
    private String sunn;

    public String getSatt() {
        return satt;
    }

    public void setSatt(String satt) {
        this.satt = satt;
    }

    public String getSunn() {
        return sunn;
    }

    public void setSunn(String sunn) {
        this.sunn = sunn;
    }

    public UploadClass(String satt, String sunn) {
        this.satt = satt;
        this.sunn = sunn;
    }
}
