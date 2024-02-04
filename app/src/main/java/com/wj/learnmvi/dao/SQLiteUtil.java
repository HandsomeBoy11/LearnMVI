package com.wj.learnmvi.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Author:WJ
 * Date:2022/9/28 16:03
 * Describe：数据库工具类
 */
public class SQLiteUtil {
    /**
     * 数据库名称
     */
    private static final String DB_NAME = "newBuriedPoint.db";
    /**
     * 是否初始化了数据库
     */
    public boolean isInitDb;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private UserInfoDao userInfoDao;
    private ModInfoDao modInfoDao;
    private PageInfoDao viewInfoDao;
    private BtnInfoDao btnInfoDao;

    private SQLiteUtil() {

    }

    public static SQLiteUtil getInstance() {
        return Holder.sqLiteUtil;
    }

    public void initDb(Context context) {
        BuriedPointOpenHelper helper = new BuriedPointOpenHelper(context, DB_NAME);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        userInfoDao = daoSession.getUserInfoDao();
        modInfoDao = daoSession.getModInfoDao();
        viewInfoDao = daoSession.getPageInfoDao();
        btnInfoDao = daoSession.getBtnInfoDao();
        isInitDb = true;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public ModInfoDao getModInfoDao() {
        return modInfoDao;
    }

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

    public PageInfoDao getViewInfoDao() {
        return viewInfoDao;
    }

    public BtnInfoDao getBtnInfoDao() {
        return btnInfoDao;
    }

    /* *//**
     * 添加用户
     *
     * @param dataBean
     * @return
     *//*
    public long addUserData(UserInfo dataBean, ButtonInfoBean buttonInfoBean) {
        dataBean.setButtonInfoBean(buttonInfoBean);
        long insert = userInfoDao.insert(dataBean);
        return insert;
    }

    *//**
     * 添加模块
     *
     * @param dataBean
     * @return
     *//*
    public long addModData(ModInfoBean dataBean) {
        long insert = modInfoDao.insert(dataBean);
        return insert;
    }

    *//**
     * 添加页面
     *
     * @param dataBean
     * @param modInfoBean
     * @return
     *//*
    public long addViewData(ViewInfoBean dataBean, ModInfoBean modInfoBean) {
        dataBean.setModInfoBean(modInfoBean);
        long insert = viewInfoDao.insert(dataBean);
        return insert;
    }

    *//**
     * 添加按钮
     *
     * @param dataBean
     * @param viewInfoBean
     * @return
     *//*
    public long addBtnData(ButtonInfoBean dataBean, ViewInfoBean viewInfoBean) {
        dataBean.setViewInfoBean(viewInfoBean);
        long insert = btnInfoDao.insert(dataBean);
        return insert;
    }

    *//**
     * 查询所有
     *
     * @return
     *//*
    public List<UserInfo> findAll() {
        List<UserInfo> userList = userInfoDao.queryBuilder().list();
        return userList;
    }
*/
    /**
     * 删除所有
     */
//    public void deleteAll() {
//        userInfoDao.deleteAll();
//    }

    static class Holder {
        private static SQLiteUtil sqLiteUtil = new SQLiteUtil();
    }
}
