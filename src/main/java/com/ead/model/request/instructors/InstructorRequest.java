package com.ead.model.request.instructors;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class InstructorRequest {

    @NotNull
    private UUID userId;
}
