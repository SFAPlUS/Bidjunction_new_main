package com.bidding.dell.BIDJUNCTION.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bidding.dell.BIDJUNCTION.Adapters.LiveBidAdapter;
import com.bidding.dell.BIDJUNCTION.Models.LiveBidModel;
import com.bidding.dell.BIDJUNCTION.R;
import com.bidding.dell.BIDJUNCTION.Utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.bidding.dell.BIDJUNCTION.Activities.HomeActivity.pushFragmentsStatic;

public class LiveFragment extends Fragment {

    RecyclerView liveRv;
    LiveBidAdapter lbAdapter;
    List<LiveBidModel> lbList = new ArrayList<>();
   // LiveBidAdapter.ItemClickListener clickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.live_fragment, container, false);

        liveRv = v.findViewById(R.id.live_rvnew);

        /*clickListener = new LiveBidAdapter.ItemClickListener() {
            @Override
            public void onBidClick(LiveBidModel lbm) {
                BiddingDetailsFragment bdf = new BiddingDetailsFragment();
                // pushFragmentsStatic(bayItemMapFragment, true, null);
                MainActivity.pushFragmentsStatic(bdf, true, null);

            }
        };*/

        LiveBidModel lbm = new LiveBidModel();
        lbm.c_Name = "SRMB";
        lbm.product_name = "TMT BAR";
        lbList.add(lbm);

        LiveBidModel lbm1 = new LiveBidModel();
        lbm1.c_Name = "TOP TECH";
        lbm1.product_name = "Steel";
        lbList.add(lbm1);

        lbAdapter = new LiveBidAdapter(getContext(), lbList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        liveRv.setLayoutManager(layoutManager);
        liveRv.setAdapter(lbAdapter);


//        liveRv.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), liveRv, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                BiddingDetailsFragment bdf = new BiddingDetailsFragment();
//                MainActivity.pushFragmentsStatic(bdf, true, null);
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//
//            }
//        }));

        liveRv.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), liveRv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {



                BiddingDetailsFragment bidDetailsFragment = new BiddingDetailsFragment();
                pushFragmentsStatic(bidDetailsFragment, true, null);

            }

            @Override
            public void onItemLongClick(View view, int position) {



            }
        }));



        return v;
    }
}
