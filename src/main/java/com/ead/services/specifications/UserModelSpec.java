package com.ead.services.specifications;

import com.ead.enums.UserStatusE;
import com.ead.enums.UserTypeE;
import com.ead.model.UserCourseModel;
import com.ead.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.util.UUID;

public class UserModelSpec {

    public static Specification<UserModel> withFullNameLike(final String fullName) {
        return ((root, query, builder) -> {
            if (StringUtils.isEmpty(fullName))
                return builder.conjunction();

            return builder.like(
                    builder.lower(root.get("fullName")),
                    builder.lower(builder.literal("%" + fullName + "%"))
            );
        });
    }

    public static Specification<UserModel> withTypeEquals(final UserTypeE typeE) {
        return ((root, query, builder) -> {
            if (typeE == null)
                return builder.conjunction();

            return builder.equal(root.get("typeE"), typeE);
        });
    }

    public static Specification<UserModel> withStatusEquals(final UserStatusE statusE) {
        return ((root, query, builder) -> {
            if (statusE == null)
                return builder.conjunction();

            return builder.equal(root.get("statusE"), statusE);
        });
    }

    public static Specification<UserModel> withEmailEquals(final String email) {
        return ((root, query, builder) -> {
            if (StringUtils.isEmpty(email))
                return builder.conjunction();

            return builder.like(
                    builder.lower(root.get("email")),
                    builder.lower(builder.literal(email))
            );
        });
    }

    public static Specification<UserModel> withUserCourseId(final UUID courseId) {
        return ((root, query, builder) -> {
            if (courseId == null)
                return builder.conjunction();

            query.distinct(true);

            Join<UserModel, UserCourseModel> userJoin = root.join("listCourse");

            return builder.equal(userJoin.get("courseId"), courseId);
        });
    }
}
