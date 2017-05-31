package com.github.congyh.seckill.exception;

/**
 * 实体未找到异常
 *
 * @author <a href="mailto:feagleliu@sina.com">Feagle</a>
 */
public class DomainNotFoundException extends RuntimeException {

    public DomainNotFoundException() {
    }

    public DomainNotFoundException(String message) {
        super(message);
    }
}
