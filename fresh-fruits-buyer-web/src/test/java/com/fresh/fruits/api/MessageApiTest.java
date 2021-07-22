package com.fresh.fruits.api;

import com.fresh.fruits.BuyerWebApp;
import com.fresh.fruits.utils.ResultCommon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @project: fresh-fruits;
 * @package: com.fresh.fruits.api;
 * @author: Administrator;
 * @date: 2021/7/9 23:26;
 * @Description:
 */
@SpringBootTest(classes = BuyerWebApp.class)
public class MessageApiTest {
    @Autowired
    MessageApi messageApi;
    @Test
    public void testMessage(){
        int resultCommon = messageApi.sendMessage("13778729882");
        System.out.println(resultCommon);
    }
}
