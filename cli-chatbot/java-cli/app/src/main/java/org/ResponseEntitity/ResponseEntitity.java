package org.ResponseEntitity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "object","created","model","choices"})
public record ResponseEntitity(
    @JsonProperty("id") String id,
    @JsonProperty("object") String object,
    @JsonProperty("created") long created,
    @JsonProperty("model") String model,
    @JsonProperty("choices") Choices[] choices,
    @JsonProperty("usage") Usage usage) {   
}