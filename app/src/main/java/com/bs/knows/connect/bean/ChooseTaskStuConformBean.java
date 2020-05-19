package com.bs.knows.connect.bean;

public class ChooseTaskStuConformBean {

    /**
     * error : false
     * ConformTaskStu : true
     */

    private boolean error;
    private boolean ConformTaskStu;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isConformTaskStu() {
        return ConformTaskStu;
    }

    public void setConformTaskStu(boolean ConformTaskStu) {
        this.ConformTaskStu = ConformTaskStu;
    }
}
