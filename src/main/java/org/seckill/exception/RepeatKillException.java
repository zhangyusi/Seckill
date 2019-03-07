package org.seckill.exception;

/**
 * @Auther: zys
 * @Date: 2019-03-05 12:50
 * @Description: 重复秒杀异常
 */
public class RepeatKillException extends SeckillException{

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
