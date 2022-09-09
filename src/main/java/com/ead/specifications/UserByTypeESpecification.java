package com.ead.specifications;

import com.ead.enums.UserTypeE;
import com.ead.model.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class UserByTypeESpecification implements Specification<UserModel> {

    private UserTypeE typeE;

    @Override
    public Predicate toPredicate(Root<UserModel> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        return builder.equal(root.get("typeE"), typeE);
    }
}
