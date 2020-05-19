package com.bs.knows.connect.bean;

public class TaskPassBean {

    /**
     * error : false
     * TaskPass : true
     */

    private boolean error;
    private boolean TaskPass;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isTaskPass() {
        return TaskPass;
    }

    public void setTaskPass(boolean TaskPass) {
        this.TaskPass = TaskPass;
    }
}
