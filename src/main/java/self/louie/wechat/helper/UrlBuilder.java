package self.louie.wechat.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import self.louie.wechat.enums.WechatEnums;
import self.louie.wechat.service.TokenService;

/**
 * Created by louie on 2017-11-13.
 */
@Component
public class UrlBuilder {
    @Value("${wechat.appID}")
    private String appID;

    @Value("${wechat.appSecret}")
    private String appSecret;

    @Autowired
    TokenService tokenService;

    public String buildGetTokenUrl(){
        return WechatEnums.URL_TOKEN.getValue()+"&appid="+ appID+"&secret="+appSecret;
    }

    public String buildCreateQRCodeUrl(){
        return WechatEnums.URL_QRCODE_CREATE.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

}
