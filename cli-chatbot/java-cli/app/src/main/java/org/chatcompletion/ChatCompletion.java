package org.chatcompletion;

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

  public void askAi(String content) {
    this.AIcontext.addMessage(new Message("user", content));
    String request = toJson();
    System.out.println(request);
    String response = AIConnector.getInstance().callAPI(request);
    System.out.println(response);
  }

  public void aiAnswer(String content) {
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