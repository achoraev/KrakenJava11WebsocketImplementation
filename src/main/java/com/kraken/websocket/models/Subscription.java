package com.kraken.websocket.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subscription {
    private String event = "subscribe";
    private List<String> pair = new ArrayList<>();
    private Map<String, String> subscription = new HashMap<>();

//    {"event":"subscribe", "subscription":{"name":"ticker"}, "pair":["ETH/USD"]}
//    {"event":"subscribe", "subscription":{"name":"ticker"}, "pair":["TBTC/USD"]}

    public Subscription() {
        pair.add("ETH/USD");
        pair.add("TBTC/USD");
        subscription.put("name", "ticker");
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public List<String> getPair() {
        return pair;
    }

    public void setPair(List<String> pair) {
        this.pair = pair;
    }

    public Map<String, String> getSubscription() {
        return subscription;
    }

    public void setSubscription(Map<String, String> subscription) {
        this.subscription = subscription;
    }
}