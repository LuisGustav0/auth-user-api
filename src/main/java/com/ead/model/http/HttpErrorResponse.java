package com.ead.model.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpErrorResponse {

    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("message")
    private String message;

    private List<HttpErrorResponse> erros;

    public void addFieldError(final String field, final String defaultMessage) {
        if(erros == null) {
            this.erros = new ArrayList<>();
        }

        erros.add(HttpErrorResponse.builder().errorCode(field).message(defaultMessage).build());
    }
}
