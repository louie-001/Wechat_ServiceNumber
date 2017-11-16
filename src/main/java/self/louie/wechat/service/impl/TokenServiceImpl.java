package self.louie.wechat.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import self.louie.wechat.beans.SnsOauthorToken;
import self.louie.wechat.beans.WechatAccessToken;
import self.louie.wechat.helper.UrlBuilder;
import self.louie.wechat.service.TokenService;
import self.louie.wechat.web.RequestExecutor;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by louie on 2017-11-14.
 */
@Service
public class TokenServiceImpl implements TokenService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UrlBuilder urlBuilder;

    private WechatAccessToken tokenCache;

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

    @Override
    public String getTokenFromCache() {
        try {
            if (tokenCache == null){
                return getAccessToken().getAccess_token();
            }

            Long expiresIn = tokenCache.getExpires_in();
            Long accessTime = tokenCache.getAccessTimestamp();

            if (System.currentTimeMillis()/1000 - accessTime >= expiresIn){
                return getAccessToken().getAccess_token();
            }else {
                return tokenCache.getAccess_token();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public SnsOauthorToken getSnsOauthorToken(String code) {
        String url = urlBuilder.buildOauthorTokenUrl(code);
        try {
            return new RequestExecutor(url).executeGetRequest(SnsOauthorToken.class);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }


    @CachePut(value = "tokenCache",key = "wechatToken")
    private void cacheToken(WechatAccessToken token){
        tokenCache = token;
    }


    @Override
    public WechatAccessToken getAccessToken() throws IOException {
        String url = urlBuilder.buildGetTokenUrl();
        try {
            WechatAccessToken token = new RequestExecutor(url).executeGetRequest(WechatAccessToken.class);
            token.setAccessTimestamp(System.currentTimeMillis()/1000);
            cacheToken(token);
            return token;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }
}
