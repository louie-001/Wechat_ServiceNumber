package self.louie.wechat.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import self.louie.wechat.beans.WechatAccessToken;
import self.louie.wechat.helper.UrlBuilder;
import self.louie.wechat.service.WechatService;
import self.louie.wechat.web.RequestExecutor;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by louie on 2017-11-13.
 */
@Service
public class WechatServiceImpl implements WechatService {
    Logger logger = Logger.getLogger(this.getClass());

    @Value("${wechat.token}")
    private String wechatToken;
    @Override
    public String checkSignature(String signature, Long timestamp, String nonce, String echostr) {
        String[] array = new String[]{timestamp+"",nonce,wechatToken};
        Arrays.sort(array);

        StringBuilder builder = new StringBuilder();
        for (String str : array) {
            builder.append(str);
        }

        String digested = DigestUtils.sha1Hex(builder.toString());
        if (signature.equals(digested)){
            return echostr;
        }
        return null;
    }



}
