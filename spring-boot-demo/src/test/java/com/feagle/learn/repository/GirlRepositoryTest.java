package com.feagle.learn.repository;

import com.feagle.learn.SpringbootDemoApplication;
import com.feagle.learn.domain.Girl;
import com.feagle.learn.service.GirlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.RollbackException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by feagle on 2017/5/23.
 */
//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文
@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlRepositoryTest {
    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    @Test
    public void findByAge() throws Exception {
        int age = 131;
        List<Girl> girlList = girlRepository.findByAge(age);
        assertNotNull(girlList);
    }

    @Test
    public void insertTwo() {
        boolean res = true;
        try {
            res = girlService.insertTwo();
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                res = false;
            }
        }
        assertFalse(res);
    }
}