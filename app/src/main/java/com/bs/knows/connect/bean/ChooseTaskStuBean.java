package com.bs.knows.connect.bean;

import java.util.List;

public class ChooseTaskStuBean {


    /**
     * error : false
     * data : [{"studentnumber":"2016220360021","studentname":"学生1","major":"软件工程","college":"机器人学院"},{"studentnumber":"2016220360022","studentname":"学生2","major":"软件工程","college":"机器人学院"}]
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
         * studentnumber : 2016220360021
         * studentname : 学生1
         * major : 软件工程
         * college : 机器人学院
         */

        private String studentnumber;
        private String studentname;
        private String major;
        private String college;

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
    }
}
