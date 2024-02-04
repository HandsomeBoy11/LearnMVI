package com.wj.learnmvi.dao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.wj.learnmvi.dao.DaoSession;
import com.wj.learnmvi.dao.PageInfoDao;
import com.wj.learnmvi.dao.BtnInfoDao;

/**
 * Author:WJ
 * Date:2024/2/1 9:25
 * Describe：
 */
@Entity
public class BtnInfo {
    @Id(autoincrement = true)
    private Long id;
    private String button_name;
    private int click_num;
    private Long PageId;
    private Long UserId;

    @ToOne(joinProperty = "PageId")
    private PageInfo pageInfo;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 852155228)
    private transient BtnInfoDao myDao;

    @Generated(hash = 1017444822)
    public BtnInfo(Long id, String button_name, int click_num, Long PageId, Long UserId) {
        this.id = id;
        this.button_name = button_name;
        this.click_num = click_num;
        this.PageId = PageId;
        this.UserId = UserId;
    }

    @Generated(hash = 1617943743)
    public BtnInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getButton_name() {
        return this.button_name;
    }

    public void setButton_name(String button_name) {
        this.button_name = button_name;
    }

    public int getClick_num() {
        return this.click_num;
    }

    public void setClick_num(int click_num) {
        this.click_num = click_num;
    }

    public Long getPageId() {
        return this.PageId;
    }

    public void setPageId(Long PageId) {
        this.PageId = PageId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getUserId() {
        return UserId;
    }

    @Generated(hash = 1817678840)
    private transient Long pageInfo__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1251790477)
    public PageInfo getPageInfo() {
        Long __key = this.PageId;
        if (pageInfo__resolvedKey == null || !pageInfo__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PageInfoDao targetDao = daoSession.getPageInfoDao();
            PageInfo pageInfoNew = targetDao.load(__key);
            synchronized (this) {
                pageInfo = pageInfoNew;
                pageInfo__resolvedKey = __key;
            }
        }
        return pageInfo;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1691662636)
    public void setPageInfo(PageInfo pageInfo) {
        synchronized (this) {
            this.pageInfo = pageInfo;
            PageId = pageInfo == null ? null : pageInfo.getId();
            pageInfo__resolvedKey = PageId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 241571822)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBtnInfoDao() : null;
    }

    @Override
    public String toString() {
        return "BtnInfo{" +
                "id=" + id +
                ", button_name='" + button_name + '\'' +
                ", click_num=" + click_num +
                ", PageId=" + PageId +
                ", pageInfo=" + pageInfo +
                '}';
    }
}
