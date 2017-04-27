package com.anb.pos.home.inventory.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anb.pos.R;
import com.anb.pos.branch.BranchManager;
import com.anb.pos.home.inventory.model.IntransitModel;

import java.util.ArrayList;

/**
 * Created by Bhaumik on 10/04/17.
 */

public class IntransitAdapter extends RecyclerView.Adapter<IntransitAdapter.ViewHolder> {


    private ArrayList<IntransitModel.IntransitData> intransitModels;
    private Context mContext;

    public IntransitAdapter(ArrayList<IntransitModel.IntransitData> intransitModels, Context mContext) {
        this.intransitModels = intransitModels;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_intransit_list, parent, false);
        final ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvInvNo.setText(intransitModels.get(position).getInv_no());
        holder.tvFromLoc.setText(BranchManager.getInstance().getBranches().get(intransitModels.get(position).getFrom_loc() - 1).getBranch_name());
        holder.tvToLoc.setText(BranchManager.getInstance().getBranches().get(intransitModels.get(position).getTo_loc() - 1).getBranch_name());
        holder.tvQty.setText(intransitModels.get(position).getQTY() + "");
    }

    @Override
    public int getItemCount() {
        return intransitModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvInvNo;
        private TextView tvDate;
        private TextView tvFromLoc;
        private TextView tvToLoc;
        private TextView tvQty;

        public ViewHolder(View itemView) {
            super(itemView);
            tvInvNo = (TextView) itemView.findViewById(R.id.row_intransit_tv_inv_no);
            tvDate = (TextView) itemView.findViewById(R.id.row_intransit_tv_inv_no);
            tvFromLoc = (TextView) itemView.findViewById(R.id.row_intransit_tv_from_loc);
            tvToLoc = (TextView) itemView.findViewById(R.id.row_intransit_tv_to_loc);
            tvQty = (TextView) itemView.findViewById(R.id.row_intransit_tv_qty);
        }
    }
}
