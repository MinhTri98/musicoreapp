package com.example.splashscreen;

public class song {
    private String title,singer;
    private  int file;

    public song(String title, String singer, int file) {
        this.title = title;
        this.singer = singer;
        this.file = file;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }


}
