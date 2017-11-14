package self.louie.wechat.beans;

import org.springframework.context.annotation.Bean;

/**
 * 微信身份凭证模型
 * Created by louie on 2017-11-14.
 */

public class WechatAccessToken {
    private String access_token;//获取到的凭证
    private Long expires_in;//凭证有效时间，单位：秒
    private Long accessTimestamp;//获取时间，时间戳，单位秒

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public Long getAccessTimestamp() {
        return accessTimestamp;
    }

    public void setAccessTimestamp(Long accessTimestamp) {
        this.accessTimestamp = accessTimestamp;
    }
}
