package self.louie.wechat.beans;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 分享二维码扫码记录
 * Created by louie on 2017-11-15.
 */
@Entity
@Table(name = "BO_XR_USER_WECHT_SHARE")
public class QRCodeScan implements Serializable{
    public QRCodeScan(){
        super();
    }

    public QRCodeScan(String wechatOpenid,String sysUserid){
        this.wechatOpenid = wechatOpenid;
        this.sysUserid = sysUserid;
    }

    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String orgid;
    private String bindid;
    private String createdate;
    private String updatedate;
    private String updateuser;
    private String processdefid;
    private Integer isend = 0;

    private String wechatOpenid;
    private String sysUserid;
    private String subscribeTime = DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getBindid() {
        return bindid;
    }

    public void setBindid(String bindid) {
        this.bindid = bindid;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public String getProcessdefid() {
        return processdefid;
    }

    public void setProcessdefid(String processdefid) {
        this.processdefid = processdefid;
    }

    public Integer getIsend() {
        return isend;
    }

    public void setIsend(Integer isend) {
        this.isend = isend;
    }

    public String getWechatOpenid() {
        return wechatOpenid;
    }

    public void setWechatOpenid(String wechatOpenid) {
        this.wechatOpenid = wechatOpenid;
    }

    public String getSysUserid() {
        return sysUserid;
    }

    public void setSysUserid(String sysUserid) {
        this.sysUserid = sysUserid;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }
}
