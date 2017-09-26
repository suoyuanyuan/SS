package com.example.gkjexam.MyBean;

import java.util.List;

/**
 * Created by 郭康杰 on 2017/8/18.
 */
public class Mycaricature {

    /**
     * error_code : 200
     * reason : 请求成功！
     * result : {"bookList":[{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad613.jpg","des":"","finish":false,"lastUpdate":20150603,"name":"灵神考试","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad61f.jpg","des":"","finish":false,"lastUpdate":20160602,"name":"虹色妖姬","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad617.jpg","des":"","finish":false,"lastUpdate":20150406,"name":"好漫画","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad634.jpg","des":"","finish":false,"lastUpdate":20150606,"name":"永恒传说","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad61d.jpg","des":"","finish":false,"lastUpdate":20160103,"name":"逆转仙师","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad638.jpg","des":"","finish":false,"lastUpdate":20150416,"name":"我的同学都是外星人","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad640.jpg","des":"","finish":false,"lastUpdate":20150416,"name":"星空六班","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad63d.jpg","des":"","finish":false,"lastUpdate":20150416,"name":"逆游戏","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad641.jpg","des":"","finish":false,"lastUpdate":20150416,"name":"不败战神","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad636.jpg","des":"","finish":false,"lastUpdate":20150416,"name":"诛仙（星漫版）","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad63c.jpg","des":"","finish":false,"lastUpdate":20150416,"name":"零界点","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad662.jpg","des":"","finish":false,"lastUpdate":20150404,"name":"勇者与王子","type":"少年漫画"},{"area":"欧美漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad684.jpg","des":"当托尼变成了反派\u2026\u2026","finish":false,"lastUpdate":20151027,"name":"究极钢铁侠Avengers NOW!","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad685.jpg","des":"提督阵亡了，他死前希望雪风活下去,可是\u2026\u2026","finish":true,"lastUpdate":20150404,"name":"一闪一闪亮晶晶","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad6d8.jpg","des":"","finish":false,"lastUpdate":20160617,"name":"怪物大师","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad709.jpg","des":"","finish":false,"lastUpdate":20150607,"name":"半月书院","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad70d.jpg","des":"","finish":false,"lastUpdate":20160106,"name":"龙门赏（漫客）","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad6d6.jpg","des":"","finish":false,"lastUpdate":20160316,"name":"深国物语","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad6d7.jpg","des":"少年阴阳武斗剧开幕！想要守护的话就振作起来！！","finish":false,"lastUpdate":20160604,"name":"双星之阴阳师","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad6ce.jpg","des":"","finish":false,"lastUpdate":20150820,"name":"终极武器未来式","type":"少年漫画"}],"limit":20,"total":15767}
     */

    private int error_code;
    private String reason;
    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * bookList : [{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad613.jpg","des":"","finish":false,"lastUpdate":20150603,"name":"灵神考试","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad61f.jpg","des":"","finish":false,"lastUpdate":20160602,"name":"虹色妖姬","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad617.jpg","des":"","finish":false,"lastUpdate":20150406,"name":"好漫画","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad634.jpg","des":"","finish":false,"lastUpdate":20150606,"name":"永恒传说","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad61d.jpg","des":"","finish":false,"lastUpdate":20160103,"name":"逆转仙师","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad638.jpg","des":"","finish":false,"lastUpdate":20150416,"name":"我的同学都是外星人","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad640.jpg","des":"","finish":false,"lastUpdate":20150416,"name":"星空六班","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad63d.jpg","des":"","finish":false,"lastUpdate":20150416,"name":"逆游戏","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad641.jpg","des":"","finish":false,"lastUpdate":20150416,"name":"不败战神","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad636.jpg","des":"","finish":false,"lastUpdate":20150416,"name":"诛仙（星漫版）","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad63c.jpg","des":"","finish":false,"lastUpdate":20150416,"name":"零界点","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad662.jpg","des":"","finish":false,"lastUpdate":20150404,"name":"勇者与王子","type":"少年漫画"},{"area":"欧美漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad684.jpg","des":"当托尼变成了反派\u2026\u2026","finish":false,"lastUpdate":20151027,"name":"究极钢铁侠Avengers NOW!","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad685.jpg","des":"提督阵亡了，他死前希望雪风活下去,可是\u2026\u2026","finish":true,"lastUpdate":20150404,"name":"一闪一闪亮晶晶","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad6d8.jpg","des":"","finish":false,"lastUpdate":20160617,"name":"怪物大师","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad709.jpg","des":"","finish":false,"lastUpdate":20150607,"name":"半月书院","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad70d.jpg","des":"","finish":false,"lastUpdate":20160106,"name":"龙门赏（漫客）","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad6d6.jpg","des":"","finish":false,"lastUpdate":20160316,"name":"深国物语","type":"少年漫画"},{"area":"日本漫画","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad6d7.jpg","des":"少年阴阳武斗剧开幕！想要守护的话就振作起来！！","finish":false,"lastUpdate":20160604,"name":"双星之阴阳师","type":"少年漫画"},{"area":"国漫","coverImg":"http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad6ce.jpg","des":"","finish":false,"lastUpdate":20150820,"name":"终极武器未来式","type":"少年漫画"}]
         * limit : 20
         * total : 15767
         */

        private int limit;
        private int total;
        private List<BookListBean> bookList;

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<BookListBean> getBookList() {
            return bookList;
        }

        public void setBookList(List<BookListBean> bookList) {
            this.bookList = bookList;
        }

        public static class BookListBean {
            /**
             * area : 国漫
             * coverImg : http://imgs.juheapi.com/comic_xin/5559b86938f275fd560ad613.jpg
             * des :
             * finish : false
             * lastUpdate : 20150603
             * name : 灵神考试
             * type : 少年漫画
             */

            private String area;
            private String coverImg;
            private String des;
            private boolean finish;
            private int lastUpdate;
            private String name;
            private String type;

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(String coverImg) {
                this.coverImg = coverImg;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public boolean isFinish() {
                return finish;
            }

            public void setFinish(boolean finish) {
                this.finish = finish;
            }

            public int getLastUpdate() {
                return lastUpdate;
            }

            public void setLastUpdate(int lastUpdate) {
                this.lastUpdate = lastUpdate;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
