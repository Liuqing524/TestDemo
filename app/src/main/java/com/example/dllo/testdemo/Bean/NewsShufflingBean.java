package com.example.dllo.testdemo.Bean;

import java.util.List;

/**
 * Created by dllo on 16/9/19.
 */
public class NewsShufflingBean {

    /**
     * code : 0
     * data : {"pics":[{"action":"web","id":0,"imgUrl":"https://krplus-pic.b0.upaiyun.com/201609/13/a029f98f1fa43456cab1f3a0a000fd92.jpg","location":"http://chuang.36kr.com/huodong#/activityApply/details/433","title":"wise直播"},{"action":"web","id":0,"imgUrl":"https://krplus-pic.b0.upaiyun.com/201609/17/336f8144c30d41ef812e2beced792af5.jpg","location":"https://www.douyu.com/733172","title":"大料"},{"action":"web","id":0,"imgUrl":"https://krplus-pic.b0.upaiyun.com/201609/08/bcb0814c9f4a23d375cafee0b13dc704.jpg","location":"https://rong.36kr.com/m/#/demos/133?type=projects&ktm_source=appbanner","title":"共享经济"},{"action":"web","id":0,"imgUrl":"https://krplus-pic.b0.upaiyun.com/201609/13/d8c6b08c3cdf847cbb59e97dbbbc429f.jpg","location":"http://mp.weixin.qq.com/s?__biz=MzAwOTM3ODUwMg==&mid=2651863765&idx=2&sn=928a4fb96b073324b8d0536ec3821e85&scene=1&srcid=0901rJIVLpS17k1YVqUGSRby#wechat_redirect","title":"BD"},{"action":"web","id":0,"imgUrl":"https://krplus-pic.b0.upaiyun.com/201608/29/5d84e589843826199c2f9c9863f9de09.jpg","location":"https://z.36kr.com/project/190","title":"修机100"}]}
     * msg : 操作成功！
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * action : web
         * id : 0
         * imgUrl : https://krplus-pic.b0.upaiyun.com/201609/13/a029f98f1fa43456cab1f3a0a000fd92.jpg
         * location : http://chuang.36kr.com/huodong#/activityApply/details/433
         * title : wise直播
         */

        private List<PicsBean> pics;

        public List<PicsBean> getPics() {
            return pics;
        }

        public void setPics(List<PicsBean> pics) {
            this.pics = pics;
        }

        public static class PicsBean {
            private String action;
            private int id;
            private String imgUrl;
            private String location;
            private String title;

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
