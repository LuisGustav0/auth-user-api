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
public class SaveUserAndPublisherService {

    private final UserRepository repository;

    private final UserEventPublisher publisher;

    private final UserModelAssembler userModelAssembler;

    public UserModel call(final UserModel userModel) {
        final UserModel userModelSaved = this.repository.save(userModel);

        final UserEventRequest request = this.userModelAssembler.toUserEvent(userModelSaved);

        this.publisher.publishUserEvent(request, ActionTypeE.CREATE);

        return userModelSaved;
    }
}
