package org.seckill.dao.cache;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: zys
 * @Date: 2019-03-06 17:50
 * @Description:
 */
public class RedisDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private JedisPool jedisPool;
//    private JedisCluster jedis;

    //单机
    public RedisDao(String ip, int port){
        jedisPool = new JedisPool(ip,port);
//        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//        nodes.add(new HostAndPort("121.196.220.19",6379));
//        nodes.add(new HostAndPort("121.196.220.19",6380));
//        nodes.add(new HostAndPort("121.196.220.19",6381));
//        nodes.add(new HostAndPort("121.196.220.19",6382));
//        nodes.add(new HostAndPort("121.196.220.19",6383));
//        nodes.add(new HostAndPort("121.196.220.19",6384));
//        jc = new JedisCluster(nodes);

    }

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public Seckill getSeckill(long seckillId){
        //redis操作逻辑
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:"+seckillId;
                //jedis客户端没有实现对象序列化
                // get -> byte[] 反序列化 -> Object(Seckill)
                //性能最好行列 protobuf，protostuff  采用自定义序列化，比java自带序列化高效很多
                byte[] bytes = jedis.get(key.getBytes());
                if(bytes != null){
                    Seckill seckill = schema.newMessage();//空对象来接收字节码的转换
                    ProtostuffIOUtil.mergeFrom(bytes,seckill,schema);//序列化seckill
                    return seckill;
                }
            }finally {
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    public String putSeckill(Seckill seckill){
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:"+seckill.getSeckillId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill,schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout = 60 * 60;//1小时
                String result = jedis.setex(key.getBytes(),timeout,bytes);
                return result;
            } finally {
                jedis.close();
            }

        } catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }
}
