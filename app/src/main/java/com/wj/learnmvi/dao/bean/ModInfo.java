package com.wj.learnmvi.dao.bean;

import com.wj.learnmvi.dao.DaoSession;
import com.wj.learnmvi.dao.ModInfoDao;
import com.wj.learnmvi.dao.PageInfoDao;
import com.wj.learnmvi.dao.UserInfoDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;

/**
 * Author:WJ
 * Date:2024/2/1 9:28
 * Describe：
 */
@Entity
public class ModInfo {
    @Id(autoincrement = true)
    private Long id;

    private String mod_name;
    private Long userId;

    @ToOne(joinProperty = "userId")
    private UserInfo userInfo;

    @ToMany(referencedJoinProperty = "ModId")
    private List<PageInfo> pageInfoList;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1080321499)
    private transient ModInfoDao myDao;
    @Generated(hash = 2066097151)
    private transient Long userInfo__resolvedKey;

    @Generated(hash = 1194343180)
    public ModInfo(Long id, String mod_name, Long userId) {
        this.id = id;
        this.mod_name = mod_name;
        this.userId = userId;
    }

    @Generated(hash = 451740618)
    public ModInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMod_name() {
        return this.mod_name;
    }

    public void setMod_name(String mod_name) {
        this.mod_name = mod_name;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 1827626095)
    public UserInfo getUserInfo() {
        Long __key = this.userId;
        if (userInfo__resolvedKey == null || !userInfo__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserInfoDao targetDao = daoSession.getUserInfoDao();
            UserInfo userInfoNew = targetDao.load(__key);
            synchronized (this) {
                userInfo = userInfoNew;
                userInfo__resolvedKey = __key;
            }
        }
        return userInfo;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1104177837)
    public void setUserInfo(UserInfo userInfo) {
        synchronized (this) {
            this.userInfo = userInfo;
            userId = userInfo == null ? null : userInfo.getId();
            userInfo__resolvedKey = userId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 278792292)
    public List<PageInfo> getPageInfoList() {
        if (pageInfoList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PageInfoDao targetDao = daoSession.getPageInfoDao();
            List<PageInfo> pageInfoListNew = targetDao
                    ._queryModInfo_PageInfoList(id);
            synchronized (this) {
                if (pageInfoList == null) {
                    pageInfoList = pageInfoListNew;
                }
            }
        }
        return pageInfoList;
    }

    public void setPageInfoList(List<PageInfo> pageInfoList) {
        this.pageInfoList = pageInfoList;
    }

    public  List<PageInfo> getPageInfos() {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        PageInfoDao targetDao = daoSession.getPageInfoDao();
        List<PageInfo> pageInfoListNew = targetDao
                ._queryModInfo_PageInfoList(id);
        pageInfoList = pageInfoListNew;
        return pageInfoList;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1346467491)
    public synchronized void resetPageInfoList() {
        pageInfoList = null;
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

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 396160904)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getModInfoDao() : null;
    }

    @Override
    public String toString() {
        return "ModInfo{" +
                "id=" + id +
                ", mod_name='" + mod_name + '\'' +
                ", userId=" + userId +
                ", userInfo=" + userInfo +
                '}';
    }
}
