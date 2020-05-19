package com.bs.knows.connect.bean;

public class AddTaskBean {

    /**
     * error : false
     * addTaskSuccess : true
     */

    private boolean error;
    private boolean addTaskSuccess;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isAddTaskSuccess() {
        return addTaskSuccess;
    }

    public void setAddTaskSuccess(boolean addTaskSuccess) {
        this.addTaskSuccess = addTaskSuccess;
    }
}
