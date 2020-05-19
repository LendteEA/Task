package com.bs.knows.connect.bean;

public class UploadFileBean {

    /**
     * error : false
     * savepath : true
     */

    private boolean error;
    private boolean savepath;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isSavepath() {
        return savepath;
    }

    public void setSavepath(boolean savepath) {
        this.savepath = savepath;
    }
}
