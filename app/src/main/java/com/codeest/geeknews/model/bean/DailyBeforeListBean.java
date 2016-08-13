package com.codeest.geeknews.model.bean;

import java.util.List;

/**
 * Created by codeest on 16/8/12.
 */

public class DailyBeforeListBean {
    /**
     * date : 20160812
     * stories : [{"images":["http://pic4.zhimg.com/ffdf4a47a087c3968e5142d8b73fcf2b.jpg"],"type":0,"id":8668470,"ga_prefix":"081222","title":"小事 · 骗了骗子的钱"},{"images":["http://pic1.zhimg.com/5601592dcd487f70f72a63fa518c1590.jpg"],"type":0,"id":8679591,"ga_prefix":"081221","title":"看起来很「割裂」的故事，其实是有意为之"},{"title":"整点儿奥运 · 为一位明天凌晨参加奥运比赛的知乎用户加油","ga_prefix":"081220","images":["http://pic3.zhimg.com/78d776b567dd41950f525fce3ddca232.jpg"],"multipic":true,"type":0,"id":8679315},{"images":["http://pic1.zhimg.com/25abcbf1da060f9bf3de64859dcaac74.jpg"],"type":0,"id":8679678,"ga_prefix":"081219","title":"为什么学习英语的过程中，很长时间一直会受到汉语干扰？"},{"title":"一个值得全世界起立致敬的男人：因为我们再也不沉默了","ga_prefix":"081218","images":["http://pic3.zhimg.com/a036e6f2a4c94a011c5b0335bd84868e.jpg"],"multipic":true,"type":0,"id":8679133},{"images":["http://pic3.zhimg.com/1ab4cbe65f5e8295106288106f904376.jpg"],"type":0,"id":8679125,"ga_prefix":"081217","title":"知乎好问题 · 一天吃几个鸡蛋比较好？"},{"images":["http://pic2.zhimg.com/f02fc1ff2ca9b2de687953f2e2d0bc91.jpg"],"type":0,"id":8651703,"ga_prefix":"081216","title":"哥特式建筑，是一种什么样的存在？"},{"images":["http://pic2.zhimg.com/c64a5c2a63d04a989fdd58b9f35ffe41.jpg"],"type":0,"id":8676431,"ga_prefix":"081215","title":"地产入门：商业地产和住宅地产到底有什么区别？"},{"images":["http://pic1.zhimg.com/c41a49510ca44a620e15d3f8bdf42420.jpg"],"type":0,"id":8673887,"ga_prefix":"081214","title":"天才都有点神经质？嗯\u2026\u2026好像是的"},{"images":["http://pic4.zhimg.com/13471bb8c17a7e98f7e4677fd06a7287.jpg"],"type":0,"id":8678092,"ga_prefix":"081213","title":"为了娶到女神，我只好把她的恋女癖爸爸杀了"},{"title":"大误 · 小智的臂力简直可怕","ga_prefix":"081212","images":["http://pic4.zhimg.com/d60a9322c988a8b7a1f49933d33f120f.jpg"],"multipic":true,"type":0,"id":8674919},{"images":["http://pic4.zhimg.com/7d04382b9e4a5b997a0575a4b126348b.jpg"],"type":0,"id":8670773,"ga_prefix":"081211","title":"堵车已经够烦了，为什么还要收「拥堵费」？"},{"images":["http://pic1.zhimg.com/f8184a6b56fc0ea3bb16889848a36200.jpg"],"type":0,"id":8676516,"ga_prefix":"081210","title":"想改变世界只差一个工程师？不如自己做「全栈工程师」"},{"images":["http://pic4.zhimg.com/ff9f92f6ce6bc0e2a16e41686902bdbb.jpg"],"type":0,"id":8669401,"ga_prefix":"081209","title":"抑郁症的发病机制都没弄清，人类是怎么发现抗抑郁药的？"},{"images":["http://pic2.zhimg.com/07ee4967655f3ef2b29d43abc4e09365.jpg"],"type":0,"id":8674728,"ga_prefix":"081208","title":"你可能不了解的世界第一：中国女子扫雷"},{"title":"从此以后见到每一条河，我都如数家珍","ga_prefix":"081207","images":["http://pic4.zhimg.com/0df540aa7b3076e51f93962bce306c93.jpg"],"multipic":true,"type":0,"id":8676539},{"images":["http://pic1.zhimg.com/5b40c986acf759953a1f875837bb8600.jpg"],"type":0,"id":8676057,"ga_prefix":"081207","title":"Facebook 分析师列给你，7 周入行互联网数据分析教程"},{"images":["http://pic3.zhimg.com/9eb54944dc591df518cf63a1f40f09b6.jpg"],"type":0,"id":8676049,"ga_prefix":"081207","title":"苹果资深公关经理总结了十年来的经验，and, one more thing\u2026"},{"images":["http://pic4.zhimg.com/2c8a3f4d43d996807b258813e07e9c5b.jpg"],"type":0,"id":8676899,"ga_prefix":"081207","title":"读读日报 24 小时热门 TOP 5 · TFBOYS 三周年"},{"images":["http://pic3.zhimg.com/bd493885f45a5a06a26259d7278a621e.jpg"],"type":0,"id":8674308,"ga_prefix":"081206","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    /**
     * images : ["http://pic4.zhimg.com/ffdf4a47a087c3968e5142d8b73fcf2b.jpg"]
     * type : 0
     * id : 8668470
     * ga_prefix : 081222
     * title : 小事 · 骗了骗子的钱
     */

    private List<DailyListBean.StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<DailyListBean.StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<DailyListBean.StoriesBean> stories) {
        this.stories = stories;
    }

//    public static class StoriesBean {
//        private int type;
//        private int id;
//        private String ga_prefix;
//        private String title;
//        private List<String> images;
//
//        public int getType() {
//            return type;
//        }
//
//        public void setType(int type) {
//            this.type = type;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getGa_prefix() {
//            return ga_prefix;
//        }
//
//        public void setGa_prefix(String ga_prefix) {
//            this.ga_prefix = ga_prefix;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public List<String> getImages() {
//            return images;
//        }
//
//        public void setImages(List<String> images) {
//            this.images = images;
//        }
//    }
}
