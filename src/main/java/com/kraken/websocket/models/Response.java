package com.kraken.websocket.models;

import java.util.List;

public class Response {
    //    ask
    List<Float> a;
    //bid
    List<Float> b;
    //close
    List<Float> c;
    //volume
    List<Float> v;
    //Volume weighted average price
    List<Float> p;
    //number of trades
    List<Float> t;
    //Low price
    List<Float> l;
    //high price
    List<Float> h;
    //open price
    List<Float> o;

    public List<Float> getA() {
        return a;
    }

    public List<Float> getB() {
        return b;
    }

    public List<Float> getC() {
        return c;
    }

    public List<Float> getV() {
        return v;
    }

    public List<Float> getP() {
        return p;
    }

    public List<Float> getT() {
        return t;
    }

    public List<Float> getL() {
        return l;
    }

    public List<Float> getH() {
        return h;
    }

    public List<Float> getO() {
        return o;
    }
}