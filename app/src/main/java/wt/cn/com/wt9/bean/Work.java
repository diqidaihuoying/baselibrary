package wt.cn.com.wt9.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 创建日期：2018/6/21 on 13:36
 * 描述:
 * 作者:wantao
 */
@Entity
public class Work {
    String name;

    @Generated(hash = 866359)
    public Work(String name) {
        this.name = name;
    }

    @Generated(hash = 572069219)
    public Work() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
