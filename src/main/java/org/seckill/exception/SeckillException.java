package org.seckill.exception;

/**
 * @Auther: zys
 * @Date: 2019-03-05 12:53
 * @Description:
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
