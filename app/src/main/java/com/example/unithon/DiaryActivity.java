package com.example.unithon;

import static com.example.unithon.DummyData.MAX_MEMBER;
import static com.example.unithon.DummyData.currentUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unithon.DiaryDetail.ReadActivity;
import com.example.unithon.Member.MemberActivity;

public class DiaryActivity extends AppCompatActivity {

    TextView memberTv, turnTv;
    ImageView imageView;
    Context context;
    Button joinBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        context = this;

        Intent intent = getIntent();
        int diary_num = intent.getIntExtra("diary_num", -1);
        boolean is_mine = intent.getBooleanExtra("my_diary", false);

        memberTv = findViewById(R.id.memberTv);
        turnTv = findViewById(R.id.turnTv);
        imageView = findViewById(R.id.imgv_diary);
        joinBtn = findViewById(R.id.joinButton);

        Model.Diary diary;
        if (is_mine) {
            diary = currentUser.getDiaries().get(diary_num).getDiary();

            if (diary.getTurnId().equals(currentUser.getId())) {
                turnTv.setText("Your Turn!");

                // TODO imageView에 표지사진 설정
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, WriteDiaryActivity.class);
                        context.startActivity(intent);
                    }
                });
            } else {
                turnTv.setText("Not Today!\n Take a Break");
                // TODO imageView에 표지사진 설정
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ReadActivity.class);
                        intent.putExtra("diary_num", diary_num);
                        intent.putExtra("my_diary", is_mine);
                        context.startActivity(intent);
                    }
                });
            }
            joinBtn.setVisibility(View.INVISIBLE);
        } else {
            diary = DummyData.diaries.get(diary_num);
            turnTv.setText("Join Us!");
            joinBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentUser.addDiary(new Model.CustomDiary(diary));
                    DummyData.diaries.remove(diary);
                    Log.e("$$$$^^^^", currentUser.getDiaries().size() + "");
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
        memberTv.setText("Member : " + diary.getMembers().size() + " / " + MAX_MEMBER);
    }
}