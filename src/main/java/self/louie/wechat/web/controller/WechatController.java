package self.louie.wechat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import self.louie.wechat.service.TokenService;
import self.louie.wechat.service.WechatService;

/**
 * Created by louie on 2017-11-13.
 */
@RestController
public class WechatController {

    @Autowired
    private WechatService wechatService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/checkSignature")
    public String checkSignature(String signature,Long timestamp,String nonce,String echostr){
        return wechatService.checkSignature(signature,timestamp,nonce,echostr);
    }

    @RequestMapping(value = "/test")
    public String testInternet(){

        return tokenService.getTokenFromCache();
    }

}
