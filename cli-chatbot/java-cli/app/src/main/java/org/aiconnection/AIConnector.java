package org.aiconnection;

import java.net.URI;

public class AIConnector {
    private static AIConnector instance;
    private final URI API_ENDPOINT;
    private final String CONTENT_TYPE;

    private AIConnector() {
        this.API_ENDPOINT = URI.create("http://localhost:8000/v1/chat/completions");
        this.CONTENT_TYPE = "application/json";
    }

    public static AIConnector getInstance() {
        if (instance == null) {
            instance = new AIConnector();
        }
        return instance;
    }

    public void askAi(String question) {
       
    }

}
