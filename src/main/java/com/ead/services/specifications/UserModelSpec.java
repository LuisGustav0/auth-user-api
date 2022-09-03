package com.ead.services.specifications;

import com.ead.enums.UserStatusE;
import com.ead.enums.UserTypeE;
import com.ead.model.UserModel;
import org.springframework.data.jpa.domain.Specification;

public class UserModelSpec {

    public static Specification<UserModel> withFullNameLike(final String fullName) {
        return ((root, query, builder) -> builder.like(
                builder.lower(root.get("fullName")),
                builder.lower(builder.literal("%" + fullName + "%"))
        ));
    }

    public static Specification<UserModel> withTypeEquals(final UserTypeE typeE) {
        return ((root, query, builder) -> builder.equal(root.get("typeE"), typeE));
    }

    public static Specification<UserModel> withStatusEquals(final UserStatusE statusE) {
        return ((root, query, builder) -> builder.equal(root.get("statusE"), statusE));
    }

    public static Specification<UserModel> withEmailEquals(final String email) {
        return ((root, query, builder) -> builder.equal(root.get("email"), email));
    }
}
