package cn.hxek.com;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ives.cn.XTool;
import com.ives.cn.permission.AppSettingsDialog;
import com.ives.cn.permission.EasyPermissions;

import java.util.List;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {


    /**
     *     友盟推送手动集成demo
     */

    String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
            , Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_PHONE_STATE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean b = XTool.checkPermission(this, perms);
        if (b == false) {
            XTool.requestPermission(this, "我需要你！", 90, perms);
        } else {
            Toast.makeText(this, "已经请求过了", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

        Toast.makeText(this, "请求成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //将请求结果传递EasyPermission库处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //当从软件设置界面，返回当前程序时候
            case AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE:
                break;
            default:
                break;
        }
    }
}
