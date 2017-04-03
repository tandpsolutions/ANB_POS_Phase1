package com.anb.pos.home.inventory.model;

import com.anb.pos.support.BaseModel;

import java.util.ArrayList;

/**
 * Created by Bhaumik on 27/03/17.
 */

public class IntransitModel extends BaseModel {

    private ArrayList<IntransitData> data;

    public ArrayList<IntransitData> getData() {
        return data;
    }

    public void setData(ArrayList<IntransitData> data) {
        this.data = data;
    }

    private class IntransitData {
        private String ref_no;
        private String inv_no;
        private String v_date;
        private String from_loc;
        private String to_loc;
        private String approve_by;

        public String getRef_no() {
            return ref_no;
        }

        public void setRef_no(String ref_no) {
            this.ref_no = ref_no;
        }

        public String getInv_no() {
            return inv_no;
        }

        public void setInv_no(String inv_no) {
            this.inv_no = inv_no;
        }

        public String getV_date() {
            return v_date;
        }

        public void setV_date(String v_date) {
            this.v_date = v_date;
        }

        public String getFrom_loc() {
            return from_loc;
        }

        public void setFrom_loc(String from_loc) {
            this.from_loc = from_loc;
        }

        public String getTo_loc() {
            return to_loc;
        }

        public void setTo_loc(String to_loc) {
            this.to_loc = to_loc;
        }

        public String getApprove_by() {
            return approve_by;
        }

        public void setApprove_by(String approve_by) {
            this.approve_by = approve_by;
        }
    }
}
