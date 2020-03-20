package com.zetyun.driver.socket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author Created by Bing
 */
public class WebSocketSimpleClient extends WebSocketClient {

    public WebSocketSimpleClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public WebSocketSimpleClient(URI serverURI) {
        super(serverURI);
    }

    public WebSocketSimpleClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    public static void main(String[] args) throws URISyntaxException {
        WebSocketSimpleClient c = new WebSocketSimpleClient(new URI("ws://localhost:8887"));
        c.connect();
    }

    @Override
    public void onOpen(ServerHandshake handShakeData) {
        send("Hello, it is me. Test :)");
        System.out.println("Opened connection");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        // If the error is fatal then onClose will be called additionally.
        System.out.println("Error: " + ex.getMessage());
        ex.printStackTrace();
    }

}