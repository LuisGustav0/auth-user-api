package com.ead.model.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpErrorResponse {

    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("message")
    private String message;
}
