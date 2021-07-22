package com.fresh.fruits.controller;

import com.fresh.fruits.utils.ResultCode;
import com.fresh.fruits.utils.ResultCommon;
import com.sun.org.glassfish.gmbal.ParameterNames;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @project: fresh-fruits;
 * @package: com.fresh.fruits.controller;
 * @author: Administrator;
 * @date: 2021/7/9 0:11;
 * @Description:
 */
@RestController
@RequestMapping("message")
@Slf4j
public class MessageController {

    //互亿 手机验证码：API访问地址
    private static final String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

    @Value("${huyi.APIID}")
    String APIID;

    @Value("${huyi.APIKEY}")
    String APIKEY;

    /**
     * 真正的验证码调用API地址 由于免费短信只有10条 不予测试
     * @param phone
     * @return
     */

    //@RequestMapping("send/{phone}")
    public ResultCommon endMessage(@PathVariable("phone") String phone){
        //HttpClient远程发送请求
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);
        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");

        //六位的随机数，这个就是随机生成的手机验证码
        int mobile_code = (int)((Math.random()*9+1)*100000);

        //短信内容【此短信内容的模板暂时不能被修改  试用期间短信内容模板不能被修改  如果要修改 互译无线后台修改---审核】
        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

        //提交短信
        NameValuePair[] data = {
                new NameValuePair("account", APIID),//查看用户名是登录用户中心->验证码短信->产品总览->APIID
                new NameValuePair("password", APIKEY),  //查看密码请登录用户中心->验证码短信->产品总览->APIKEY
                //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
                new NameValuePair("mobile",phone ),
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);

        try {
            client.executeMethod(method);
            String submitResult = method.getResponseBodyAsString();
            Document doc = DocumentHelper.parseText(submitResult);
            Element root = doc.getRootElement();

            String code = root.elementText("code");  // 返回值为2时，表示提交成功
            String msg = root.elementText("msg");  // 提交结果描述
            String smsid = root.elementText("smsid"); // 当提交成功后，此字段为流水号，否则为0

            log.info(code);
            log.info(msg);
            log.info(smsid);

            if ("2".equals(code)){
                log.info("短信提交成功");
                return ResultCommon.success(ResultCode.SUCCESS,mobile_code);
            }
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return ResultCommon.fail(ResultCode.FAIL);
    }

    @RequestMapping("send/{phone}")
    public int sendMessage(@PathVariable("phone") String phone){
        log.info(APIID+":"+APIKEY);
        //六位的随机数，这个就是随机生成的手机验证码
        int mobile_code = (int)((Math.random()*9+1)*100000);
        log.info("模拟API：短信提交成功，验证码为："+mobile_code);
        return mobile_code;
    }
}
