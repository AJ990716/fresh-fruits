package com.fresh.fruits.api;

import com.fresh.fruits.utils.ResultCommon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @project: fresh-fruits;
 * @package: com.fresh.fruits.api;
 * @author: Administrator;
 * @date: 2021/7/9 22:57;
 * @Description:
 */
@FeignClient("fresh-fruits-third-service")
public interface MessageApi {
    /**
     * 01-短信接口
     * @return
     */
    @RequestMapping("message/send/{phone}")
    public int sendMessage(@PathVariable("phone") String phone);
}
