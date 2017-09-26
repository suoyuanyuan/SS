package bw.com.demo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 索园 on 2017/8/6.
 */
public class DataBean {
    public int count;
    public int curpage;
    public List<DataList> datalist;
    /**
     * datalist : [{"cid":"5857","course_name":"3DMAX效果图-实战篇（欧式二）","course_paycount":"0","course_pic":"storage/emulated/0/zz.jpg","course_price":"20.00","course_tname":"丁丁","school_name":"吉大教育"},{"cid":"1167","course_name":"行测数量关系核心考点之行程问题1","course_paycount":"66","course_pic":"storage/emulated/0/zzz.jpg","course_price":"0.00","course_tname":"中公教育","school_name":"中公教育"},{"cid":"1170","course_name":"行测数量关系核心考点之行程问题2","course_paycount":"49","course_pic":"storage/emulated/0/g.jpg","course_price":"0.00","course_tname":"中公教育","school_name":"中公教育"}]
     * limit : 10
     */

    private int limit;
    @SerializedName("datalist")
    private List<DatalistBean> datalistX;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<DatalistBean> getDatalistX() {
        return datalistX;
    }

    public void setDatalistX(List<DatalistBean> datalistX) {
        this.datalistX = datalistX;
    }

    public static class DatalistBean {
        /**
         * cid : 5857
         * course_name : 3DMAX效果图-实战篇（欧式二）
         * course_paycount : 0
         * course_pic : storage/emulated/0/zz.jpg
         * course_price : 20.00
         * course_tname : 丁丁
         * school_name : 吉大教育
         */

        private String cid;
        private String course_name;
        private String course_paycount;
        private String course_pic;
        private String course_price;
        private String course_tname;
        private String school_name;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getCourse_paycount() {
            return course_paycount;
        }

        public void setCourse_paycount(String course_paycount) {
            this.course_paycount = course_paycount;
        }

        public String getCourse_pic() {
            return course_pic;
        }

        public void setCourse_pic(String course_pic) {
            this.course_pic = course_pic;
        }

        public String getCourse_price() {
            return course_price;
        }

        public void setCourse_price(String course_price) {
            this.course_price = course_price;
        }

        public String getCourse_tname() {
            return course_tname;
        }

        public void setCourse_tname(String course_tname) {
            this.course_tname = course_tname;
        }

        public String getSchool_name() {
            return school_name;
        }

        public void setSchool_name(String school_name) {
            this.school_name = school_name;
        }
    }

    public class DataList{
        public String course_pic;
        public String course_price;
        public String course_tname;
    }

}
