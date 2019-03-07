package org.seckill.exception;

/**
 * @Auther: zys
 * @Date: 2019-03-05 12:52
 * @Description: 秒杀关闭异常
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
