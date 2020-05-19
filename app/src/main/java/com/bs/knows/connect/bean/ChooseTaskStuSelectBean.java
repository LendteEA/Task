package com.bs.knows.connect.bean;

public class ChooseTaskStuSelectBean {


    /**
     * error : false
     * chooseTask : true
     */

    private boolean error;
    private boolean chooseTask;
    /**
     * SelectTask : false
     */

    private boolean SelectTask;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isChooseTask() {
        return chooseTask;
    }

    public void setChooseTask(boolean chooseTask) {
        this.chooseTask = chooseTask;
    }

    public boolean isSelectTask() {
        return SelectTask;
    }

    public void setSelectTask(boolean SelectTask) {
        this.SelectTask = SelectTask;
    }
}
