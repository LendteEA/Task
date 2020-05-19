package com.bs.knows.connect.bean;

import java.util.List;

public class NewsListBean {

    /**
     * error : false
     * news : [{"id":"1","newsTitles":"我校关工委荣获北京教育系统关工委多项表彰","newsUrl":"https://news.buu.edu.cn/art/2020/5/8/art_13583_601630.html","newTime":"【05-08】"},{"id":"2","newsTitles":"我校徐鲲教授荣获2020年度北京市三八红旗奖章","newsUrl":"https://news.buu.edu.cn/art/2020/5/8/art_13583_601654.html","newTime":"【05-08】"},{"id":"3","newsTitles":"北联大学子获2020年美国大学生数学建模竞赛特等奖提名奖","newsUrl":"https://news.buu.edu.cn/art/2020/5/7/art_13583_601612.html","newTime":"【05-07】"}]
     */

    private boolean error;
    private List<NewsBean> news;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public static class NewsBean {
        /**
         * id : 1
         * newsTitles : 我校关工委荣获北京教育系统关工委多项表彰
         * newsUrl : https://news.buu.edu.cn/art/2020/5/8/art_13583_601630.html
         * newTime : 【05-08】
         */

        private String id;
        private String newsTitles;
        private String newsUrl;
        private String newTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNewsTitles() {
            return newsTitles;
        }

        public void setNewsTitles(String newsTitles) {
            this.newsTitles = newsTitles;
        }

        public String getNewsUrl() {
            return newsUrl;
        }

        public void setNewsUrl(String newsUrl) {
            this.newsUrl = newsUrl;
        }

        public String getNewTime() {
            return newTime;
        }

        public void setNewTime(String newTime) {
            this.newTime = newTime;
        }
    }
}
