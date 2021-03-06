package com.hantsylabs.restexample.springmvc.repository;

import com.hantsylabs.restexample.springmvc.domain.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hantsy Bai<hantsy@gmail.com>
 */
public class UserSpecifications {

    private UserSpecifications() {
    }

    public static Specification<User> filterUsersByKeyword(
            final String keyword,//
            final String role //
    ) {

        return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(keyword)) {
                predicates.add(
                        cb.or(
                                cb.like(root.get("name"), "%" + keyword + "%"),
                                cb.like(root.get("username"), "%" + keyword + "%")
                        ));
            }

            if (StringUtils.hasText(role) && !"ALL".equals(role)) {
                predicates.add(cb.equal(root.get("role"), role));
//                ListJoin<User_, String> roleJoin = root.join(User_.roles);
//                predicates.add(cb.equal(roleJoin, role));
            }

//            if (StringUtils.hasText(locked)) {
//                predicates.add(cb.equal(root.get(User_.locked), Boolean.valueOf(locked)));
//            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
