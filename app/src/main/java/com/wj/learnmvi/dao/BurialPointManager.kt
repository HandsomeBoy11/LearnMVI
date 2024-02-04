package com.wj.learnmvi.dao

import com.google.gson.Gson
import com.wj.learnmvi.dao.bean.BtnInfo
import com.wj.learnmvi.dao.bean.ModInfo
import com.wj.learnmvi.dao.bean.PageInfo
import com.wj.learnmvi.dao.bean.UserInfo

/**
 *  Author:WJ
 *  Date:2024/2/4 10:13
 *  Describe：
 */
object BurialPointManager {
    /**
     * 添加按钮
     */
     fun addBtn(userName: String, modeName: String, pageName: String, btnName: String) {
        val userQueryBuilder = SQLiteUtil.getInstance().userInfoDao.queryBuilder()
        var user = userQueryBuilder.where(UserInfoDao.Properties.User_name.eq(userName)).list()
            .firstOrNull()
        println("用户 $user")
        user ?: run {
            user = UserInfo().apply {
                user_id = "100"
                user_name = userName
                version = "2"
            }
            SQLiteUtil.getInstance().userInfoDao.insert(user)
            println("新添加用户 $user")
        }
        var modInfo = user!!.modInfos?.firstOrNull { mod -> mod.mod_name == modeName }
        println("模块 $modInfo")
        modInfo ?: run {
            modInfo = ModInfo().apply {
                mod_name = modeName
                userId = user!!.id
            }
            SQLiteUtil.getInstance().modInfoDao.insert(modInfo)
            println("新添加模块 $modInfo")
        }
        var pageInfo = modInfo!!.pageInfos?.firstOrNull { page -> page.view_name == pageName }
        println("页面 $pageInfo")
        pageInfo ?: run {
            pageInfo = PageInfo().apply {
                view_name = pageName
                modId = modInfo!!.id
            }
            SQLiteUtil.getInstance().viewInfoDao.insert(pageInfo)
            println("新添加页面 $modInfo")
        }
        var btnInfo = pageInfo!!.btnInfos?.firstOrNull { btn -> btn.button_name == btnName }
        println("按钮 $btnInfo")
        btnInfo?.let {
            it.click_num += 1
            it.update()
        } ?: run {
            btnInfo = BtnInfo().apply {
                button_name = btnName
                click_num = 1
                pageId = pageInfo!!.id
                userId = user!!.id
            }
            SQLiteUtil.getInstance().btnInfoDao.insert(btnInfo)
            println("新添加按钮 $btnInfo")
        }
    }

     fun deleteUserBtn(userName: String) {
        val userList = SQLiteUtil.getInstance().userInfoDao.queryBuilder()
            .where(UserInfoDao.Properties.User_name.eq(userName))
            .list()
        SQLiteUtil.getInstance().userInfoDao.deleteInTx(userList)
        userList.forEach {
            val btnList = SQLiteUtil.getInstance().btnInfoDao.queryBuilder()
                .where(BtnInfoDao.Properties.UserId.eq(it.id))
                .list()
            SQLiteUtil.getInstance().btnInfoDao.deleteInTx(btnList)
        }
    }

    fun deleteAll() {
        SQLiteUtil.getInstance().btnInfoDao.deleteAll()
        SQLiteUtil.getInstance().userInfoDao.deleteAll()
    }

     fun findAll() {
        val list = SQLiteUtil.getInstance().userInfoDao.queryBuilder().list()
        list.map { user ->
            user.modInfoList = user.modInfos
            user.modInfoList.map { mod ->
                mod.pageInfoList = mod.pageInfos
                mod.pageInfoList.map { page ->
                    page.btnInfoList = page.btnInfos
                    page
                }
                mod
            }
            user
        }
        println("wj==>全部：+${Gson().toJson(list)}")
    }

}