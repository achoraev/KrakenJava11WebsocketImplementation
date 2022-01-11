package com.kraken.websocket;

import com.kraken.websocket.listener.WebSocketListenerImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CountDownLatch;

public class TestWebsocket {

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        URI uri = new URI("wss://ws.kraken.com/");

        CountDownLatch latch = new CountDownLatch(10);

        WebSocket ws = HttpClient
                .newHttpClient()
                .newWebSocketBuilder()
                .buildAsync(uri, new WebSocketListenerImpl(latch))
                .join();

        latch.await();
    }
}