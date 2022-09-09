package com.ead.model.response.usercourse;

import com.ead.model.response.CourseResponse;
import com.ead.model.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionUserInCurseResponse {

    private UUID id;
    private UserResponse user;
    private CourseResponse course;
}
