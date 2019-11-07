package com.android.photocaptureviewer.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.photocaptureviewer.R;
import com.android.photocaptureviewer.utils.ImageUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ImagePreviewActivity extends AppCompatActivity {
    public static final String IMAGE_PATH = "image_path";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        String path = getIntent().getStringExtra(IMAGE_PATH);
        ImageView previewImage = findViewById(R.id.preview_img);
        previewImage.setImageBitmap(ImageUtils.getImageBitmap(this, path));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
