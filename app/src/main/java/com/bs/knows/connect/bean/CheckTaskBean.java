package com.bs.knows.connect.bean;

import java.util.List;

public class CheckTaskBean {


    /**
     * error : false
     * data : [{"taskname":"基于特征空间的图像配准算法实现与优化","taskabstract":"基于特征空间的图像配准算法实现与优化  课题简介","taskteacher":"0006","taskteachername":"景竑元","chosed":"0"},{"taskname":"基于Java的美剧爱好者社区的设计与实现","taskabstract":"基于Java的美剧爱好者社区的设计与实现  课题简介","taskteacher":"0007","taskteachername":"商新娜","chosed":"0"},{"taskname":"基于Android平台的双向选择系统设计与实现","taskabstract":"基于Android平台的双向选择系统设计与实现  课题简介","taskteacher":"0008","taskteachername":"孙庆华","chosed":"0"},{"taskname":"基于Web的社区爱心捐赠网站的设计与实现","taskabstract":"基于Web的社区爱心捐赠网站的设计与实现  课题简介","taskteacher":"0009","taskteachername":"张冰峰","chosed":"0"},{"taskname":"基于Java的水果电子商务网站的设计与实现","taskabstract":"基于Java的水果电子商务网站的设计与实现  课题简介","taskteacher":"0010","taskteachername":"张玉祥","chosed":"0"},{"taskname":"诗词文化微信小程序设计与开发","taskabstract":"诗词文化微信小程序设计与开发  课题简介","taskteacher":"0011","taskteachername":"杨继萍","chosed":"0"},{"taskname":"硬件课题1","taskabstract":"硬件课题1  课题简介","taskteacher":"0012","taskteachername":"教师AA","chosed":"0"},{"taskname":"硬件课题2","taskabstract":"硬件课题2  课题简介","taskteacher":"0013","taskteachername":"教师BB","chosed":"0"}]
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
         * taskname : 基于特征空间的图像配准算法实现与优化
         * taskabstract : 基于特征空间的图像配准算法实现与优化  课题简介
         * taskteacher : 0006
         * taskteachername : 景竑元
         * chosed : 0
         */

        private String taskname;
        private String taskabstract;
        private String taskteacher;
        private String taskteachername;
        private String chosed;

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

        public String getChosed() {
            return chosed;
        }

        public void setChosed(String chosed) {
            this.chosed = chosed;
        }
    }
}
