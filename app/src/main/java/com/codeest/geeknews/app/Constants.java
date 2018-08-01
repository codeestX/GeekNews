package com.codeest.geeknews.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by codeest on 2016/8/3.
 */
public class Constants {

    //================= TYPE ====================

    public static final int TYPE_ZHIHU = 101;

    public static final int TYPE_ANDROID = 102;

    public static final int TYPE_IOS = 103;

    public static final int TYPE_WEB = 104;

    public static final int TYPE_GIRL = 105;

    public static final int TYPE_WECHAT = 106;

    public static final int TYPE_GANK = 107;

    public static final int TYPE_GOLD = 108;

    public static final int TYPE_VTEX = 109;

    public static final int TYPE_SETTING = 110;

    public static final int TYPE_LIKE = 111;

    public static final int TYPE_ABOUT = 112;

    //================= KEY ====================

//    public static final String KEY_API = "f95283476506aa756559dd28a56f0c0b"; //需要APIKEY请去 http://apistore.baidu.com/ 申请,复用会减少访问可用次数
    public static final String KEY_API = "52b7ec3471ac3bec6846577e79f20e4c"; //需要APIKEY请去 http://www.tianapi.com/#wxnew 申请,复用会减少访问可用次数

    public static final String KEY_ALIPAY = "aex07566wvayrgxicnaraae";

    public static final String LEANCLOUD_ID = "mhke0kuv33myn4t4ghuid4oq2hjj12li374hvcif202y5bm6";

    public static final String LEANCLOUD_SIGN = "badc5461a25a46291054b902887a68eb,1480438132702";

    public static final String BUGLY_ID = "257700f3f8";

    public static final String FILE_PROVIDER_AUTHORITY="com.codeest.geeknews.fileprovider";

    //================= PATH ====================

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "codeest" + File.separator + "GeekNews";

    //================= PREFERENCE ====================

    public static final String SP_NIGHT_MODE = "night_mode";

    public static final String SP_NO_IMAGE = "no_image";

    public static final String SP_AUTO_CACHE = "auto_cache";

    public static final String SP_CURRENT_ITEM = "current_item";

    public static final String SP_LIKE_POINT = "like_point";

    public static final String SP_VERSION_POINT = "version_point";

    public static final String SP_MANAGER_POINT = "manager_point";

    //================= INTENT ====================
    public static final String IT_GANK_TYPE = "gank_type";

    public static final String IT_GANK_TYPE_CODE = "gank_type_code";

    public static final String IT_GANK_DETAIL_TITLE = "gank_detail_title";

    public static final String IT_GANK_DETAIL_URL = "gank_detail_url";

    public static final String IT_GANK_DETAIL_IMG_URL = "gank_detail_img_url";

    public static final String IT_GANK_DETAIL_ID = "gank_detail_id";

    public static final String IT_GANK_DETAIL_TYPE = "gank_detail_type";

    public static final String IT_GANK_GRIL_ID = "gank_girl_id";

    public static final String IT_GANK_GRIL_URL = "gank_girl_url";

    public static final String IT_GOLD_TYPE = "gold_type";

    public static final String IT_GOLD_TYPE_STR = "gold_type_str";

    public static final String IT_GOLD_MANAGER = "gold_manager";

    public static final String IT_VTEX_TYPE = "vtex_type";

    public static final String IT_VTEX_TOPIC_ID = "vtex_id";

    public static final String IT_VTEX_REPLIES_TOP = "vtex_replies_top";

    public static final String IT_VTEX_NODE_NAME = "vtex_node_name";

    public static final String IT_ZHIHU_DETAIL_ID = "zhihu_detail_id";

    public static final String IT_ZHIHU_DETAIL_TRANSITION = "zhihu_detail_transition";

    public static final String IT_ZHIHU_THEME_ID = "zhihu_theme_id";

    public static final String IT_ZHIHU_SECTION_ID = "zhihu_section_id";

    public static final String IT_ZHIHU_SECTION_TITLE = "zhihu_section_title";

    public static final String IT_ZHIHU_COMMENT_ID = "zhihu_comment_id";

    public static final String IT_ZHIHU_COMMENT_ALL_NUM = "zhihu_comment_all_num";

    public static final String IT_ZHIHU_COMMENT_SHORT_NUM = "zhihu_comment_short_num";

    public static final String IT_ZHIHU_COMMENT_LONG_NUM = "zhihu_comment_long_num";
}
