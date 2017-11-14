package self.louie.wechat.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import self.louie.wechat.enums.WechatEnums;

/**
 * Created by louie on 2017-11-13.
 */
@Component
public class UrlBuilder {
    @Value("${wechat.appID}")
    private String appID;

    @Value("${wechat.appSecret}")
    private String appSecret;

    public String buildGetTokenUrl(){
        return WechatEnums.URL_TOKEN.getValue()+"&appid="+ appID+"&secret="+appSecret;
    }
}
