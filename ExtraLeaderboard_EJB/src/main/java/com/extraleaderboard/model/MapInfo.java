package com.extraleaderboard.model;

public class MapInfo extends ResponseData{

    String uid;
    String name;
    String author;
    int nbLaps;
    String submitter;

    //medal times
    int authorTime;
    int goldTime;
    int silverTime;
    int bronzeTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNbLaps() {
        return nbLaps;
    }

    public void setNbLaps(int nbLaps) {
        this.nbLaps = nbLaps;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public int getAuthorTime() {
        return authorTime;
    }

    public void setAuthorTime(int authorTime) {
        this.authorTime = authorTime;
    }

    public int getGoldTime() {
        return goldTime;
    }

    public void setGoldTime(int goldTime) {
        this.goldTime = goldTime;
    }

    public int getSilverTime() {
        return silverTime;
    }

    public void setSilverTime(int silverTime) {
        this.silverTime = silverTime;
    }

    public int getBronzeTime() {
        return bronzeTime;
    }

    public void setBronzeTime(int bronzeTime) {
        this.bronzeTime = bronzeTime;
    }
}
