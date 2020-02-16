package com.parade.baseproject.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 *author: parade岁月
 *date:  2020/2/15 14:45
 *description：
 */
public class CommonUtils {
    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public static void diallPhone(Context context, String phoneNum) {
        if (TextUtils.isEmpty(phoneNum))return;
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 从assets资源下读取json文件
     * @param context 上下文
     * @param fileName 文件名称 assets下的相对路径
     * @return 读取的json字符串
     */
   public static String getJsonFromeAsset(Context context,String fileName){
       StringBuilder builder = new StringBuilder();

       try {
           AssetManager assetManager = context.getAssets();
           BufferedReader reader = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
           String line;
           while ((line = reader.readLine()) != null){
               builder.append(line);
           }
       } catch (IOException e) {
           e.printStackTrace();
       }

       return builder.toString();
   }
}
