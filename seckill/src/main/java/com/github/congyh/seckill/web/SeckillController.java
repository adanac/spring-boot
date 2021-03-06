package com.github.congyh.seckill.web;

import com.github.congyh.seckill.domain.SeckillProductDO;
import com.github.congyh.seckill.dto.Result;
import com.github.congyh.seckill.dto.SeckillExecutionDTO;
import com.github.congyh.seckill.dto.SeckillUrlDTO;
import com.github.congyh.seckill.enums.ResultTypeEnum;
import com.github.congyh.seckill.exception.DomainNotFoundException;
import com.github.congyh.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author <a href="mailto:feagleliu@sina.com">Feagle</a>
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    /**
     * 获取商品列表
     *
     * @return 商品列表页面
     * @throws DomainNotFoundException 实体未找到
     */
    @GetMapping("/products")
    public String products(Model model) throws DomainNotFoundException {
        List<SeckillProductDO> seckillProductList = seckillService.findAll();
        if (seckillProductList == null) {
            throw new DomainNotFoundException();
        }
        model.addAttribute("seckillProductList", seckillProductList);

        return "products";
    }

    /**
     * 商品详情页
     *
     * @param id    商品id
     * @param model 商品信息
     * @return 商品详情页地址
     * @throws DomainNotFoundException 实体未找到
     */
    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable("id") long id,
                                Model model) throws DomainNotFoundException {
        SeckillProductDO seckillProduct = seckillService.findById(id);
        if (seckillProduct == null) {
            throw new DomainNotFoundException();
        }
        model.addAttribute("seckillProduct", seckillProduct);

        return "product-detail";
    }


    /**
     * 暴露秒杀URL地址给ajax调用
     *
     * @param id 秒杀商品id
     * @return 秒杀地址
     */
    @GetMapping("/products/{id}/seckillUrl")
    @ResponseBody
    public Result<SeckillUrlDTO> seckillUrl(@PathVariable("id") long id) {
        SeckillUrlDTO seckillUrl = seckillService.exposeSeckillUrl(id);
        return new Result<>(ResultTypeEnum.OK, seckillUrl);
    }

    /**
     * 执行秒杀操作
     *
     * @param id        秒杀商品id
     * @param url       秒杀url
     * @param userPhone 用户手机号
     * @return 秒杀执行结果
     */
    @GetMapping("/products/{id}/{url}")
    @ResponseBody
    public Result<SeckillExecutionDTO> seckillUrl(@PathVariable("id") long id,
                                                  @PathVariable("url") String url,
                                                  @CookieValue(value = "userPhone", required = false) long userPhone) {
        return seckillService.executeSeckill(id, userPhone, url);
    }
}
