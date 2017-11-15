package self.louie.wechat.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import self.louie.wechat.service.SignatureService;

import java.util.Arrays;

/**
 * Created by louie on 2017-11-13.
 */
@Service
public class SignatureServiceImpl implements SignatureService {
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
