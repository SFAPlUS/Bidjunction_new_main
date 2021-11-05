package com.bidding.dell.BIDJUNCTION.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bidding.dell.BIDJUNCTION.Models.LiveBidModel;
import com.bidding.dell.BIDJUNCTION.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LiveBidAdapter extends RecyclerView.Adapter<LiveBidAdapter.Hold> {

    Context context;
    List<LiveBidModel> list;


    public LiveBidAdapter(Context applicationContext, List<LiveBidModel> lbList) {

        this.context = applicationContext;
        this.list = lbList;
        // this.clickListener = clickListener;

    }

    @NonNull
    @Override
    public Hold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_items_row, parent, false);
        return new Hold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Hold holder, int position) {

        final LiveBidModel lbm = list.get(position);

        holder.tvCompany.setText(lbm.c_Name);
        holder.tvProduct.setText(lbm.product_name);
        // holder.mainLay.setOnClickListener();

       /* holder.mainLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //clickListener.onBidClick(lbm);

                BiddingDetailsFragment bdf = new BiddingDetailsFragment();
                MainActivity.pushFragmentsStatic(bdf, true, null);


                *//*Fragment fragment = new BiddingDetailsFragment();
                FragmentManager fragmentManager = context.getApplicationContext().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*//*

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class Hold extends RecyclerView.ViewHolder {

        TextView tvCompany, tvProduct;
        RelativeLayout mainLay;

        public Hold(@NonNull View itemView) {
            super(itemView);

            tvCompany = itemView.findViewById(R.id.tv_company);
            tvProduct = itemView.findViewById(R.id.tv_product);
            mainLay = itemView.findViewById(R.id.main_lay);


        }

    }
}
