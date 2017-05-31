package com.feagle.learn.service;

import com.feagle.learn.domain.Girl;
import com.feagle.learn.enums.ResultEnum;
import com.feagle.learn.exception.GirlException;
import com.feagle.learn.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by feagle on 2017/5/23.
 */
@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Transactional
    public boolean insertTwo() {
        try {
            Girl girlA = new Girl();
            girlA.setAge(21);
            girlA.setCupSize("A");
            Girl a = girlRepository.save(girlA);

            Girl girlB = new Girl();
            girlB.setAge(22);
            girlB.setCupSize("BBB");
            Girl b = girlRepository.save(girlB);
            return a != null || b != null;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public void getAge(Integer id) {
        Girl girl = girlRepository.findOne(id);
        Integer age = girl.getAge();
        if (age < 10) {
            //可能还在上小学
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (age >= 10 && age < 16) {
            //可能还在上中学
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }

        //如果age>16,加钱
        // ....

    }

    public Girl findOne(Integer id){
        Girl girl = girlRepository.findOne(id);
        return girl;
    }
}
