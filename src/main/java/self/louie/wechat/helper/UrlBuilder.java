package self.louie.wechat.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import self.louie.wechat.enums.WechatEnums;
import self.louie.wechat.service.TokenService;

/**
 * 微信公众平台请求服务url构建器
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

    /**
     * 构架获取accessTocken的请求url
     * @return
     */
    public String buildGetTokenUrl(){
        return WechatEnums.URL_TOKEN.getValue()+"&appid="+ appID+"&secret="+appSecret;
    }

    /**
     * 构建获取带参数二维码的请求url
     * @return
     */
    public String buildCreateQRCodeUrl(){
        return WechatEnums.URL_QRCODE_CREATE.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建获取网页授权token的请求url
     * @param code
     * @return
     */
    public String buildOauthorTokenUrl(String code){
        return WechatEnums.URL_OAUTHOR_TOKEN.getValue()+"appid="+appID+"&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";
    }


}
