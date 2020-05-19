package com.bs.knows.connect.bean;

public class DeleteData {

    /**
     * error : true
     * deleteComplate : true
     */

    private boolean error;
    private boolean deleteComplate;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isDeleteComplate() {
        return deleteComplate;
    }

    public void setDeleteComplate(boolean deleteComplate) {
        this.deleteComplate = deleteComplate;
    }
}
