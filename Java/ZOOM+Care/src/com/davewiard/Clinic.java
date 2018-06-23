package com.davewiard;

import java.util.ArrayList;

public class Clinic {
    private ArrayList<String> clinicHours;
    private String clinicCode;
    private String clinicName;


    /**
     *
     * @return
     */
    public ArrayList<String> getClinicHours() {
        return clinicHours;
    }


    /**
     *
     * @param clinicHours
     */
    public void setClinicHours(ArrayList<String> clinicHours) {
        this.clinicHours = clinicHours;
    }


    /**
     *
     * @return
     */
    public String getClinicCode() {
        return clinicCode;
    }


    /**
     *
     * @param clinicCode
     */
    public void setClinicCode(String clinicCode) {
        this.clinicCode = clinicCode;
    }


    /**
     *
     * @return
     */
    public String getClinicName() {
        return clinicName;
    }


    /**
     *
     * @param clinicName
     */
    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }


}
