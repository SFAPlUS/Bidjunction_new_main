package com.bidding.dell.BIDJUNCTION.Network;

import java.io.Serializable;

public class MeetingBO implements Serializable {
    public String MI_ID;
    public String MI_Number;
    public String MI_Title;
    public String MI_DistID;
    public String Dist_Name;
    public String MI_ApproxBudget;
    public String MI_MeetingDateTime;
    public String MI_ISCode;
    public String IS_Name;
    public String MI_EvtTypeID;

    public String getMI_EvtTypeID() {
        return MI_EvtTypeID;
    }

    public void setMI_EvtTypeID(String MI_EvtTypeID) {
        this.MI_EvtTypeID = MI_EvtTypeID;
    }

    public String getMI_ID() {
        return MI_ID;
    }

    public void setMI_ID(String MI_ID) {
        this.MI_ID = MI_ID;
    }

    public String getMI_Number() {
        return MI_Number;
    }

    public void setMI_Number(String MI_Number) {
        this.MI_Number = MI_Number;
    }

    public String getMI_Title() {
        return MI_Title;
    }

    public void setMI_Title(String MI_Title) {
        this.MI_Title = MI_Title;
    }

    public String getMI_DistID() {
        return MI_DistID;
    }

    public void setMI_DistID(String MI_DistID) {
        this.MI_DistID = MI_DistID;
    }

    public String getDist_Name() {
        return Dist_Name;
    }

    public void setDist_Name(String dist_Name) {
        Dist_Name = dist_Name;
    }

    public String getMI_ApproxBudget() {
        return MI_ApproxBudget;
    }

    public void setMI_ApproxBudget(String MI_ApproxBudget) {
        this.MI_ApproxBudget = MI_ApproxBudget;
    }

    public String getMI_MeetingDateTime() {
        return MI_MeetingDateTime;
    }

    public void setMI_MeetingDateTime(String MI_MeetingDateTime) {
        this.MI_MeetingDateTime = MI_MeetingDateTime;
    }

    public String getMI_ISCode() {
        return MI_ISCode;
    }

    public void setMI_ISCode(String MI_ISCode) {
        this.MI_ISCode = MI_ISCode;
    }

    public String getIS_Name() {
        return IS_Name;
    }

    public void setIS_Name(String IS_Name) {
        this.IS_Name = IS_Name;
    }
}
