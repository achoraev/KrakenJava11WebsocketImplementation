package com.kraken.websocket.listener;

import com.kraken.websocket.impl.WebSocketImpl;
import com.kraken.websocket.utils.WebsocketsUtil;

import java.net.http.WebSocket;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CountDownLatch;

public class WebSocketListenerImpl extends WebSocketImpl implements WebSocket.Listener {
    private final CountDownLatch latch;
    private boolean isSubscribed;

    public WebSocketListenerImpl(CountDownLatch latch) { this.latch = latch; }

    @Override
    public void onOpen(WebSocket webSocket) {
        System.out.println("Websocket is open.");
        WebSocket.Listener.super.onOpen(webSocket);
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        System.out.println("onText received " + data + " current time: " + System.currentTimeMillis() + " ms.");
        latch.countDown();

        if(!webSocket.isOutputClosed()) {
            WebsocketsUtil.parseResponse(data);
            if (!isSubscribed){
                new WebsocketsUtil().subscribe(webSocket);
                isSubscribed = true;
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return WebSocket.Listener.super.onText(webSocket, data, last);
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        System.out.println("onError " + error.getMessage());
        error.printStackTrace();
        WebSocket.Listener.super.onError(webSocket, error);
    }

    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        System.out.println("Connection closed. Code: " + statusCode + " Reason: " + reason);
        return null;
    }
}