package com.example.unithon.Home;

import static com.example.unithon.DummyData.currentUser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.unithon.DummyData;
import com.example.unithon.Model;
import com.example.unithon.NewDiaryActivity;
import com.example.unithon.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    GridView gridView;
    HomeGridAdapter gridAdapter;
    private Button newDiary;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        gridView = rootView.findViewById(R.id.gridView);
        gridAdapter = new HomeGridAdapter(currentUser.getDiaries());
        gridView.setAdapter(gridAdapter);

        newDiary = rootView.findViewById(R.id.newDiary);

        newDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewDiaryActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("$$$$", currentUser.getDiaries().size() + "");
        gridAdapter.notifyList(currentUser.getDiaries());
        gridAdapter.notifyDataSetChanged();
    }
}
