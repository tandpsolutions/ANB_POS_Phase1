package com.anb.pos.branch;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.anb.pos.R;
import com.anb.pos.branch.model.BranchModelData;

import java.util.ArrayList;

/**
 * Created by Bhaumik on 27/03/17.
 */

public class BranchAdapter extends ArrayAdapter<BranchModelData.BranchModel> {

    private ArrayList<BranchModelData.BranchModel> mBranchModels;
    private Context mContext;
    private LayoutInflater inflater;

    public BranchAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<BranchModelData.BranchModel> branchModels) {
        super(context, resource, branchModels);
        this.mContext = context;
        this.mBranchModels = branchModels;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mBranchModels.size();
    }

    @Nullable
    @Override
    public BranchModelData.BranchModel getItem(int position) {
        return mBranchModels.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final View row = inflater.inflate(R.layout.row_spinner_text, parent, false);
        final TextView textView = (TextView) row.findViewById(R.id.row_spinner_title);
        textView.setText(mBranchModels.get(position).getBranch_name());
        return row;
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final View row = inflater.inflate(R.layout.row_spinner_text, parent, false);
        final TextView label = (TextView) row.findViewById(R.id.row_spinner_title);
        label.setText(mBranchModels.get(position).getBranch_name());
        return row;
    }
}
