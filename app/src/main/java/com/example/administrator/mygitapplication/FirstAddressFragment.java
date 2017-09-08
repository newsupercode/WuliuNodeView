package com.example.administrator.mygitapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Unbinder;

import static com.example.administrator.mygitapplication.R.id.rl_mine_address_pop_title;

/**
 * Created by Administrator on 2017/9/1.
 */

public class FirstAddressFragment extends Fragment {
    Unbinder unbinder;
    private ArrayList logisticsDataList;
    private float touchY;
    private RelativeLayout popRootLayout;
    private CardView popLayout;
    private LinearLayout llMineTotalRoot;
    private RelativeLayout rlMineAddressPopTitle;
    private TextView title;
    private ImageView ivImgHead;
    private TextView content;
    private TextView tvKuaidi;
    private TextView tvTimeArrive;
    private ImageView ivArrow;
    private LogisticsInformationView logisticsInformationView;
    final int REQUEST_CODE = 0x1001;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_first_address, null);
        popRootLayout = (RelativeLayout) inflate.findViewById(R.id.pop_root_layout);
        popLayout = (CardView)  inflate.findViewById(R.id.pop_layout);
        llMineTotalRoot = (LinearLayout)  inflate.findViewById(R.id.ll_mine_total_root);
        rlMineAddressPopTitle = (RelativeLayout)  inflate.findViewById(rl_mine_address_pop_title);
        title = (TextView)  inflate.findViewById(R.id.title);
        ivImgHead = (ImageView)  inflate.findViewById(R.id.iv_img_head);
        content = (TextView)  inflate.findViewById(R.id.content);
        tvKuaidi = (TextView)  inflate.findViewById(R.id.tv_kuaidi);
        tvTimeArrive = (TextView)  inflate.findViewById(R.id.tv_time_arrive);
        ivArrow = (ImageView)  inflate.findViewById(R.id.iv_arrow);
        logisticsInformationView = (LogisticsInformationView) inflate. findViewById(R.id.logistics_InformationView);

        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
        addListeners();
    }

    protected void setData() {
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

//显示位置
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        popLayout.setLayoutParams(lp);
        logisticsInformationView.setLogisticsDataList(logisticsDataList);

        popRootLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
//
//                        if (builder.isBackgroundClose) {
//                            if (builder.showAtLocationType.equals(GRAVITY_CENTER)) {
//                                if (touchY < popLayout.getTop() || touchY > popLayout.getBottom()) {
//                                    popupWindow.dismiss();
//                                }
//                            } else if (builder.showAtLocationType.equals(GRAVITY_BOTTOM)) {
//                                if (touchY < popLayout.getTop()) {
//                                    popupWindow.dismiss();
//                                }
//                            }
//                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        title.setText("已到达");
        content.setText("该配合你演出的我,眼视而不见,在比一个最爱你的人即兴表演");
//电话操作
        initPermissionChecker();
    }

    /**
     * 当系统为6.0以上都要手动配置权限
     * 如果想避免手动配置权限，在gradle中配置targetSdkVersion 23以下即可，不包括23
     */
    private void initPermissionChecker() {
        if (Build.VERSION.SDK_INT >= 23) {

            //判断有没有拨打电话权限
            if (PermissionChecker.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                //请求拨打电话权限
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);

            }

        }
    }

//    @Override
    protected void addListeners() {
        //点击进入详情
        rlMineAddressPopTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转详情页
            }
        });


    }


    /**
     * 请求权限的回调方法
     *
     * @param requestCode  请求码
     * @param permissions  请求的权限
     * @param grantResults 权限的结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE && PermissionChecker.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            //开启成功
        } else {
            Toast.makeText(getActivity(), "拨打电话授权失败，请在设置中手动开启", Toast.LENGTH_LONG);
        }
    }


}
