package com.kkard.seoulroad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kkard.seoulroad.MyMenu.MyLikeActivity;
import com.kkard.seoulroad.MyMenu.MyPostActivity;
import com.kkard.seoulroad.MyMenu.NoticeActivity;


public class FragmentActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout drawer;
    private LinearLayout drawerLayout;
    private ImageView Mymenu;
    private String userId, userName;
    private TextView drawerName,drawerId,drawerWrite,drawerLike,drawerNotice,drawerModify,drawerLogout;
    ///////////////Back 버튼 2번 종료 관련 변수////////////
    private final long FINISH_INTERVAL_TIME = 2000; //2초안에 Back 버튼 누르면 종료
    private long   backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        InitView();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        SharedPreferences pre = getSharedPreferences("DB",MODE_PRIVATE);
        userId = pre.getString("G_ID","g_id error");
        userName = pre.getString("G_NAME","g_name error");

        tabLayout.addTab(tabLayout.newTab().setText("방문록"));
        tabLayout.addTab(tabLayout.newTab().setText("공연/행사"));
        tabLayout.addTab(tabLayout.newTab().setText("식물찾기"));
        tabLayout.addTab(tabLayout.newTab().setText("지도보기"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        Mymenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.RIGHT);
            }
        });
        drawerId.setText(userId);
        drawerName.setText(userName);
        drawerWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FragmentActivity.this, MyPostActivity.class));
                finish();
            }
        });
        drawerLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FragmentActivity.this, MyLikeActivity.class));
                finish();
            }
        });
        drawerNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FragmentActivity.this,NoticeActivity.class));
                finish();
            }
        });
        drawerModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FragmentActivity.this, ModifyActivity.class));
                finish();
            }
        });
        drawerLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh = getSharedPreferences("AutoINFO",MODE_PRIVATE);
                SharedPreferences.Editor editor = sh.edit();
                editor.putString("isAuto","false");
                editor.apply();
                startActivity(new Intent(FragmentActivity.this, LoginActivity.class));
                finish();
            }
        });
        drawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Nothing Do
            }
        });
    }

    private void InitView(){
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        Mymenu = (ImageView) findViewById(R.id.myMenu);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerId = (TextView)findViewById(R.id.drawer_id);
        drawerName = (TextView)findViewById(R.id.drawer_name);
        drawerWrite = (TextView)findViewById(R.id.drawer_write);
        drawerLike = (TextView)findViewById(R.id.drawer_like);
        drawerModify = (TextView)findViewById(R.id.drawer_modify);
        drawerNotice = (TextView)findViewById(R.id.drawer_notice);
        drawerLogout = (TextView)findViewById(R.id.drawer_logout);
        drawerLayout = (LinearLayout)findViewById(R.id.layout_right_drawer);
    }
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(Gravity.RIGHT)) {
            drawer.closeDrawer(Gravity.RIGHT);
        }else {
            long tempTime = System.currentTimeMillis();
            long intervalTime = tempTime - backPressedTime;

            if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
                super.onBackPressed();
            } else {
                backPressedTime = tempTime;
                Toast.makeText(getApplicationContext(), "종료를 원하시면 뒤로 버튼을 한 번 더 눌러주세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
