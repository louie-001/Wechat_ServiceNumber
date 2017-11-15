package self.louie.wechat.service;

import self.louie.wechat.beans.QRCodeTicket;

/**
 * 微信公众号推广二维码相关接口
 * Created by louie on 2017-11-14.
 */
public interface QRCodeService {

    /**
     * 获取微信带参数的二维码ticket
     * @param qrcodeParam
     * @return
     */
    public QRCodeTicket getQRCodeTicket(String qrcodeParam);

    /**
     * 获取用户二维码解析url地址
     * @param userId
     * @return
     */
    public String getUserQRCodeUrl(String userId);

}
