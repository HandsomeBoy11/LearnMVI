package com.wj.learnmvi.dao.bean;

import com.wj.learnmvi.dao.DaoSession;
import com.wj.learnmvi.dao.ModInfoDao;
import com.wj.learnmvi.dao.UserInfoDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * Author:WJ
 * Date:2024/2/1 9:28
 * Describe：
 */
@Entity
public class UserInfo {
    @Id(autoincrement = true)
    private Long id;
    private String user_name;
    private String user_id;
    private String version;

    @ToMany(referencedJoinProperty = "userId")
    private List<ModInfo> modInfoList;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 437952339)
    private transient UserInfoDao myDao;

    @Generated(hash = 1202653410)
    public UserInfo(Long id, String user_name, String user_id, String version) {
        this.id = id;
        this.user_name = user_name;
        this.user_id = user_id;
        this.version = version;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 269058287)
    public List<ModInfo> getModInfoList() {
        if (modInfoList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ModInfoDao targetDao = daoSession.getModInfoDao();
            List<ModInfo> modInfoListNew = targetDao._queryUserInfo_ModInfoList(id);
            synchronized (this) {
                if (modInfoList == null) {
                    modInfoList = modInfoListNew;
                }
            }
        }
        return modInfoList;
    }

    public void setModInfoList(List<ModInfo> list) {
        this.modInfoList = list;
    }

    public  List<ModInfo> getModInfos() {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        ModInfoDao targetDao = daoSession.getModInfoDao();
        List<ModInfo> modInfoListNew = targetDao._queryUserInfo_ModInfoList(id);
        modInfoList = modInfoListNew;
        return modInfoList;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 596674076)
    public synchronized void resetModInfoList() {
        modInfoList = null;
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
    @Generated(hash = 821180768)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserInfoDao() : null;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", user_id='" + user_id + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

}
