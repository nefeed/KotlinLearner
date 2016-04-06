package com.gavin.kotlinlearner.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-04-06
 * Time: 14:45
 */
public class NavigationBarUtil {

    public static int getNavigationBarHeight(Activity activity) {
        if (checkDeviceHasNavigationBar(activity)) {
            Resources resources = activity.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height",
                    "dimen", "android");
            //获取Navigationar的高度
            return resources.getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }

    private static boolean checkDeviceHasNavigationBar(Context activity) {

        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
        boolean hasMenuKey = ViewConfiguration.get(activity)
                .hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap
                .deviceHasKey(KeyEvent.KEYCODE_BACK);

        if (!hasMenuKey && !hasBackKey) {
            // 做任何你需要做的,这个设备有一个导航栏
            return true;
        }
        return false;
    }
}
