package self.louie.wechat.service;

import self.louie.wechat.beans.WechatAccessToken;

import java.io.IOException;

/**
 * Created by louie on 2017-11-13.
 */
public interface SignatureService {
    /**
     * 微信接入签名校验
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    public String checkSignature(String signature, Long timestamp, String nonce, String echostr);


}
