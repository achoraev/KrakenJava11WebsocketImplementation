package com.kraken.websocket.utils;

import com.google.gson.Gson;
import com.kraken.websocket.models.*;

import java.net.http.WebSocket;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

public class WebsocketsUtil {

    private static int channelIdETH;
    private static int channelIdBTC;
    private static OutputResponseETH outputResponseETH = new OutputResponseETH();
    private static OutputResponseBTC outputResponseBTC = new OutputResponseBTC();

    public void subscribe(WebSocket webSocket) {
        String json = new Gson().toJson(new Subscription());
        webSocket.sendText(json, true);
    }

    public static void parseResponse(CharSequence data) {
        String dataString = data.toString();

        if (dataString.startsWith("{\"channel")){
            SubscriptionResponse subscriptionResponse = new Gson().fromJson(dataString, SubscriptionResponse.class);

            if (subscriptionResponse.getPair().equals("ETH/USD")){
                channelIdETH = subscriptionResponse.getChannelID();
            } else if(subscriptionResponse.getPair().equals("TBTC/USD")){
                channelIdBTC = subscriptionResponse.getChannelID();
            }
        } else if(dataString.startsWith("[")){
            int index = dataString.indexOf(",");
            int channelID = Integer.parseInt(dataString.substring(1, index));
            if (channelID == channelIdETH){
                Response response = processETHresponse(dataString);
                OutputResponse outputResponse = customMapResponse(response, outputResponseETH);
                //                System.out.println(new Gson().toJson(outputResponse));
                System.out.println(outputResponse.printResponse());
            } else if (channelID == channelIdBTC) {
                Response response = processBTCresponse(dataString);
                OutputResponse outputResponse = customMapResponse(response, outputResponseBTC);
                //                System.out.println(new Gson().toJson(outputResponse));
                System.out.println(outputResponse.printResponse());
            }
        }
    }

    private static Response processBTCresponse(String json) {
        String response = json.substring(1);
        int lastIndex = response.lastIndexOf("]");
        response = response.substring(0, lastIndex);
        lastIndex = response.lastIndexOf("}") + 1;
        int firstIndex = response.indexOf("{");
        Response res = new Gson().fromJson(response.substring(firstIndex, lastIndex), Response.class);
        return res;

    }

    private static Response processETHresponse(String json) {
        String response = json.substring(1);
        int lastIndex = response.lastIndexOf("]");
        response = response.substring(0, lastIndex);
        lastIndex = response.lastIndexOf("}");
        Response res = new Gson().fromJson(response.substring(4, lastIndex+1), Response.class);
        return res;
    }

    private static OutputResponse customMapResponse(Response response1, OutputResponse outputResponse) {

        outputResponse.getAsks().put(response1.getA().get(0), response1.getA().get(2));
        outputResponse.getBids().put(response1.getB().get(0), response1.getB().get(2));

        // best bid
        Map.Entry<Float, Float> first = outputResponse.getBids().firstEntry();
        TreeMap<Float, Float> firstEntry = new TreeMap<>();
        firstEntry.put(first.getKey(), first.getValue());

        // best ask
        Map.Entry<Float, Float> last = outputResponse.getAsks().lastEntry();
        TreeMap<Float, Float> lastEntry = new TreeMap<>();
        lastEntry.put(last.getKey(), last.getValue());

        outputResponse.setBestAsk(lastEntry);
        outputResponse.setBestBid(firstEntry);

        outputResponse.setTimeStamp(LocalDateTime.now());
        return outputResponse;
    }
}