package com.example.administrator.mygitapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

//tou透明度
public class DialogAddressActivity extends FragmentActivity {

    private int ONE_ALPHE = 10;
    private ViewPager vpAddressDialog;
    private TabLayout tbAddressDialog;
    private LinearLayout llClose;
    private ImageView close;
    ArrayList<Fragment> list;
    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_address);
        vpAddressDialog = (ViewPager) findViewById(R.id.vp_address_dialog);
        tbAddressDialog = (TabLayout) findViewById(R.id.tb_address_dialog);
        llClose = (LinearLayout) findViewById(R.id.ll_close);
        close = (ImageView) findViewById(R.id.close);
        //页面，数据源
        list = new ArrayList<>();
        list.add(new FirstAddressFragment());
        list.add(new FirstAddressFragment());
        myAdapter = new MyAdapter(getSupportFragmentManager());
        vpAddressDialog.setAdapter(myAdapter);
        //绑定
        tbAddressDialog.setupWithViewPager(vpAddressDialog);

        for (int i = 0; i < myAdapter.getCount(); i++) {
            TabLayout.Tab tab = tbAddressDialog.getTabAt(i);//获得每一个tab
            tab.setCustomView(R.layout.tab_item_dialog_address);//给每一个tab设置view
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                ImageView imageView = tab.getCustomView().findViewById(R.id.iv_indicator);
                imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.viewpager2));
                imageView.setSelected(true);

            } else {
                //其他的灰色
                ImageView imageView = tab.getCustomView().findViewById(R.id.iv_indicator);
                imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.viewpager3));
                imageView.setSelected(false);
            }


            tbAddressDialog.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    ImageView imageView = tab.getCustomView().findViewById(R.id.iv_indicator);
                    imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.viewpager2));
                    imageView.setSelected(true);
                    vpAddressDialog.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    ImageView imageView = tab.getCustomView().findViewById(R.id.iv_indicator);
                    imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.viewpager3));
                    imageView.setSelected(false);

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
        llClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
