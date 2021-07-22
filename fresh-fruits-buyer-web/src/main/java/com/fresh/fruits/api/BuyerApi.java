package com.fresh.fruits.api;

import com.fresh.fruits.pojo.BuyerEntity;
import com.fresh.fruits.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @project: fresh-fruits;
 * @package: com.fresh.fruits.api;
 * @author: Administrator;
 * @date: 2021/7/8 23:25;
 * @Description:
 */
@FeignClient("fresh-fruits-buyer-service")
public interface BuyerApi {
    @RequestMapping("/buyer/list")
    public R list(@RequestParam Map<String, Object> params);

    @RequestMapping("/buyer/save")
    public R save(@RequestBody BuyerEntity buyer);
}
