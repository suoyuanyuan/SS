package bw.com.day_viewpagerlx;

import java.util.List;

/**
 * Created by 索园 on 2017/8/14.
 */
public class DataBean {
    public Data data;
    public class Data{
        public List<Adlist> adlist;
    }
    public class Adlist{
        public String id;
        public String img;
    }
}
