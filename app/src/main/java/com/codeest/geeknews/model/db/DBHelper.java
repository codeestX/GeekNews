package com.codeest.geeknews.model.db;

import com.codeest.geeknews.model.bean.GoldManagerBean;
import com.codeest.geeknews.model.bean.RealmLikeBean;

import java.util.List;

/**
 * @author: Est <codeest.dev@gmail.com>
 * @date: 2017/4/21
 * @description:
 */

public interface DBHelper {

    void insertNewsId(int id);

    /**
     * 查询 阅读记录
     * @param id
     * @return
     */
    boolean queryNewsId(int id);

    /**
     * 增加 收藏记录
     * @param bean
     */
    void insertLikeBean(RealmLikeBean bean);

    /**
     * 删除 收藏记录
     * @param id
     */
    void deleteLikeBean(String id);

    /**
     * 查询 收藏记录
     * @param id
     * @return
     */
    boolean queryLikeId(String id);

    List<RealmLikeBean> getLikeList();

    /**
     * 修改 收藏记录 时间戳以重新排序
     * @param id
     * @param time
     * @param isPlus
     */
    void changeLikeTime(String id ,long time, boolean isPlus);

    /**
     * 更新 掘金首页管理列表
     * @param bean
     */
    void updateGoldManagerList(GoldManagerBean bean);

    /**
     * 获取 掘金首页管理列表
     * @return
     */
    GoldManagerBean getGoldManagerList();
}
