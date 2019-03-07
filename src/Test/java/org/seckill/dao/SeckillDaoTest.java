package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: zys
 * @Date: 2019-03-04 14:00
 * @Description:
 * 配置spring和junit整合,Junit启动时加载springIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)//Junit启动时加载springIOC容器
@ContextConfiguration({"classpath:spring/spring-dao.xml"})//告诉Junit spring配置文件
public class SeckillDaoTest {

    //注入Dao实现依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testQueryById() throws Exception{
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void testQueryAll() throws Exception{
        List<Seckill> seckillList = seckillDao.queryAll(0,100);
        for (Seckill seckill : seckillList){
            System.out.println(seckill);
        }
    }

    @Test
    public void testReduceNumber() throws Exception{
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000,killTime);
        System.out.println(updateCount);
    }

}