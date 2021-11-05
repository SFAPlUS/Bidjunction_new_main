package com.bidding.dell.BIDJUNCTION.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bidding.dell.BIDJUNCTION.R;

import androidx.fragment.app.Fragment;


public class BiddingDetailsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bidding_details, container, false);
        return v;
    }
}