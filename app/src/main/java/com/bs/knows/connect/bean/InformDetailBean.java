package com.bs.knows.connect.bean;

import java.util.List;

public class InformDetailBean {


    /**
     * error : false
     * data : [{"id":"1","informtitle":"通知1","informdetail":"通知1详细信息","major":"软件工程","college":"机器人学院","number":"1111111111","readed":"0","TaskTo":"0","role":"教师"},{"id":"2","informtitle":"通知2","informdetail":"通知2详细信息","major":"软件工程","college":"机器人学院","number":"1111111111","readed":"0","TaskTo":"0","role":"教师"},{"id":"3","informtitle":"通知3","informdetail":"通知3详细信息","major":"软件工程","college":"机器人学院","number":"1111111111","readed":"0","TaskTo":"0","role":"教师"},{"id":"12","informtitle":"通知5","informdetail":"通知5详细信息","major":"软件工程","college":"机器人学院","number":"1111111111","readed":"0","TaskTo":"1","role":"教师"},{"id":"13","informtitle":"通知4","informdetail":"通知4详细信息","major":"软件工程","college":"机器人学院","number":"1111111111","readed":"0","TaskTo":"1","role":"教师"},{"id":"20","informtitle":"选择课题失败","informdetail":"经指导教师审核，您未能成功选择：课题2.\n具体原因为：11111","major":"","college":"","number":"","readed":"0","TaskTo":"2016220360021","role":"Admin"},{"id":"22","informtitle":"课题审核未通过","informdetail":"该课题课题6.未通过课题审核。\n具体原因为：原因1","major":"","college":"","number":"admin","readed":"0","TaskTo":"1111111111","role":"管理员"}]
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
         * informtitle : 通知1
         * informdetail : 通知1详细信息
         * major : 软件工程
         * college : 机器人学院
         * number : 1111111111
         * readed : 0
         * TaskTo : 0
         * role : 教师
         */

        private String id;
        private String informtitle;
        private String informdetail;
        private String major;
        private String college;
        private String number;
        private String readed;
        private String TaskTo;
        private String role;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInformtitle() {
            return informtitle;
        }

        public void setInformtitle(String informtitle) {
            this.informtitle = informtitle;
        }

        public String getInformdetail() {
            return informdetail;
        }

        public void setInformdetail(String informdetail) {
            this.informdetail = informdetail;
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

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getReaded() {
            return readed;
        }

        public void setReaded(String readed) {
            this.readed = readed;
        }

        public String getTaskTo() {
            return TaskTo;
        }

        public void setTaskTo(String TaskTo) {
            this.TaskTo = TaskTo;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
