package com.feagle.learn.repository;

import com.feagle.learn.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by feagle on 2017/5/23.
 */
public interface GirlRepository extends JpaRepository<Girl, Integer> {
    /**
     * 根据年龄查询女生列表
     *
     * @param age
     * @return
     */
    List<Girl> findByAge(Integer age);
}
