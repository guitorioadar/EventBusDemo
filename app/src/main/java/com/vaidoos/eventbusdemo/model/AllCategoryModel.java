package com.vaidoos.eventbusdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllCategoryModel {

    @SerializedName("pcCategoryID")
    @Expose
    private String pcCategoryID;
    @SerializedName("pcCategoryName")
    @Expose
    private String pcCategoryName;
    @SerializedName("pcStockACID")
    @Expose
    private Object pcStockACID;
    @SerializedName("pcExpACID")
    @Expose
    private Object pcExpACID;
    @SerializedName("pcCompanyID")
    @Expose
    private String pcCompanyID;
    @SerializedName("pcPIC_Locate")
    @Expose
    private String pcPICLocate;

    private boolean isChecked;

    /**
     * No args constructor for use in serialization
     *
     */
    public AllCategoryModel() {
    }

    /**
     *
     * @param pcCompanyID
     * @param pcCategoryID
     * @param pcPICLocate
     * @param pcExpACID
     * @param pcCategoryName
     * @param pcStockACID
     * @param isChecked
     */
    public AllCategoryModel(String pcCategoryID, String pcCategoryName, Object pcStockACID, Object pcExpACID, String pcCompanyID, String pcPICLocate, boolean isChecked) {
        super();
        this.pcCategoryID = pcCategoryID;
        this.pcCategoryName = pcCategoryName;
        this.pcStockACID = pcStockACID;
        this.pcExpACID = pcExpACID;
        this.pcCompanyID = pcCompanyID;
        this.pcPICLocate = pcPICLocate;
        this.isChecked = isChecked;
    }

    public String getPcCategoryID() {
        return pcCategoryID;
    }

    public void setPcCategoryID(String pcCategoryID) {
        this.pcCategoryID = pcCategoryID;
    }

    public String getPcCategoryName() {
        return pcCategoryName;
    }

    public void setPcCategoryName(String pcCategoryName) {
        this.pcCategoryName = pcCategoryName;
    }

    public Object getPcStockACID() {
        return pcStockACID;
    }

    public void setPcStockACID(Object pcStockACID) {
        this.pcStockACID = pcStockACID;
    }

    public Object getPcExpACID() {
        return pcExpACID;
    }

    public void setPcExpACID(Object pcExpACID) {
        this.pcExpACID = pcExpACID;
    }

    public String getPcCompanyID() {
        return pcCompanyID;
    }

    public void setPcCompanyID(String pcCompanyID) {
        this.pcCompanyID = pcCompanyID;
    }

    public String getPcPICLocate() {
        return pcPICLocate;
    }

    public void setPcPICLocate(String pcPICLocate) {
        this.pcPICLocate = pcPICLocate;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}