package com.example.materialtest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout swipeRefresh;

    private Partner[] partners = {
            new Partner("路夫",R.mipmap.partner_luffy, R.string.partner_luffy),
            new Partner("索隆",R.mipmap.partner_zoro, R.string.partner_zoro),
            new Partner("山治",R.mipmap.partner_sanji,R.string.partner_sanji),
            new Partner("艾斯",R.mipmap.partner_ace,R.string.partner_ace),
            new Partner("羅",R.mipmap.partner_law,R.string.partner_law),
            new Partner("娜美",R.mipmap.partner_nami,R.string.partner_nami),
            new Partner("羅賓",R.mipmap.partner_robin,R.string.partner_robin),
            new Partner("薇薇",R.mipmap.partner_vivi,R.string.partner_vivi),
            new Partner("蕾貝卡",R.mipmap.partner_rebecca,R.string.partner_rebecca),
            new Partner("漢考克",R.mipmap.partner_hancock,R.string.partner_hancock)};


    private List<Partner> partnerList = new ArrayList<>();

    private PartnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Data restored", Toast.LENGTH_SHORT).show();
                    }
                }).show());

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menuicon);
        }

        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(item -> {
            mDrawerLayout.closeDrawers();
            return true;
        });

        initPartner();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PartnerAdapter(partnerList);
        recyclerView.setAdapter(adapter);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.design_default_color_primary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshPartner();
            }
        });
    }

    private void refreshPartner() {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> {
                initPartner();
                adapter.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);
            });
        }).start();
    }

    private void initPartner() {
        partnerList.clear();
        for(int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(partners.length);
            partnerList.add(partners[index]);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "你按了備份", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "你按了刪除", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "設定", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}