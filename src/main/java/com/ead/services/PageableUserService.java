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
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ead.services.specifications.UserModelSpec.withEmailEquals;
import static com.ead.services.specifications.UserModelSpec.withFullNameLike;
import static com.ead.services.specifications.UserModelSpec.withStatusEquals;
import static com.ead.services.specifications.UserModelSpec.withTypeEquals;

@Service
@RequiredArgsConstructor
public class PageableUserService {

    private final UserModelAssembler userModelAssembler;

    private final UserRepository repository;

    //        var userByTypeE = new UserByTypeESpecification(filter.getTypeE());
//        var userByStatusE = new UserByStatusESpecification(filter.getStatusE());
//        var userByEmail = new UserByEmailSpecification(filter.getEmail());

//        Specification<UserModel> spec = userByTypeE.or(userByStatusE).or(userByEmail);

//        Specification<UserModel> spec = withTypeEquals(filter.getTypeE())
//                .and(withStatusEquals(filter.getStatusE())).and(withEmailEquals(filter.getEmail()));

    private Specification<UserModel> getUserSpecification(final UserFilter filter) {
        Specification<UserModel> spec = null;

        if (filter.isFullNameNotEmpty())
            spec = withFullNameLike(filter.getFullName());

        if (filter.isTypeNotNull())
            if (spec != null)
                spec.and(withTypeEquals(filter.getTypeE()));
            else
                spec = withTypeEquals(filter.getTypeE());

        if (filter.isStatusNotNull()) {
            if (spec != null)
                spec.and(withStatusEquals(filter.getStatusE()));
            else
                spec = withStatusEquals(filter.getStatusE());
        }

        if (filter.isEmailNotEmpty()) {
            if (spec != null)
                spec.and(withEmailEquals(filter.getEmail()));
            else
                spec = withEmailEquals(filter.getEmail());
        }

        return spec;
    }

    public PageUserResponse call(final UserFilter filter,
                                 final @PageableDefault(sort = "id",
                                                        direction = Sort.Direction.DESC) Pageable pageable) {
        final Specification<UserModel> spec = getUserSpecification(filter);

        Page<UserModel> pageUserModel = this.repository.findAll(spec, pageable);

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
