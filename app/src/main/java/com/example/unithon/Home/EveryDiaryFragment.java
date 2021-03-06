package com.example.unithon.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unithon.Comment.CommentAdapter;
import com.example.unithon.DummyData;
import com.example.unithon.Model;
import com.example.unithon.R;

import java.util.ArrayList;

public class EveryDiaryFragment extends Fragment {
    EveryDiaryAdapter diaryAdapter;
    RecyclerView rcvDiaries;
    ArrayList<Model.Diary> diaryArrayList;
    private SearchView svSearch;
    public static int isHashTag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        diaryArrayList = DummyData.diaries;
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_others, container, false);

        System.out.println("here " + diaryArrayList);

        svSearch = (SearchView) rootView.findViewById(R.id.sv_search);

        rcvDiaries = rootView.findViewById(R.id.others_rcv);
        diaryAdapter = new EveryDiaryAdapter(getActivity());
        rcvDiaries.setAdapter(diaryAdapter);
        diaryAdapter.submitList(diaryArrayList);

        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                diaryAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println(newText);
                if(newText.length() != 0 && newText.charAt(0) == '#') {
                    isHashTag = 1;
                    diaryAdapter.getFilter().filter(newText);
                } else {
                    isHashTag = 0;
                    diaryAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });

        return rootView;
    }
}
