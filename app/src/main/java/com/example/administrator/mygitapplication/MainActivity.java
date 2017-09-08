package com.example.administrator.mygitapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener {
    RelativeLayout rl_address;
    RelativeLayout rl_address_wuliu;
    private ArrayList<Object> logisticsDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
        rl_address = findViewById(R.id.rl_address);
        rl_address_wuliu = findViewById(R.id.rl_address_wuliu);

        rl_address.setOnClickListener(this);
        rl_address_wuliu.setOnClickListener(this);

    }

    private void setData() {
        logisticsDataList = new ArrayList<>();
        logisticsDataList.add(new LogisticsData().setTime("07:11").setDate("01-20").setContext("[杭州市]浙江杭州江干公司派件员：吕敬桥15555555551  正在为您派件正在为您派件正在为您派件正在为您派件正在为您派件正在为您派件正在为您派件"));
        logisticsDataList.add(new LogisticsData().setTime("23:11").setDate("01-19").setContext("[杭州市]浙江派件员：吕敬桥  正在为您派件"));
        logisticsDataList.add(new LogisticsData().setTime("23:08").setDate("01-19").setContext("[杭州市]员：吕敬桥  正在为您派件"));
        logisticsDataList.add(new LogisticsData().setTime("11:23").setDate("01-19").setContext("[杭州市]浙江杭州江干区新杭派公司进行签收扫描，快件已被  已签收  签收，感谢使用韵达快递，期待再次为您服务"));
        logisticsDataList.add(new LogisticsData().setTime("15:52").setDate("01-19").setContext("[泰州市]韵达快递  江苏靖江市公司收件员  已揽件"));
        logisticsDataList.add(new LogisticsData().setTime("12:39").setDate("01-19").setContext("包裹正等待揽件"));
        logisticsDataList.add(new LogisticsData().setTime("15:13").setDate("12-28").setContext("快件在【相城中转仓】装车,正发往【无锡分拨中心】已签收,签收人是【王漾】,签收网点是【忻州原平】"));
        logisticsDataList.add(new LogisticsData().setTime("11:23").setDate("01-19").setContext("[杭州市]浙江杭州江干区新杭派公司进行签收扫描，快件已被  已签收  签收，感谢使用韵达快递，期待再次为您服务"));
        logisticsDataList.add(new LogisticsData().setTime("06:48").setDate("01-19").setContext("到达目的地网点浙江杭州江干区新杭派，快件很快进行派送"));
        logisticsDataList.add(new LogisticsData().setTime("23:11").setDate("01-19").setContext("[苏州市]江苏苏州分拨中心  已发出"));
        logisticsDataList.add(new LogisticsData().setTime("23:08").setDate("01-19").setContext("[苏州市]快件已到达  江苏苏州分拨中心"));
        logisticsDataList.add(new LogisticsData().setTime("15:52").setDate("01-19").setContext("[泰州市]韵达快递  江苏靖江市公司收件员  已揽件"));
        logisticsDataList.add(new LogisticsData().setTime("12:39").setDate("01-19").setContext("菜鸟驿站代收，请及时取件，如有疑问请联系 程先生:18061208980"));

    }

    //恢复透明度
    @Override
    protected void onResume() {
        super.onResume();
        darkenBackground(1.0f);
    }

    /**
     * 改变背景颜色
     */
    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_address:
                //activity+透明度
                Intent intent = new Intent(MainActivity.this, DialogAddressActivity.class);
                darkenBackground(0.4f);
                startActivity(intent);
                break;
            case R.id.rl_address_wuliu:
                //popwindow+高斯模糊
                new BlurPopWin.Builder(this).setContent("该配合你演出的我,眼视而不见,在比一个最爱你的人即兴表演")
                        //Radius越大耗时越长,被图片处理图像越模糊
                        .setRadius(3).setTitle("已到达")
                        //设置居中还是底部显示
                        .setshowAtLocationType(0)
                        .setShowCloseButton(true)
                        .setOutSideClickable(false)
                        .SetLogisticsDataList(logisticsDataList)
                        .onphoneclock(new BlurPopWin.OnPhoneClickListener() {
                            @Override
                            public void onphoneclock(String phoneNumber) {
//                                dialogCreateCall(phoneNumber);
                                //实现拨打电话的操作
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                                        + phoneNumber));
                                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)
                                        != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                startActivity(intent);
                            }
                        })
//                       .onClick(new BlurPopWin.PopupCallback() {
//                            @Override
//                            public void onClick(@NonNull BlurPopWin blurPopWin) {
//                                Toast.makeText(mContext, "中间被点了", Toast.LENGTH_SHORT).show();
//                                //跳转详情页
//                                Intent intent = new Intent(mContext, AddressInfoActivity.class);
//                                mContext.startActivity(intent);
//
//
//                                blurPopWin.dismiss();
//                            }
//                        })
                        .show(view);

                break;
        }

    }

}
