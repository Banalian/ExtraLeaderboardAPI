package com.extraleaderboard.model;

public class LeaderboardPosition extends ResponseData {
    private int time;
    private int rank;

    private String accountId;

    public LeaderboardPosition(int time, int rank,String accountId) {
        this.time = time;
        this.rank = rank;
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
