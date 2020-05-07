package com.pgkartavya.Model;

public class ItemMaster {

    public ItemMaster(String uid, String productName, String productBarCode) {
        this.uid = uid;
        this.productName = productName;
        this.productBarCode = productBarCode;
    }

    public void itemmaster(){}



    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBarCode() {
        return productBarCode;
    }

    public void setProductBarCode(String productBarCode) {
        this.productBarCode = productBarCode;
    }

    String uid, productName, productBarCode;



}
