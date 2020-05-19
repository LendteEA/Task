package com.bs.knows.connect.bean;

public class AddMessageBean {

    /**
     * error : false
     * AddMessage : true
     */

    private boolean error;
    private boolean AddMessage;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isAddMessage() {
        return AddMessage;
    }

    public void setAddMessage(boolean AddMessage) {
        this.AddMessage = AddMessage;
    }
}
