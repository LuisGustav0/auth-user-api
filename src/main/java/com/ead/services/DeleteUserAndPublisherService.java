package com.ead.services;

import com.ead.assembler.UserModelAssembler;
import com.ead.enums.ActionTypeE;
import com.ead.model.UserModel;
import com.ead.model.request.UserEventRequest;
import com.ead.publishers.UserEventPublisher;
import com.ead.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserAndPublisherService {

    private final UserRepository repository;

    private final UserEventPublisher publisher;

    private final UserModelAssembler userModelAssembler;

    public void call(final UserModel userModel) {
        this.repository.delete(userModel);

        final UserEventRequest request = this.userModelAssembler.toUserEvent(userModel);

        this.publisher.publishUserEvent(request, ActionTypeE.DELETE);
    }
}
