package com.kraken.websocket.models;

import java.time.LocalDateTime;
import java.util.TreeMap;

public interface OutputResponse {

    TreeMap<Float, Float> getAsks();
    TreeMap<Float, Float> getBids();

    void setAsks(TreeMap<Float, Float> asks);
    void setBids(TreeMap<Float, Float> bids);

    void setBestAsk(TreeMap<Float, Float> bestAsk);
    void setBestBid(TreeMap<Float, Float> bestBid);

    void setTimeStamp(LocalDateTime timeStamp);

    String printResponse();
}