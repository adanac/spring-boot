package com.github.congyh.seckill.dto;

import java.io.Serializable;

/**
 * 秒杀地址封装类
 *
 * @author <a href="mailto:feagleliu@sina.com">Feagle</a>
 */
public class SeckillUrlDTO implements Serializable {

    private static final long serialVersionUID = -2578192044275753056L;
    private Long productId;
    /** 一种加密措施 */
    private String md5;

    public SeckillUrlDTO(Long productId, String md5) {
        this.md5 = md5;
        this.productId = productId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
