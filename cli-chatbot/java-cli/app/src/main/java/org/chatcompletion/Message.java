package org.chatcompletion;

import org.json.JSONException;
import org.json.JSONObject;

public class Message{

    public static JSONObject createMessage(String role, String content) {
        JSONObject message = new JSONObject();
        try {
            message.put("role", role);
            message.put("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return message;
    }
}
