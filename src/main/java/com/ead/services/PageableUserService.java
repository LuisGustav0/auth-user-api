package com.ead.services;

import com.ead.assembler.UserModelAssembler;
import com.ead.model.UserModel;
import com.ead.model.filter.UserFilter;
import com.ead.repositories.UserRepository;
import com.ead.resources.response.PageUserResponse;
import com.ead.resources.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ead.services.specifications.UserModelSpec.withEmailEquals;
import static com.ead.services.specifications.UserModelSpec.withFullNameLike;
import static com.ead.services.specifications.UserModelSpec.withStatusEquals;
import static com.ead.services.specifications.UserModelSpec.withTypeEquals;
import static com.ead.services.specifications.UserModelSpec.withUserCourseId;

@Service
@RequiredArgsConstructor
public class PageableUserService {

    private final UserModelAssembler userModelAssembler;

    private final UserRepository repository;

    private Specification<UserModel> getSpecification(final UserFilter filter) {
        return withFullNameLike(filter.getFullName())
                .and(withTypeEquals(filter.getTypeE()))
                .and(withStatusEquals(filter.getStatusE()))
                .and(withEmailEquals(filter.getEmail()))
                .and(withUserCourseId(filter.getCourseId()));
    }

    public PageUserResponse call(final UserFilter filter,
                                 final Pageable pageable) {
        final Specification<UserModel> spec = this.getSpecification(filter);

        final Page<UserModel> pageUserModel = this.repository.findAll(spec, pageable);

        final List<UserResponse> listUser = this.userModelAssembler.toListResponse(pageUserModel.getContent());

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
