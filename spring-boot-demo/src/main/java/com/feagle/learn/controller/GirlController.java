package com.feagle.learn.controller;

import com.feagle.learn.domain.Girl;
import com.feagle.learn.domain.Result;
import com.feagle.learn.properties.GirlProperties;
import com.feagle.learn.repository.GirlRepository;
import com.feagle.learn.service.GirlService;
import com.feagle.learn.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by feagle on 2017/5/23.
 */
@RestController
public class GirlController {
    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);
    @Autowired
    private GirlProperties girlProperties;

    @Autowired
    private GirlRepository girlRepository;


    @Autowired
    private GirlService girlService;

    //    @RequestMapping(value = {"/say","/hi"}, method = RequestMethod.POST)
    @GetMapping(value = {"/say", "/hi"})
    public String say(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myId) {
        return "id:" + myId + "," + girlProperties.getCupSize() + "," + girlProperties.getAge();
    }

    /**
     * 查询所有女生列表
     *
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList() {
        logger.info("girlList");
        return girlRepository.findAll();
    }

    /**
     * 创建一个女生
     *
     * @return
     */
    @PostMapping(value = "/girls")
    public Result girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(-1,bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(girlRepository.save(girl));
    }

    /**
     * 通过Id查询一个女生
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id) {
        return girlRepository.findOne(id);
    }

    /**
     * 通过ID更新一个女生
     *
     * @param id
     * @param cupSize
     * @param age
     * @return
     */
    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdateOne(@PathVariable("id") Integer id, @RequestParam(value = "cupSize") String cupSize, @RequestParam(value = "age") Integer age) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.save(girl);
    }

    /**
     * 通过ID删除一个女生
     *
     * @param id
     */
    @DeleteMapping("/girls/{id}")
    public void girlDeleteById(@PathVariable("id") Integer id) {
        girlRepository.delete(id);
    }

    /**
     * 通过年龄查询女生列表
     *
     * @param age
     * @return
     */
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlFindByAge(@PathVariable("age") Integer age) {
        return girlRepository.findByAge(age);
    }

    /**
     * 添加两个女生,方便测试事务
     * @return
     */
    @PostMapping(value = "/girls/two")
    public String girlTwo() {
        return String.valueOf(girlService.insertTwo());
    }

    @GetMapping(value="girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id){
        girlService.getAge(id);
    }
}
