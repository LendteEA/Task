package com.bs.knows.connect.bean;

import java.util.List;

public class TaskDetail {


    /**
     * error : false
     * data : [{"id":"3","taskname":"课题1","taskabstract":"课题简介1","taskteacher":"111","taskteachername":"teacher2","major":"软件工程","college":"机器人学院","chosed":"1","status":"选题中","uploedfilepath":"","score":"0","checked":"1","conformStu":"2016220360021"}]
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
         * id : 3
         * taskname : 课题1
         * taskabstract : 课题简介1
         * taskteacher : 111
         * taskteachername : teacher2
         * major : 软件工程
         * college : 机器人学院
         * chosed : 1
         * status : 选题中
         * uploedfilepath :
         * score : 0
         * checked : 1
         * conformStu : 2016220360021
         */

        private String id;
        private String taskname;
        private String taskabstract;
        private String taskteacher;
        private String taskteachername;
        private String major;
        private String college;
        private String chosed;
        private String status;
        private String uploedfilepath;
        private String score;
        private String checked;
        private String conformStu;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTaskname() {
            return taskname;
        }

        public void setTaskname(String taskname) {
            this.taskname = taskname;
        }

        public String getTaskabstract() {
            return taskabstract;
        }

        public void setTaskabstract(String taskabstract) {
            this.taskabstract = taskabstract;
        }

        public String getTaskteacher() {
            return taskteacher;
        }

        public void setTaskteacher(String taskteacher) {
            this.taskteacher = taskteacher;
        }

        public String getTaskteachername() {
            return taskteachername;
        }

        public void setTaskteachername(String taskteachername) {
            this.taskteachername = taskteachername;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getCollege() {
            return college;
        }

        public void setCollege(String college) {
            this.college = college;
        }

        public String getChosed() {
            return chosed;
        }

        public void setChosed(String chosed) {
            this.chosed = chosed;
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

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getChecked() {
            return checked;
        }

        public void setChecked(String checked) {
            this.checked = checked;
        }

        public String getConformStu() {
            return conformStu;
        }

        public void setConformStu(String conformStu) {
            this.conformStu = conformStu;
        }
    }
}
