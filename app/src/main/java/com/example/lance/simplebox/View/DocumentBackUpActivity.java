package com.example.lance.simplebox.View;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.example.lance.simplebox.Content.Content;
import com.example.lance.simplebox.R;

import java.security.PrivateKey;

/**
 * Created by xiyu0 on 2017/10/31.
 */

public class DocumentBackUpActivity extends AppCompatActivity{

    private Toolbar toolbar;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_back_up);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.font));
        toolbar.setTitle("文档备份");
    }
}
