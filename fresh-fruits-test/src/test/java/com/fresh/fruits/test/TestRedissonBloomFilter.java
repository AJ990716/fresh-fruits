package com.fresh.fruits.test;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class TestRedissonBloomFilter {

    @Test
    public void test1(){

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        //config.useSingleServer().setPassword("123");
        //构造Redisson Client
        RedissonClient redisson = Redisson.create(config);
        //在Redis中创建一个布隆过滤器
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("phoneList");
        //初始化布隆过滤器：预计元素为100000000L,误差率为3%
        bloomFilter.tryInit(100,0.03);

        //模拟：将号码10086插入到布隆过滤器中
        bloomFilter.add("10086");
        bloomFilter.add("10083");
        bloomFilter.add("10081");
        bloomFilter.add("10081");
        bloomFilter.add("10083");
        bloomFilter.add("10087");
        bloomFilter.add("10089");

        //开始判断：判断下面号码是否在布隆过滤器中
        System.out.println(bloomFilter.contains("123456"));//false：电话号码一定不存在
        System.out.println(bloomFilter.contains("10086"));//true  表示可能存在！
    }
}
