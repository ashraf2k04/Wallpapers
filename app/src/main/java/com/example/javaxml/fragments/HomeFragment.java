package com.example.javaxml.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.javaxml.R;
import com.example.javaxml.api.ApiViewModel;
import com.example.javaxml.api.Hit;
import com.example.javaxml.recyclerview.AdapterClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;

    private ApiViewModel apiViewModel;

    String[] title = {"Sun", "moon", "valley", "boy", "girl", "women", "village", "flower", "tree"};

    List<Hit> results = new ArrayList<>();

    AdapterClass adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiViewModel = new ViewModelProvider(requireActivity()).get(ApiViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new AdapterClass(results);
        recyclerView.setAdapter(adapter);

        apiViewModel.getApiResponse().observe(
                getViewLifecycleOwner(), response->{
                    if (response != null) {
                        List<Hit> newHits = response.getHits();
                        if (newHits != null && !newHits.isEmpty()) {
                            int oldSize = results.size();
                            results.addAll(newHits);
                            Collections.shuffle(results); // Shuffle after adding
                            adapter.notifyItemRangeInserted(oldSize, newHits.size()); // ✅ Notify adapter of changes
                        }
                    }
                }
        );

        if(results.isEmpty()) {
            for (String s : title) {
                apiViewModel.fetchApiData(s);
            }
        }

        return view;
    }
}