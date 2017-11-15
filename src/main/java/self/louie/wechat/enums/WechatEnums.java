package self.louie.wechat.enums;

/**
 * Created by louie on 2017-11-13.
 */
public enum WechatEnums {
    URL_TOKEN("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential","获取accessToken"),
    URL_QRCODE_CREATE("https://api.weixin.qq.com/cgi-bin/qrcode/create?","临时二维码请求"),
    ;

    private WechatEnums(String value,String desc){
        this.value = value;
        this.desc = desc;
    }

    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
