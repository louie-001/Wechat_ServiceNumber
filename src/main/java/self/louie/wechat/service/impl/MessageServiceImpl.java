package self.louie.wechat.service.impl;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import self.louie.wechat.beans.WechatMessage;
import self.louie.wechat.service.MessageService;

/**
 * Created by louie on 2017-11-14.
 */
@Service
public class MessageServiceImpl implements MessageService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public String parseMessage(String xmlMessage) {
        XStream xStream = new XStream(new DomDriver("utf8"));
        xStream.alias("xml",WechatMessage.class);
//        xStream.processAnnotations(WechatMessage.class);
        WechatMessage message = (WechatMessage)xStream.fromXML(xmlMessage);

        System.out.println(message.getClass().getName());
        return "";
    }
}
