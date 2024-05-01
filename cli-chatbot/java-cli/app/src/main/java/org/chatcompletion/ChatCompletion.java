package org.chatcompletion;

import org.ResponseEntitity.Message;
import org.aiconnection.AIConnector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

/**
 * handles the ongoing chat with the AI
 */
@Getter
public class ChatCompletion {
  private AIContext AIcontext;

  public ChatCompletion() {
    this.AIcontext = new AIContext();
  }

  public String askAi(String content) {
    this.AIcontext.addMessage(new Message("user", content));
    String request = toJson();
    String response = AIConnector.getInstance().callAPI(request);
    this.aiAnswer(response);
    return response;
  }

  private void aiAnswer(String content) {
    this.AIcontext.addMessage(new Message("system", content));
  }

  private String toJson() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(this.AIcontext);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }
}