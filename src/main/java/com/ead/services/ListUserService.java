package com.ead.services;

import com.ead.assembler.UserModelAssembler;
import com.ead.model.UserModel;
import com.ead.repositories.UserRepository;
import com.ead.resources.response.PageUserResponse;
import com.ead.resources.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListUserService {

    private final UserModelAssembler userModelAssembler;

    private final UserRepository repository;

    public PageUserResponse call(final @PageableDefault(sort = "id",
                                                        direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UserModel> pageUserModel = this.repository.findAll(pageable);

        List<UserResponse> listUser = this.userModelAssembler.toListResponse(pageUserModel.getContent());

        final int pageNumber = pageUserModel.getPageable().getPageNumber();
        final int pageSize = pageUserModel.getPageable().getPageSize();
        final int totalPages = pageUserModel.getTotalPages();
        final long totalElements = pageUserModel.getTotalElements();

        return PageUserResponse.builder()
                               .pageNumber(pageNumber)
                               .pageSize(pageSize)
                               .totalPages(totalPages)
                               .totalElements(totalElements)
                               .data(listUser)
                               .build();
    }
}
