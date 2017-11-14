package self.louie.wechat.service;

import self.louie.wechat.beans.WechatAccessToken;

import java.io.IOException;

/**
 * 微信身份凭证accessToken服务接口
 * Created by louie on 2017-11-14.
 */
public interface TokenService {
    public WechatAccessToken getAccessToken() throws IOException;

    public String getTokenFromCache() ;
}
