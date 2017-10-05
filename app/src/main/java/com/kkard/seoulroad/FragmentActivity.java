package com.kkard.seoulroad;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kkard.seoulroad.MyMenu.NoticeActivity;


public class FragmentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout drawer;
    private ImageView Mymenu;
    private String userId;
    ///////////////Back 버튼 2번 종료 관련 변수////////////
    private final long FINISH_INTERVAL_TIME = 2000; //2초안에 Back 버튼 누르면 종료
    private long   backPressedTime = 0;

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        }else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "종료를 원하시면 뒤로 버튼을 한 번 더 눌러주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("공연/행사"));
        tabLayout.addTab(tabLayout.newTab().setText("지도"));
        tabLayout.addTab(tabLayout.newTab().setText("방문록"));
        tabLayout.addTab(tabLayout.newTab().setText("식물"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);

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
        Mymenu = (ImageView) findViewById(R.id.myMenu);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Mymenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.RIGHT);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        TextView navId = (TextView)navigationView.getHeaderView(0).findViewById(R.id.nav_id);
        navId.setText(userId);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_my_post) {
            Toast.makeText(getApplicationContext(),"아직 못만듬",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_my_like) {
            Toast.makeText(getApplicationContext(),"아직 못만듬",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_fqa) {
            Toast.makeText(getApplicationContext(),"아직 못만듬",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_notice) {
            startActivity(new Intent(FragmentActivity.this,NoticeActivity.class));
            finish();
        } else if (id == R.id.menu_modify) {
            Toast.makeText(getApplicationContext(),"아직 못만듬",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_logout) {
            Intent intent = new Intent(FragmentActivity.this, LoginActivity.class);
            intent.putExtra("isAuto","false");
            startActivity(intent);
            finish();
        }
        return true;
    }
}
