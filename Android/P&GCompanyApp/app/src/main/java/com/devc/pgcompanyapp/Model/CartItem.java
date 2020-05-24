package com.devc.pgcompanyapp.Model;

public class CartItem {
    String uid;
    String productid;
    String userid;
    String status;
    String timestamp;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    String productname;

    public CartItem(String uid, String productid, String userid, String status, String timestamp, String productname) {
        this.uid = uid;
        this.productid = productid;
        this.userid = userid;
        this.status = status;
        this.timestamp = timestamp;
        this.productname = productname;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


}
