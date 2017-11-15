package self.louie.wechat.service;

/**
 * 微信公众平台消息服务
 * Created by louie on 2017-11-14.
 */
public interface MessageService {

    /**
     * 解析微信平台推送的消息
     * @param xmlMessage 微信平台post的消息xml
     * @return
     */
    public String parseMessage(String xmlMessage);

}
