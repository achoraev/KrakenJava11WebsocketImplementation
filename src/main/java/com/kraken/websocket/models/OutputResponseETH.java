package com.kraken.websocket.models;

import java.time.LocalDateTime;
import java.util.*;

public class OutputResponseETH implements OutputResponse {
    private final String PAIR = "ETC/USD";

    public OutputResponseETH() {
    }

    TreeMap<Float, Float> asks = new TreeMap<>(Collections.reverseOrder());

    TreeMap<Float, Float> bestBid;
    TreeMap<Float, Float> bestAsk;

    TreeMap<Float, Float> bids = new TreeMap<>(Collections.reverseOrder());

    LocalDateTime timeStamp;

    public TreeMap<Float, Float> getAsks() {
        return asks;
    }

    public void setAsks(TreeMap<Float, Float> asks) {
        this.asks = asks;
    }

    public TreeMap<Float, Float> getBestBid() {
        return bestBid;
    }

    public void setBestBid(TreeMap<Float, Float> bestBid) {
        this.bestBid = bestBid;
    }

    public TreeMap<Float, Float> getBestAsk() {
        return bestAsk;
    }

    public void setBestAsk(TreeMap<Float, Float> bestAsk) {
        this.bestAsk = bestAsk;
    }

    public TreeMap<Float, Float> getBids() {
        return bids;
    }

    public void setBids(TreeMap<Float, Float> bids) {
        this.bids = bids;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPair() {
        return PAIR;
    }


    public String printResponse() {
        StringBuilder result = new StringBuilder();

        result.append("<------------------------------------>\n");
        result.append("asks:\n").append("[ ");
        for (Map.Entry<Float, Float> map : this.getAsks().entrySet()) {
            result.append("[ " + map.getKey() + ", " + map.getValue() + " ],\n");
        }

        result.delete(result.length() - 2, result.length());
        result.append(" ]\n");

        Map.Entry<Float, Float> bestBid = getBestBid().firstEntry();
        Map.Entry<Float, Float> bestAsk = getBestAsk().firstEntry();

        result.append("best bid: [ " + bestBid.getKey() + ", " + bestBid.getValue() + " ]\n");
        result.append("best ask: [ " + bestAsk.getKey() + ", " + bestAsk.getValue() +" ]\n");

        result.append("bids:\n").append("[ ");
        for (Map.Entry<Float, Float> map : this.getBids().entrySet()) {
            result.append("[ " + map.getKey() + ", " + map.getValue() + " ],\n");
        }

        result.delete(result.length() - 2, result.length());
        result.append(" ]\n");

        result.append(getTimeStamp() + "\n");
        result.append(getPair() + "\n");
        result.append("<------------------------------------>\n");

        return result.toString();
    }
}