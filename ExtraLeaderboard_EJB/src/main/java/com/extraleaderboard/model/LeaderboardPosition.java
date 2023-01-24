package com.extraleaderboard.model;

import com.extraleaderboard.model.trackmania.EntryType;

public class LeaderboardPosition implements ResponseData {
    private int time;
    private int rank;
    private String accountId;
    private EntryType entryType = EntryType.OTHER;

    public LeaderboardPosition(int time, int rank, String accountId) {
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

    public EntryType getEntryType() {
        return entryType;
    }

    public void setEntryType(EntryType entryType) {
        this.entryType = entryType;
    }
}
