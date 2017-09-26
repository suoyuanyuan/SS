package bw.com.day8_rikao;

import java.util.List;

/**
 * Created by 索园 on 2017/8/10.
 */
public class Bean {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2017-08-10","description":"南山子春秋","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-29012953.static/640","title":"一个\u201c了\u201d字，道尽人间沧桑","url":"https://mp.weixin.qq.com/s?src=16&ver=295&timestamp=1502326832&signature=0i6VOqg4nhK710ONUw7mFkazCEhpmvYhebvWWnLZBfS3AUuEI1QWAfA8rQQb0PpgMPXIxmOqgZvqEMiIgXdLSzJaNtvBCVCa0bKf-K5iIS8="},{"ctime":"2017-08-10","description":"南山子春秋","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-29012953.static/640","title":"啥是好日子？（句句在理）","url":"https://mp.weixin.qq.com/s?src=16&ver=295&timestamp=1502326832&signature=lTcCnCCSM0jy0lu1dyL6QS8KmCA-g6XfgW2p6a6AfzxYdXLqBmHQkyPjDYFxp2aT8aMWrXpyPZ*gDrb7u3rwdrwsz5qjO*25LEhy2JFe31M="},{"ctime":"2017-08-10","description":"智联招聘","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-28908119.jpg/640","title":"月薪三千的人，到底应不应该买房？","url":"https://mp.weixin.qq.com/s?src=16&ver=295&timestamp=1502326832&signature=tu9WskxY1IzoTeUAnuhKqvqfpvQgxcohHDPrj7NtM58P-EDOezHBoih2wOau-6Vv7O-a25mPlYrj9m0zp*ZQeN81Jb1H3mcsE*MWSezOnnA="},{"ctime":"2017-08-10","description":"CPPA幸福中国","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-34755124.jpg/640","title":"【美文】罗曼罗兰：写给秋天","url":"https://mp.weixin.qq.com/s?src=16&ver=295&timestamp=1502326831&signature=NQjTxy8UF2fdK5a784fghp9qoSTjk28AaiYeQbZP72Fdz0F*1ZtWdWs4LNMEZW*Ffs2ShtraAxwuuAShiOeYKwuB1LXfzWk6q3D1r6fnNT4="},{"ctime":"2017-08-10","description":"亲子教育","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-30840291.jpg/640","title":"一定要教给孩子：不占便宜是教养","url":"https://mp.weixin.qq.com/s?src=16&ver=295&timestamp=1502326831&signature=g0*T4K2dKSahec2KHfYa5SWY8bNzQ4Y7NQw3fXzcIoV5tL80hn3hJF00tiP6dbPPX9TX609HUO5htoTJrcZ5fPihhjy2K8krj73d6FVL9sw="},{"ctime":"2017-08-10","description":"美白瘦身","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-33175021.static/640","title":"农村一女被人霸占，真人真事！所有人看傻了！","url":"https://mp.weixin.qq.com/s?src=16&ver=295&timestamp=1502326830&signature=WeIp-8BXCCIXIk9ph0C693vHur5ojynFKNvjBW36ephp-6rE9wHMBawjko6NY5Xxj1KfswA1dRnX4XoT9cj9**aVpIDrWvStca8TMJ4tTgs="},{"ctime":"2017-08-10","description":"ijingjie","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-34753770.jpg/640","title":"如何度过你生命中难熬的时光（上）","url":"https://mp.weixin.qq.com/s?src=16&ver=295&timestamp=1502326830&signature=zbyB4mlE8p5Thlt9tcCpYagto0QVg*BWN5S997VVhvbsu*hcxTmJYBjk1E6Tr4UzJeihzIegh0lj7gOppQj*wTh-T8DYfjIiwGFEsi3jFds="},{"ctime":"2017-08-10","description":"生活絮语","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-15987290.jpg/640","title":"怎样和小人相处（精辟）","url":"https://mp.weixin.qq.com/s?src=16&ver=295&timestamp=1502326830&signature=dsbj2YDpdmjnd1Mbd5v528haRYgHBNOSh5tnNi-9w19Za2-ob-K3q-5d5LbWWGuMTzk26sYc-9IQr5NygfAwCD6-3AMYwtpHNaMy2l0NmTk="},{"ctime":"2017-08-10","description":"三分钟新闻早餐","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-34706817.static/640","title":"每日一句：早起思考一段话","url":"https://mp.weixin.qq.com/s?src=16&ver=295&timestamp=1502326829&signature=PccfzKky4FuXPOvhH5-gMdhoJSIj-6jMvFeeRLLwj5UrnXHwt5bKHCJR8S11mScsfY2LZCBhxAswTsy6svy1VSVNLjf2t9tyPNkqEIaSeVE="},{"ctime":"2017-08-10","description":"三分钟新闻早餐","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-34702148.jpg/640","title":"心好，命就好（好文）","url":"https://mp.weixin.qq.com/s?src=16&ver=295&timestamp=1502326829&signature=SvawfOuOKGNYvwtOTpHcUghl2qnRV5Gpvlo2O9RcdD5OHzB80l6AF3BDl8ADv4hJ8MBF6x4Wq2pyteTXxMgI5KEr53c5WR*un4qit4pzMw8="}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2017-08-10
         * description : 南山子春秋
         * picUrl : https://zxpic.gtimg.com/infonew/0/wechat_pics_-29012953.static/640
         * title : 一个“了”字，道尽人间沧桑
         * url : https://mp.weixin.qq.com/s?src=16&ver=295&timestamp=1502326832&signature=0i6VOqg4nhK710ONUw7mFkazCEhpmvYhebvWWnLZBfS3AUuEI1QWAfA8rQQb0PpgMPXIxmOqgZvqEMiIgXdLSzJaNtvBCVCa0bKf-K5iIS8=
         */

        private String ctime;
        private String description;
        private String picUrl;
        private String title;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
