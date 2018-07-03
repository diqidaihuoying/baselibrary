package wt.cn.com.wt9.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 创建日期：2018/7/2 on 10:25
 * 描述:
 * 作者:wantao
 */
@Entity
public class HeartrateData {
    private Long    _id;
    private String  serverID;
    private Integer heartrateCout;
    private Long    timeMillis;
    private String  dateStr;
    private Integer optionType;
    private Boolean upload;
    private String  userid;

    @Generated(hash = 233779920)
    public HeartrateData(Long _id, String serverID, Integer heartrateCout,
            Long timeMillis, String dateStr, Integer optionType, Boolean upload,
            String userid) {
        this._id = _id;
        this.serverID = serverID;
        this.heartrateCout = heartrateCout;
        this.timeMillis = timeMillis;
        this.dateStr = dateStr;
        this.optionType = optionType;
        this.upload = upload;
        this.userid = userid;
    }

    @Generated(hash = 696249871)
    public HeartrateData() {
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getServerID() {
        return serverID;
    }

    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    public Integer getHeartrateCout() {
        return heartrateCout;
    }

    public void setHeartrateCout(Integer heartrateCout) {
        this.heartrateCout = heartrateCout;
    }

    public Long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(Long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Integer getOptionType() {
        return optionType;
    }

    public void setOptionType(Integer optionType) {
        this.optionType = optionType;
    }

    public Boolean getUpload() {
        return upload;
    }

    public void setUpload(Boolean upload) {
        this.upload = upload;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
