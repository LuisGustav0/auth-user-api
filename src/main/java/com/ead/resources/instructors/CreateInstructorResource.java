package com.ead.resources.instructors;

import com.ead.model.request.instructors.InstructorRequest;
import com.ead.model.response.instructors.InstructorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CreateInstructorResource {

    @PostMapping("/instructors/subscription")
    public ResponseEntity<InstructorResponse> call(@RequestBody @Valid InstructorRequest request) {
        log.info("POST CreateInstructorResource.call Request: {}", request);

        final InstructorResponse response = this.service.call(request);

        log.info("POST CreateInstructorResource.call Response: {}", response);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
