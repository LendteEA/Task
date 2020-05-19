package com.bs.knows.connect.bean;

public class ChooseTaskStuDeleteBean {


    /**
     * error : false
     * Task : ["2016220360022"]
     * DeleteTaskStu : true
     */

    private boolean error;
    private boolean DeleteTaskStu;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isDeleteTaskStu() {
        return DeleteTaskStu;
    }

    public void setDeleteTaskStu(boolean DeleteTaskStu) {
        this.DeleteTaskStu = DeleteTaskStu;
    }
}
