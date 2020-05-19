package com.bs.knows.connect.bean;

public class AddInformBean {


    /**
     * error : false
     * AddInfrom : true
     */

    private boolean error;
    private boolean AddInfrom;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isAddInfrom() {
        return AddInfrom;
    }

    public void setAddInfrom(boolean AddInfrom) {
        this.AddInfrom = AddInfrom;
    }
}
