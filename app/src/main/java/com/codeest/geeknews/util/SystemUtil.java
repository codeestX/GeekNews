package com.codeest.geeknews.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.codeest.geeknews.app.App;
import com.codeest.geeknews.app.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by codeest on 2016/8/4.
 */
public class SystemUtil {

    /**
     * 检查WIFI是否连接
     *
     * @author Ysjian
     * @date 2014-5-9
     * @return 如果连接了返回true，否则返回false
     */
    public static boolean isWifiConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiInfo != null;
    }
    /**
     * 检查手机网络(4G/3G/2G)是否连接
     *
     * @author Ysjian
     * @date 2014-5-9
     * @return 如果连接了返回true，否则返回false
     */
    public static boolean isMobileNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetworkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return mobileNetworkInfo != null;
    }
    /**
     * 检查是否有可用网络
     *
     * @author Ysjian
     * @date 2014-5-9
     * @return 存在WIFI和手机数据任意可用网络返回true，否则返回false
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    /**
     * 保存文字到剪贴板
     * @param context
     * @param text
     */
    public static void copyToClipBoard(Context context, String text) {
        ClipData clipData = ClipData.newPlainText("url", text);
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(clipData);
        ToastUtil.shortShow("已复制到剪贴板");
    }

    /**
     * 保存图片到本地
     * @param context
     * @param url
     * @param bitmap
     */
    public static void saveBitmapToFile(Context context, String url, Bitmap bitmap){
        String fileName = url.substring(url.lastIndexOf("/"),url.lastIndexOf(".")) + ".png";
        File fileDir = new File(Constants.DATA_PATH);
        if (!fileDir.exists()){
            fileDir.mkdir();
        }
        File imageFile = new File(fileDir,fileName);
        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            boolean isCompress = bitmap.compress(Bitmap.CompressFormat.PNG, 256, fos);
            if (isCompress) {
                ToastUtil.shortShow("图片保存成功");
            } else {
                ToastUtil.shortShow("图片保存失败");
            }
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri uri = Uri.fromFile(imageFile);
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,uri));
    }
}
