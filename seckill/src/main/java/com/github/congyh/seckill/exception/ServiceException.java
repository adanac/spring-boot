package com.github.congyh.seckill.exception;

/**
 * Service层异常.
 *
 * @author <a href="mailto:feagleliu@sina.com">Feagle</a>
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
