package org.chatcompletion;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;

/**
 * handles the ongoing chat with the AI
 */
@Getter
public class ChatCompletion {
    private static ChatCompletion instance;
    private JSONArray context;

    private ChatCompletion(){
        this.context = new JSONArray();
        JSONObject model = new JSONObject();
        try {
          model.put("model", "gpt-3.5-turbo");
        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        JSONArray messages = new JSONArray();
        messages.put(Message.createMessage("system", "You are a helpful assistant."));
        this.context.put(model);
        this.context.put(messages);
}

    public static ChatCompletion getInstance() throws JSONException {
        if (instance == null) {
            instance = new ChatCompletion();
        }
        return instance;
    }

    public void addMessage(String role, String content) {
        JSONObject message = Message.createMessage(role, content);
        try {
          ChatCompletion.getInstance().getContext().getJSONArray(1).put(message);
        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }
}