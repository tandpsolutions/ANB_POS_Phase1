package com.anb.pos.branch.model;

import com.anb.pos.support.BaseModel;

import java.util.ArrayList;

/**
 * Created by Bhaumik on 20/03/17.
 */

public class BranchModelData extends BaseModel {

    private ArrayList<BranchModel> data;

    public class BranchModel {
        private String branch_code;
        private String branch_name;

        public String getBranch_code() {
            return branch_code;
        }

        public void setBranch_code(String branch_code) {
            this.branch_code = branch_code;
        }

        public String getBranch_name() {
            return branch_name;
        }

        public void setBranch_name(String branch_name) {
            this.branch_name = branch_name;
        }
    }

    public ArrayList<BranchModel> getData() {
        return data;
    }

    public void setData(ArrayList<BranchModel> data) {
        this.data = data;
    }
}
