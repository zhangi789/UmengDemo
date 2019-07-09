package cn.hxek.com;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import cn.hxek.com.util.CPoolUtil;
import cn.hxek.com.view.CustomNotificationHandler;

/**
 * @author 张海洋
 * @Date on 2019/07/09.
 * @org 上海..科技有限公司
 * @describe
 */
public class MyApi extends Application {

    private PushAgent mPushAgent;

    @Override
    public void onCreate() {
        super.onCreate();
        initUmengPush();
    }

    private void initUmengPush() {
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(this, CPoolUtil.AppKey, "Umeng", UMConfigure.DEVICE_TYPE_PHONE, CPoolUtil.Umeng_Message_Secret);

        //注册
        mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken
                //注册成功会返回device token
                Log.i("GGG", "deviceToken: " + deviceToken);

            }

            @Override
            public void onFailure(String s, String s1) {

                Log.i("GGG", "s: " + s + " s1" + s1);
            }
        });


        //接收数据  第一种
        UmengMessageHandler umengMessageHandler = new UmengMessageHandler() {
            /**
             * 自定义通知栏样式的回调方法
             */
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                Log.i("GGG", "普通消息" + msg.getRaw().toString());
                switch (msg.builder_id) {
                    //这个id是后台填写的自定义样式
                    case 1:
                        Notification.Builder builder = new Notification.Builder(context);
                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(),
                                R.layout.notification_view);
                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
//                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
                        myNotificationView.setImageViewResource(R.id.notification_small_icon,
                                getSmallIconId(context, msg));
                        builder.setContent(myNotificationView)
                                .setSmallIcon(getSmallIconId(context, msg))
                                .setTicker(msg.ticker)
                                .setAutoCancel(true);

                        return builder.getNotification();
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }

            @Override
            public void dealWithCustomMessage(Context context, UMessage uMessage) {
                //自定义消息。不是通知类型的推送。
                Log.i("GGG", "自定义消息" + uMessage.getRaw().toString());
                super.dealWithCustomMessage(context, uMessage);
            }
        };
      //  mPushAgent.setMessageHandler(umengMessageHandler);
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {

            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                //这里处理的是自定义行为。
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                Log.i("GGG", "click");
            }
        };
      //  mPushAgent.setNotificationClickHandler(notificationClickHandler);



        //接收数据  第二种   自定义


        mPushAgent.setNotificationClickHandler(new CustomNotificationHandler());
        mPushAgent.setDisplayNotificationNumber(3);//通知栏消息数量

    }
}
