package org.ResponseEntitity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@JsonPropertyOrder({ "prompt_tokens", "completion_tokens", "total_tokens"})
public class Usage {
    @JsonProperty("prompt_tokens")
        private int promptTokens;
        
        @JsonProperty("completion_tokens")
        private int completionTokens;
        
        @JsonProperty("total_tokens")
        private int totalTokens;

        public Usage(int promptTokens, int completionTokens, int totalTokens) {
            this.promptTokens = promptTokens;
            this.completionTokens = completionTokens;
            this.totalTokens = totalTokens;
        }
}
