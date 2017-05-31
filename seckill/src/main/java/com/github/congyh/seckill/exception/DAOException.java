package com.github.congyh.seckill.exception;

/**
 * DAO层通用异常
 *
 * @author <a href="mailto:feagleliu@sina.com">Feagle</a>
 */
public class DAOException extends RuntimeException {

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
