package self.louie.wechat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import self.louie.wechat.beans.WechatAccessToken;
import self.louie.wechat.helper.UrlBuilder;
import self.louie.wechat.service.TokenService;
import self.louie.wechat.web.RequestExecutor;

import java.io.IOException;

/**
 * Created by louie on 2017-11-14.
 */
@Service
public class TokenServiceImpl implements TokenService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UrlBuilder urlBuilder;

    private WechatAccessToken tokenCache;

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
