package self.louie.wechat.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import self.louie.wechat.beans.QRCodeScan;
import self.louie.wechat.enums.WechatEnums;
import self.louie.wechat.repository.QRCodeScanRepository;
import self.louie.wechat.service.MessageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by louie on 2017-11-14.
 */
@Service
public class MessageServiceImpl implements MessageService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String parseMessage(String xmlMessage) {
        Map<String,String> params = new HashMap<>();
        try {
            Document document = DocumentHelper.parseText(xmlMessage);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();

            for (Element element : elements) {
                params.put(element.getName(),element.getStringValue());
            }

            String msgType = params.get("MsgType");
            if (msgType.equals(WechatEnums.MSG_TYPE_EVENT.getValue())){//事件推送
                parseEventMsg(params);
            }else if (msgType.equals(WechatEnums.MSG_TYPE_TEXT.getValue())){//文本消息

            }else if (msgType.equals(WechatEnums.MSG_TYPE_IMAGE)){//图片消息

            }
        } catch (DocumentException e) {
            logger.error(e.getMessage(),e);
        }
        return "";
    }

    /**
     * 解析微信平台事件消息
     * @param eventParams
     */
    @Autowired
    private QRCodeScanRepository codeScanRepository;
    private void parseEventMsg(Map<String,String> eventParams){
        String eventType = eventParams.get("Event");
        if (WechatEnums.EVENT_SUBSCRIBE.getValue().equals(eventType)){//订阅事件
            String eventKey = eventParams.get("EventKey");
            if (StringUtils.isNotEmpty(eventKey)){//通过扫描分享的二维码关注
                String userId = eventKey.replaceAll("qrscene_","");
                String openId = eventParams.get("FromUserName");

                QRCodeScan qrCodeScan = new QRCodeScan(openId,userId);
                codeScanRepository.save(qrCodeScan);
            }
        }

    }
}
