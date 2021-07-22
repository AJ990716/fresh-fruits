package com.fresh.fruits.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.fruits.pojo.BuyerQuestionEntity;
import com.fresh.fruits.service.BuyerQuestionService;
import com.fresh.fruits.utils.PageUtils;
import com.fresh.fruits.utils.R;



/**
 * 密保问题
 * @author bruce
 * @email bruce@gmail.com
 * @date 2021-07-08 23:02:22
 */
@RestController
@RequestMapping("buyerquestion")
public class BuyerQuestionController {
    @Autowired
    private BuyerQuestionService buyerQuestionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok().put("page", null);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		BuyerQuestionEntity buyerQuestion = buyerQuestionService.getById(id);

        return R.ok().put("buyerQuestion", buyerQuestion);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BuyerQuestionEntity buyerQuestion){
		buyerQuestionService.save(buyerQuestion);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BuyerQuestionEntity buyerQuestion){
		buyerQuestionService.updateById(buyerQuestion);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		buyerQuestionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
