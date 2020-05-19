package com.bs.knows.connect.bean;

public class ProgressUpate {


    /**
     * error : false
     * updateProgress : true
     */

    private boolean error;
    private boolean updateProgress;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isUpdateProgress() {
        return updateProgress;
    }

    public void setUpdateProgress(boolean updateProgress) {
        this.updateProgress = updateProgress;
    }
}
