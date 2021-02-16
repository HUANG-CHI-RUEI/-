package com.example.materialtest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;



import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class PartnerActivity extends AppCompatActivity {

    public static final String PARTNER_NAME = "partner_name";

    public static final String PARTNER_IMAGE_ID = "partner_image_id";
    public static final String PARTNER_PROFILE_ID = "partner_profile_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner);

        Intent intent = getIntent();
        String partnerName = intent.getStringExtra(PARTNER_NAME);
        int partnerImageId = intent.getIntExtra(PARTNER_IMAGE_ID, R.mipmap.partner_luffy);
        int partnerProfileId = intent.getIntExtra(PARTNER_PROFILE_ID, R.string.partner_luffy);

        Toolbar toolbar = findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        ImageView partnerImageView = findViewById(R.id.partner_image_view);
        TextView partnerProfile = findViewById(R.id.partner_profile);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(partnerName);
        Glide.with(this).load(partnerImageId).into(partnerImageView);
        String partnerContent = generatePartnerContent(partnerName);
        partnerProfile.setText(partnerContent);
    }



    private String generatePartnerContent(String partnerName) {
        StringBuilder partnerContent = new StringBuilder();
        for(int i = 0; i < 500; i++) {
            partnerContent.append(partnerName);
        }
        return partnerContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}