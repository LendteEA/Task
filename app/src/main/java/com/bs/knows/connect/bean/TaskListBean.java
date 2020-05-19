package com.bs.knows.connect.bean;

import java.util.List;

public class TaskListBean {


    /**
     * error : false
     * data : [{"taskname":"饮食推荐系统的设计与实现","taskabstract":"饮食推荐系统的设计与实现 课题简介","taskteacher":"0001","taskteachername":"陈艾东","chosed":"0"},{"taskname":"无人物流配送小车配送软件的设计与实现","taskabstract":"无人物流配送小车配送软件的设计与实现 \r\n 课题简介","taskteacher":"0002","taskteachername":"杜煜","chosed":"0"},{"taskname":"Panda智能活动规划App的设计与实现","taskabstract":"Panda智能活动规划App的设计与实现  课题简介","taskteacher":"0003","taskteachername":"付百文","chosed":"0"},{"taskname":"无人机配件销售网站的设计与实现","taskabstract":"无人机配件销售网站的设计与实现  课题简介","taskteacher":"0004","taskteachername":"宏晨","chosed":"0"},{"taskname":"基于WEB的酒店管理系统的设计与实现","taskabstract":"课题5简介基于WEB的酒店管理系统的设计与实现  课题简介","taskteacher":"0005","taskteachername":"蒋北艳","chosed":"0"}]
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
         * taskname : 饮食推荐系统的设计与实现
         * taskabstract : 饮食推荐系统的设计与实现 课题简介
         * taskteacher : 0001
         * taskteachername : 陈艾东
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
