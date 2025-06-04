package com.example.javaxml.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javaxml.R;
import com.example.javaxml.api.ApiViewModel;
import com.example.javaxml.recyclerview.AdapterClass;

public class SearchFragment extends Fragment {
    private RecyclerView recyclerView;

    EditText textView;
    ImageButton imageView;
    private ApiViewModel apiViewModel;
    String[] searchText = new String[1];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiViewModel = new ViewModelProvider(requireActivity()).get(ApiViewModel.class);
        apiViewModel.fetchApiData("\"");

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        imageView = view.findViewById(R.id.imageView2);
        textView = view.findViewById(R.id.textView6);


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        apiViewModel.getApiResponse().observe(
                getViewLifecycleOwner(), response->{
                    if (response.getHits() != null) {
                        recyclerView.setAdapter(new AdapterClass(response.getHits()));
                        Log.d("apiResponse", String.valueOf(response.getTotalHits()));
                    }else {
                        Toast.makeText(requireActivity(), "No search result found ", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        textView.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if(i == EditorInfo.IME_ACTION_SEARCH){
                            search();
                            return true;
                        }
                        return false;
                    }
                }
        );


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               search();
            }
        });

        return view;
    }

   void search(){

       InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(requireActivity().INPUT_METHOD_SERVICE);
       imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);

        searchText[0] = textView.getText().toString();
        textView.clearFocus();
        if(searchText[0].isBlank() || searchText[0].isEmpty()){
            Toast.makeText(requireActivity(), "please enter something ", Toast.LENGTH_SHORT).show();
        } else {
            apiViewModel.fetchApiData(searchText[0]);
        }
    }
}