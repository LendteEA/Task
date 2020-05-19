package com.bs.knows.connect.bean;

import java.util.List;

public class CheckUploadTaskBean {

    /**
     * error : false
     * data : [{"taskname":"课题1","status":"选题中","uploedfilepath":"暂无"},{"taskname":"课题2","status":"选题中","uploedfilepath":"暂无"},{"taskname":"课题3","status":"选题中","uploedfilepath":"暂无"},{"taskname":"课题4","status":"选题中","uploedfilepath":"暂无"},{"taskname":"课题5","status":"选题中","uploedfilepath":"暂无"},{"taskname":"课题6","status":"选题中","uploedfilepath":"暂无"},{"taskname":"课题7","status":"选题中","uploedfilepath":"暂无"},{"taskname":"课题8","status":"选题中","uploedfilepath":"暂无"}]
     */

    private boolean error;
    private List<DataBean> data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * taskname : 课题1
         * status : 选题中
         * uploedfilepath : 暂无
         */

        private String taskname;
        private String status;
        private String uploedfilepath;

        public String getTaskname() {
            return taskname;
        }

        public void setTaskname(String taskname) {
            this.taskname = taskname;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUploedfilepath() {
            return uploedfilepath;
        }

        public void setUploedfilepath(String uploedfilepath) {
            this.uploedfilepath = uploedfilepath;
        }
    }
}
