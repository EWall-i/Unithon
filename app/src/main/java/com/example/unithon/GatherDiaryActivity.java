package com.example.unithon;

import static com.example.unithon.DummyData.currentUser;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GatherDiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gather_diary);

        Intent intent = getIntent();
        int diary_num = intent.getIntExtra("diary_num", -1);

        Model.Diary diary;
        diary = currentUser.getDiaries().get(diary_num).getDiary();

        GridView gridView = findViewById(R.id.pages_grid_view);

        PagesGridAdapter gridAdapter = new PagesGridAdapter(diary.getPages(), diary_num);
        gridView.setAdapter(gridAdapter);
    }
}
