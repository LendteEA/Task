package com.bs.knows.connect.bean;

import java.util.List;

public class UserDetailBean {


    /**
     * error : false
     * data : [{"id":"1","teachernumber":"1111111111","password":"1","teachername":"AAA","major":"软件工程","college":"机器人学院"}]
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
         * id : 1
         * teachernumber : 1111111111
         * password : 1
         * teachername : AAA
         * major : 软件工程
         * college : 机器人学院
         */

        private String id;
        private String teachernumber;
        private String password;
        private String teachername;
        private String major;
        private String college;
        /**
         * studentnumber : 2016220360021
         * studentname : 学生1
         * task : 题目1,
         * conformTask : 1
         */

        private String studentnumber;
        private String studentname;
        private String task;
        private String conformTask;
        /**
         * adminID : admin
         */

        private String adminID;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTeachernumber() {
            return teachernumber;
        }

        public void setTeachernumber(String teachernumber) {
            this.teachernumber = teachernumber;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getTeachername() {
            return teachername;
        }

        public void setTeachername(String teachername) {
            this.teachername = teachername;
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

        public String getStudentnumber() {
            return studentnumber;
        }

        public void setStudentnumber(String studentnumber) {
            this.studentnumber = studentnumber;
        }

        public String getStudentname() {
            return studentname;
        }

        public void setStudentname(String studentname) {
            this.studentname = studentname;
        }

        public String getTask() {
            return task;
        }

        public void setTask(String task) {
            this.task = task;
        }

        public String getConformTask() {
            return conformTask;
        }

        public void setConformTask(String conformTask) {
            this.conformTask = conformTask;
        }

        public String getAdminID() {
            return adminID;
        }

        public void setAdminID(String adminID) {
            this.adminID = adminID;
        }
    }
}
