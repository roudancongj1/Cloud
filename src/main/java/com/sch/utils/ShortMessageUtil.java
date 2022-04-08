package com.sch.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

/**
 * @Auth: Gao
 * @Date: 2022/3/18 17:06
 */

//@Component
public class ShortMessageUtil {
    /*验证码*/
    public static ResultUtil sendCodeMessage(String phone) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<your-access-key-id>", "<your-access-key-secret>");
        /** use STS Token
         DefaultProfile profile = DefaultProfile.getProfile(
         "<your-region-id>",           // The region ID
         "<your-access-key-id>",       // The AccessKey ID of the RAM account
         "<your-access-key-secret>",   // The AccessKey Secret of the RAM account
         "<your-sts-token>");          // STS Token
         **/
        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        request.setSignName("光晕科技");
        request.setTemplateCode("applyCodeTemp");
        request.setPhoneNumbers(phone);

        String code = UUID.randomUUID().toString().substring(4);
        JSONObject codeMap = new JSONObject();
        codeMap.put("code",code);

        request.setTemplateParam(codeMap.toJSONString());

        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
            return ResultUtil.ok("短信成功发送");
        } catch (ServerException e) {
            e.printStackTrace();
            return ResultUtil.error("短信发送失败:服务异常");
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
            return ResultUtil.error("短信发送失败:程序异常");
        }

    }


    /*发送信息*/
    public static ResultUtil sendMessage(String phone,String info) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<your-access-key-id>", "<your-access-key-secret>");
        /** use STS Token
         DefaultProfile profile = DefaultProfile.getProfile(
         "<your-region-id>",           // The region ID
         "<your-access-key-id>",       // The AccessKey ID of the RAM account
         "<your-access-key-secret>",   // The AccessKey Secret of the RAM account
         "<your-sts-token>");          // STS Token
         **/
        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        request.setSignName("光晕科技");
        request.setTemplateCode("applyStringTemp");
        request.setPhoneNumbers(phone);


        JSONObject codeMap = new JSONObject();
        codeMap.put("info",info);

        request.setTemplateParam(codeMap.toJSONString());

        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
            return ResultUtil.ok("短信成功发送");
        } catch (ServerException e) {
            e.printStackTrace();
            return ResultUtil.error("短信发送失败:服务异常");
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
            return ResultUtil.error("短信发送失败:程序异常");
        }

    }
}
