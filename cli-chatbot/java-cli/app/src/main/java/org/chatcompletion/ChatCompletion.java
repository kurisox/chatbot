package org.chatcompletion;

import org.ResponseEntitity.Message;
import org.ResponseEntitity.ResponseEntitity;
import org.aiconnection.AIConnector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

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

  /**
   * Asks the AI for a response to the given content. If the context is too large, the context will be summarized before the answer.
   *
   * @param  content  the content to ask the AI about
   * @return          the response message from the AI
   */
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

  /**
   * Summarizes the context of the conversation by asking the AI to create a short summary.
   *
   * @param  request        the JSON representation of the AI context
   * @param  responseMessage the response message from the AI
   */
  private void summarizeContext(String request, String responseMessage) {
    this.AIcontext.addMessage(new Message("user", "Can you create a short summary of the conversation?"));
    request = toJson();
    ResponseEntitity response = AIConnector.getInstance().callAPI(request);
    responseMessage = evaluateResponse(response, true);
    this.AIcontext.resetContent(responseMessage);
    aiAnswer(responseMessage);
  }

  /**
   * Evaluates the response from the AI and updates the tokens accordingly.
   *
   * @param  response     the response entity from the AI
   * @param  tokenOverflow indicates if the token overflow occurred
   * @return              the response message from the AI
   */
  private String evaluateResponse(ResponseEntitity response, boolean tokenOverflow) {
    updateTokens(response, tokenOverflow);
    return getResponseMessage(response);
  }

  /**
   * Updates the tokens based on the response and token overflow flag.
   *
   * @param  response       the response entity containing information about the tokens
   * @param  tokenOverflow  a flag indicating if the token overflow has occurred. if true, the tokens will be set to the completion tokens, else the tokens will be set to the total tokens
   */
  private void updateTokens(ResponseEntitity response, boolean tokenOverflow) {
    this.tokens = tokenOverflow ? response.getUsage().getCompletionTokens() : response.getUsage().getTotalTokens();
  }

  /**
   * Retrieves the response message from the given ResponseEntity object.
   *
   * @param  response  the ResponseEntity object containing the response choices
   * @return           the content of the first message in the response choices
   */
  private String getResponseMessage(ResponseEntitity response) {
    return response.getChoices()[0].getMessage().getContent();
  }

  

    /**
     * Adds a new message to the AI context with the role "system" and the provided content.
     *
     * @param  content  the content of the message to be added
     */
  private void aiAnswer(String content) {
    this.AIcontext.addMessage(new Message("system", content));
  }

  /**
   * Converts the AI context to a JSON string 
   *
   * @return The JSON string representation of the AI context object, or null if an error occurs during serialization.
   */
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