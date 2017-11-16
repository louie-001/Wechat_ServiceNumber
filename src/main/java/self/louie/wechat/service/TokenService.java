package self.louie.wechat.service;

import self.louie.wechat.beans.SnsOauthorToken;
import self.louie.wechat.beans.WechatAccessToken;

import java.io.IOException;

/**
 * 微信身份凭证accessToken服务接口
 * Created by louie on 2017-11-14.
 */
public interface TokenService {

    /**
     * 微信接入签名校验
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    public String checkSignature(String signature, Long timestamp, String nonce, String echostr);
    /**
     * 获取身份凭证
     * @return
     * @throws IOException
     */
    public WechatAccessToken getAccessToken() throws IOException;

    /**
     * 获取缓存的微信身份凭证
     * @return
     */
    public String getTokenFromCache() ;

    /**
     * 获取用户网页授权token
     * @param code
     * @return
     */
    public SnsOauthorToken getSnsOauthorToken(String code);
}
