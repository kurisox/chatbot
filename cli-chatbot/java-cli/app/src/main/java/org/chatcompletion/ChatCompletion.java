package org.chatcompletion;

import lombok.Getter;

/**
 * handles the ongoing chat with the AI
 */
@Getter
public class ChatCompletion {
  private String context;

  private ChatCompletion() {
    this.context = "{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"system\",\"content\":\"You are a helpful assistant.\"}";
  }
}