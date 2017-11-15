package self.louie.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import self.louie.wechat.beans.QRCodeTicket;
import self.louie.wechat.helper.UrlBuilder;
import self.louie.wechat.service.QRCodeService;
import self.louie.wechat.web.RequestExecutor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by louie on 2017-11-14.
 */
@Service
public class QRCodeServiceImpl implements QRCodeService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<String,QRCodeTicket> userQRCodeCache = new HashMap<>();

    @Autowired
    private UrlBuilder urlBuilder;

    @Override
    public QRCodeTicket getQRCodeTicket(String qrcodeParam) {
        RequestExecutor executor = new RequestExecutor(urlBuilder.buildCreateQRCodeUrl());

        JSONObject requestData = new JSONObject();
        requestData.put("action_name","QR_STR_SCENE");
        requestData.put("expire_seconds",2592000);
        Map<String,String> sceneStrMap = new HashMap<String,String>();
        sceneStrMap.put("scene_str",qrcodeParam);

        JSONObject actionInfo = new JSONObject();
        actionInfo.put("scene",sceneStrMap);

        requestData.put("action_info",actionInfo);

        try {
            QRCodeTicket qrCodeTicket = executor.executePostRequest(requestData.toJSONString(),QRCodeTicket.class);
            qrCodeTicket.setCreateTimestamp(System.currentTimeMillis()/1000);

            return qrCodeTicket;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public String getUserQRCodeUrl(String userId) {
        QRCodeTicket qrCodeTicket = userQRCodeCache.get(userId);
        if (qrCodeTicket != null){
            Long qrcodeCreateTime = qrCodeTicket.getCreateTimestamp();
            Long qrcodeExpireTime = qrCodeTicket.getExpire_seconds();

            if (System.currentTimeMillis()/1000 - qrcodeCreateTime >= qrcodeExpireTime){//二维码已失效，清除缓存重新获取
                clearQRCodeTicketFromCache(userId);
                qrCodeTicket = getQRCodeTicket(userId);
            }
        }else {
            qrCodeTicket = getQRCodeTicket(userId);
        }
        userQRCodeCache.put(userId,qrCodeTicket);

        return qrCodeTicket.getUrl();
    }

    private void clearQRCodeTicketFromCache(String userId){
        userQRCodeCache.remove(userId);
        logger.info("清除用户"+userId+"的二维码缓存");
    }
}
