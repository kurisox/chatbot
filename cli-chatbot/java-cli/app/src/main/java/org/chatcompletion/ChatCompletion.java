package org.chatcompletion;

import org.ResponseEntitity.Message;
import org.ResponseEntitity.ResponseEntitity;
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
  private int max_tokens;
  private int tokens;

  public ChatCompletion() {
    this.AIcontext = new AIContext();
    this.max_tokens = 2048;
    this.tokens = 0;
  }

  public ChatCompletion(int max_tokens, String aiPersona) {
    this.AIcontext = new AIContext(aiPersona);
    this.max_tokens = max_tokens;
    this.tokens = 0;
  }

  public String askAi(String content) {
    this.AIcontext.addMessage(new Message("user", content));
    String request = toJson();
    String responseMessage = "";
    if (tokens + 200 > max_tokens) {
      summarizeContext(request, responseMessage);
    }
    this.AIcontext.addMessage(new Message("user", content));
    request = toJson();
    ResponseEntitity response = AIConnector.getInstance().callAPI(request);
    responseMessage = evaluateResponse(response, false);
    System.out.println("Tokens: " + this.tokens);
    return responseMessage;
  }

  private void summarizeContext(String request, String responseMessage) {
    this.AIcontext.addMessage(new Message("user", "Can you create a short summary of the conversation?"));
    request = toJson();
    ResponseEntitity response = AIConnector.getInstance().callAPI(request);
    responseMessage = evaluateResponse(response, true);
    this.AIcontext.resetContent(responseMessage);
    aiAnswer(responseMessage);
  }

  private String evaluateResponse(ResponseEntitity response, boolean tokenOverflow) {
    updateTokens(response, tokenOverflow);
    return getResponseMessage(response);
  }

  private void updateTokens(ResponseEntitity response, boolean tokenOverflow) {
    this.tokens = tokenOverflow ? response.getUsage().getCompletionTokens() : response.getUsage().getTotalTokens();
  }

  private String getResponseMessage(ResponseEntitity response) {
    return response.getChoices()[0].getMessage().getContent();
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