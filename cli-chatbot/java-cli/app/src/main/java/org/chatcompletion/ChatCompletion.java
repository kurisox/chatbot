package org.chatcompletion;

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
  }

  public void aiAnswer(String content) {
    this.AIcontext.addMessage(new Message("system", content));
  }

  public String toJson() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(this.AIcontext);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }
}