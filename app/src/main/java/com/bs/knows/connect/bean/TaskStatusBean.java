package com.bs.knows.connect.bean;

public class TaskStatusBean {

    /**
     * error : false
     * status : 选题中
     */

    private boolean error;
    private String status;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
