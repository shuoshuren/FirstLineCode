package com.example.xiao.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FruitActivity extends AppCompatActivity {

    public static final String FRUIT_IMAGE_ID = "fruit_image_id";
    public static final String FRUIT_TEXT = "fruit_name";

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView fruitImageView;
    private TextView fruitContentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_TEXT);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID,R.mipmap.ic_launcher_round);

        toolbar = (Toolbar) findViewById(R.id.activity_fruit_toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        fruitImageView = (ImageView) findViewById(R.id.activity_fruit_image);
        fruitContentText = (TextView) findViewById(R.id.activity_fruit_text);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(fruitName);

        Glide.with(this).load(fruitImageId).into(fruitImageView);
        String fruitContent = generateFruitContent(fruitName);
        fruitContentText.setText(fruitContent);



    }

    private String generateFruitContent(String fruitName) {
        StringBuilder sb = new StringBuilder(fruitName);
        for (int i = 0; i < 500; i++) {
            sb.append(fruitName);
        }
        return sb.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
