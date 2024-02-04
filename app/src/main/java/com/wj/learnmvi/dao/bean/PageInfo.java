package com.wj.learnmvi.dao.bean;

import com.wj.learnmvi.dao.BtnInfoDao;
import com.wj.learnmvi.dao.DaoSession;
import com.wj.learnmvi.dao.ModInfoDao;
import com.wj.learnmvi.dao.PageInfoDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;

/**
 * Author:WJ
 * Date:2024/2/1 9:27
 * Describe：
 */
@Entity
public class PageInfo {
    @Id(autoincrement = true)
    private Long id;
    private String view_name;

    private Long ModId;
    @ToOne(joinProperty = "ModId")
    private ModInfo modInfo;

    @ToMany(referencedJoinProperty = "PageId")
    private List<BtnInfo> btnInfoList;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 393336655)
    private transient PageInfoDao myDao;
    @Generated(hash = 19169358)
    private transient Long modInfo__resolvedKey;

    @Generated(hash = 1835297590)
    public PageInfo(Long id, String view_name, Long ModId) {
        this.id = id;
        this.view_name = view_name;
        this.ModId = ModId;
    }

    @Generated(hash = 40275086)
    public PageInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getView_name() {
        return this.view_name;
    }

    public void setView_name(String view_name) {
        this.view_name = view_name;
    }

    public Long getModId() {
        return this.ModId;
    }

    public void setModId(Long ModId) {
        this.ModId = ModId;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 967221103)
    public ModInfo getModInfo() {
        Long __key = this.ModId;
        if (modInfo__resolvedKey == null || !modInfo__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ModInfoDao targetDao = daoSession.getModInfoDao();
            ModInfo modInfoNew = targetDao.load(__key);
            synchronized (this) {
                modInfo = modInfoNew;
                modInfo__resolvedKey = __key;
            }
        }
        return modInfo;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 506645020)
    public void setModInfo(ModInfo modInfo) {
        synchronized (this) {
            this.modInfo = modInfo;
            ModId = modInfo == null ? null : modInfo.getId();
            modInfo__resolvedKey = ModId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 45267972)
    public List<BtnInfo> getBtnInfoList() {
        if (btnInfoList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BtnInfoDao targetDao = daoSession.getBtnInfoDao();
            List<BtnInfo> btnInfoListNew = targetDao._queryPageInfo_BtnInfoList(id);
            synchronized (this) {
                if (btnInfoList == null) {
                    btnInfoList = btnInfoListNew;
                }
            }
        }
        return btnInfoList;
    }

    public void setBtnInfoList(List<BtnInfo> btnInfoList) {
        this.btnInfoList = btnInfoList;
    }

    public  List<BtnInfo> getBtnInfos() {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        BtnInfoDao targetDao = daoSession.getBtnInfoDao();
        List<BtnInfo> btnInfoListNew = targetDao._queryPageInfo_BtnInfoList(id);
        btnInfoList = btnInfoListNew;
        return btnInfoList;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 619216547)
    public synchronized void resetBtnInfoList() {
        btnInfoList = null;
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
    @Generated(hash = 1478294379)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPageInfoDao() : null;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "id=" + id +
                ", view_name='" + view_name + '\'' +
                ", ModId=" + ModId +
                ", modInfo=" + modInfo +
                '}';
    }
}
