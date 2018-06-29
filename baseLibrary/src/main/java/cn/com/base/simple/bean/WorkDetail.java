package cn.com.base.simple.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import java.util.List;

import cn.com.base.BR;
import cn.com.base.LibApplication;
import cn.com.base.R;
import cn.com.base.util.DateUtil;

/**
 * 创建日期：2018/6/29 on 18:05
 * 描述:
 * 作者:wantao
 */
public class WorkDetail extends BaseObservable {

    /**
     * avatar : /v1/getAvatar?id=933
     * categoryName : 摄影
     * comments : 0
     * cover : /resource/v1/cover?path=/upload/image/20180628/15301525112440.png
     * createtime : 1530152512393
     * duration : 0
     * favorites : 0
     * favors : 0
     * height : 480
     * hid : 1
     * id : 8343
     * imageList : ["/resource/v1/image?path=/upload/image/20180628/15301525112440.png"]
     * isFavor : 0
     * isFavorite : 0
     * isFollow : 0
     * isRecommend : 0
     * name : 和姐
     * path :
     * pv : 1
     * size : 0
     * state : 1
     * summary :
     * type : 1
     * uid : 933
     * width : 360
     */

    private String avatar;
    private String categoryName;
    private int comments;
    private String cover;
    private long createtime;
    private int duration;
    private int favorites;
    private int favors;
    private int height;
    private int hid;
    private int id;
    private int isFavor;
    private int isFavorite;
    private int isFollow;
    private int isRecommend;
    private String name;
    private String path;
    private int pv;
    private int size;
    private int state;
    private String summary;
    private int type;
    private int uid;
    private int width;
    private List<String> imageList;
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public int getFavors() {
        return favors;
    }

    public void setFavors(int favors) {
        this.favors = favors;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsFavor() {
        return isFavor;
    }

    public void setIsFavor(int isFavor) {
        this.isFavor = isFavor;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }


    /**
     * 时间设置
     */
    private String mcreateTime;
    public String getMcreateTime() {
        return DateUtil.getTimeDescrition(createtime);
    }

    /**
     * 设置鲜花
     */
    private Drawable isFavorBg;
    @Bindable
    public Drawable getIsFavorBg() {
        if (isFavor==0)
        {
            return getDrawable(R.mipmap.ic_test_flower_unselected);
        }else
        {
            return getDrawable(R.mipmap.ic_test_flower_selected);
        }
    }

    public void setIsFavorBg(int isFavor) {
        this.isFavor=isFavor;
        notifyPropertyChanged(BR.isFavorBg);
    }

    /**
     * 设置鲜花
     */
    private Drawable isCollectBg;
    @Bindable
    public Drawable getIsCollectBg() {
        if (isFollow==0)
        {
            return getDrawable(R.mipmap.ic_test_collect_unselected);
        }else
        {
            return getDrawable(R.mipmap.ic_test_collect_selected);
        }
    }

    public void setIsCollectBg(int isFollow) {
        this.isFollow = isFollow;
        notifyPropertyChanged(BR.isCollectBg);
    }

    private Drawable getDrawable(int resourceId)
    {
        return ContextCompat.getDrawable(LibApplication.application, resourceId);
    }

}
