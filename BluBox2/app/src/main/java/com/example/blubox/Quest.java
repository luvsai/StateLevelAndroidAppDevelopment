package com.example.blubox;



public class Quest {



    private String qTitle ,qprice,dprice, qMsg ;
    private int qId , qrating;



    public Quest(int qId , String qTitle, String qMsg, String qprice , String dprice, int qrating   ) {
        this.qId= qId;
        this.qTitle =  qTitle;
        this.qMsg = qMsg;
         this.qprice = qprice ;
         this.dprice = dprice ;
         this.qrating = qrating ;

    }



    public String getqTitle() {
        return qTitle;
    }

    public void setqTitle(String qTitle) {
        this.qTitle = qTitle;
    }

    public String getQprice() {
        return qprice;
    }

    public void setQprice(String qprice) {
        this.qprice = qprice;
    }

    public String getDprice() {
        return dprice;
    }

    public void setDprice(String dprice) {
        this.dprice = dprice;
    }

    public String getqMsg() {
        return qMsg;
    }

    public void setqMsg(String qMsg) {
        this.qMsg = qMsg;
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public int getQrating() {
        return qrating;
    }

    public void setQrating(int qrating) {
        this.qrating = qrating;
    }
}
