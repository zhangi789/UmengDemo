package cn.hxek.com.view;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import cn.hxek.com.bean.PushCustomeBean;


/**
 * releace
 * 作者：gdy on 2018/1/4:19.
 * 邮箱：240767446@qq.com
 */

public class CustomNotificationHandler extends UmengNotificationClickHandler {

    @Override
    public void dismissNotification(Context context, UMessage msg) {


        Log.i("GGG ","dismissNotification"+msg.getRaw().toString());
        super.dismissNotification(context, msg);
    }

    @Override
    public void launchApp(Context context, UMessage msg) {

        Log.i("GGG ","launchApp"+msg.getRaw().toString());
        super.launchApp(context, msg);


    }

    @Override
    public void openActivity(Context context, UMessage msg) {

        Log.i("GGG ","openActivity"+msg.getRaw().toString());
        super.openActivity(context, msg);


    }

    @Override
    public void openUrl(Context context, UMessage msg) {

        Log.i("GGG ","openUrl"+msg.getRaw().toString());
        super.openUrl(context, msg);


    }

    @Override
    public void dealWithCustomAction(final Context context, UMessage msg) {
        Log.i("GGG ","dealWithCustomAction"+msg.getRaw().toString());
        super.dealWithCustomAction(context, msg);

    }
    private void gotoActivity(PushCustomeBean bean, Context context) {
        switch (bean.getType()) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 7:

                break;
        }
    }

    @Override
    public void autoUpdate(Context context, UMessage msg) {
        super.autoUpdate(context, msg);

    }

    /**
     * 判断app是否处于前台
     *
     * @param context
     *
     * @return
     */
    public boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1)
                .get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
            return true;
        }
        return false;
    }

    private void gotoActivityByBundle(Context context, Class activity, Bundle bundle) {

    }
}
