package com.anb.pos.branch;

import com.anb.pos.branch.model.BranchModelData;

import java.util.ArrayList;

/**
 * Created by Bhaumik on 20/03/17.
 */

public class BranchManager {
    private static final BranchManager ourInstance = new BranchManager();

    public static BranchManager getInstance() {
        return ourInstance;
    }

    private BranchManager() {
    }

    private ArrayList<BranchModelData.BranchModel> branches;

    public ArrayList<BranchModelData.BranchModel> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<BranchModelData.BranchModel> branches) {
        this.branches = branches;
    }
}
