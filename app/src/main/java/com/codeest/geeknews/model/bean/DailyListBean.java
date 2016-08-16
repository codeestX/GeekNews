package com.codeest.geeknews.model.bean;

import java.util.List;

/**
 * Created by codeest on 16/8/12.
 */

public class DailyListBean {
    /**
     * date : 20160811
     * stories : [{"images":["http://pic4.zhimg.com/95625b7bacfff0f6b1105ff3e71f37cb.jpg"],"type":0,"id":8675619,"ga_prefix":"081122","title":"小事 · 我在戒网瘾学校里经历了什么"},{"title":"有人看到情欲，有人看到暗黑，有人认为这是年度最值得期待的佳作","ga_prefix":"081121","images":["http://pic4.zhimg.com/67cbc856dbed2503dffe5afbdcccfc57.jpg"],"multipic":true,"type":0,"id":8675273},{"title":"整点儿奥运 · 别走啊，我用锅铲跟你打，行吗？","ga_prefix":"081120","images":["http://pic4.zhimg.com/af18477180b614d5f438c2dd06496bb3.jpg"],"multipic":true,"type":0,"id":8673847},{"title":"照片调色加俩黑条 = 电影感？一群导演和摄影师哭瞎","ga_prefix":"081119","images":["http://pic1.zhimg.com/6555bcf2b733a3cb0e26225ea5dcc548.jpg"],"multipic":true,"type":0,"id":8675601},{"images":["http://pic4.zhimg.com/c3fd1923a8f012b2a7770cffa6c8a0c3.jpg"],"type":0,"id":8675582,"ga_prefix":"081118","title":"性别不同，更有可能站在不同的「政」营？"},{"images":["http://pic2.zhimg.com/dc11586a263af5bbcfa27d7fd80bd835.jpg"],"type":0,"id":8671349,"ga_prefix":"081117","title":"知乎好问题 · 低学历是否比高学历更加会赚钱？"},{"title":"整个彩虹合唱团都出动了，只为让你唱好合唱","ga_prefix":"081116","images":["http://pic3.zhimg.com/d530aaba3304e52c8543a416e6d4670e.jpg"],"multipic":true,"type":0,"id":8664572},{"title":"柠檬 + 酸奶 + 冰淇淋，每一样都能让被蒸熟的我复活","ga_prefix":"081114","images":["http://pic2.zhimg.com/272d4bcd106271178620c864490c6039.jpg"],"multipic":true,"type":0,"id":8671431},{"images":["http://pic4.zhimg.com/944d3062d9709285763b276894679443.jpg"],"type":0,"id":8665792,"ga_prefix":"081112","title":"大误 · 我无法砸了我的手机"},{"images":["http://pic3.zhimg.com/92d67149451bfc24228e84b00dcdd316.jpg"],"type":0,"id":8673963,"ga_prefix":"081111","title":"有大公司 offer 却选择了小公司是为什么？"},{"images":["http://pic3.zhimg.com/3f734ae8eaa2c919f9df7bdadb258572.jpg"],"type":0,"id":8672587,"ga_prefix":"081110","title":"把加密算法本身保密起来，不就是绝对安全了？"},{"images":["http://pic4.zhimg.com/1818c8c204592c03d2322ee958261d4b.jpg"],"type":0,"id":8671284,"ga_prefix":"081109","title":"「孙杨夺冠把泳帽误扔泳池里以后，我发现我更喜欢他了」"},{"images":["http://pic4.zhimg.com/d2d586e6b82902c30a17605cdfb894ef.jpg"],"type":0,"id":8672664,"ga_prefix":"081108","title":"只是寄个快递，结果律师被「坑惨」了"},{"images":["http://pic4.zhimg.com/8adbdaad1daaad81e09cc55eb3a6b553.jpg"],"type":0,"id":8652559,"ga_prefix":"081107","title":"还在比较「唱功」的人，听不懂李宗盛"},{"images":["http://pic2.zhimg.com/efa0c4d9b0dba8d1b45fb9161bb9c9b1.jpg"],"type":0,"id":8672657,"ga_prefix":"081107","title":"有时也会迷惑，我到底是来种地，还是来读硕士的"},{"title":"吃河豚会死吗？不吃河豚会死吗？","ga_prefix":"081107","images":["http://pic4.zhimg.com/c11e628e679fa30b30a0103f757edadf.jpg"],"multipic":true,"type":0,"id":8655025},{"images":["http://pic4.zhimg.com/7eabe3ce21a0728cc7e149d7f92865f7.jpg"],"type":0,"id":8672907,"ga_prefix":"081107","title":"读读日报 24 小时热门 TOP 5 · 为什么孙杨总会被别人质疑「服药」"},{"images":["http://pic1.zhimg.com/00da4103ad41aa4f39118004a4dd7078.jpg"],"type":0,"id":8670451,"ga_prefix":"081106","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic4.zhimg.com/1f45c1ed56116f21955878dff00e8c37.jpg","type":0,"id":8673847,"ga_prefix":"081120","title":"整点儿奥运 · 别走啊，我用锅铲跟你打，行吗？"},{"image":"http://pic4.zhimg.com/8d962d0d877d42518a9e6f73fe35ac8b.jpg","type":0,"id":8664572,"ga_prefix":"081116","title":"整个彩虹合唱团都出动了，只为让你唱好合唱"},{"image":"http://pic1.zhimg.com/e73a48ac3c95f55c715d8625957808a4.jpg","type":0,"id":8671349,"ga_prefix":"081117","title":"知乎好问题 · 低学历是否比高学历更加会赚钱？"},{"image":"http://pic3.zhimg.com/eca053868591893a70e8a48a05979d46.jpg","type":0,"id":8671431,"ga_prefix":"081114","title":"柠檬 + 酸奶 + 冰淇淋，每一样都能让被蒸熟的我复活"},{"image":"http://pic1.zhimg.com/8504a2e5046bd33ca556daaaeb3981e8.jpg","type":0,"id":8671284,"ga_prefix":"081109","title":"「孙杨夺冠把泳帽误扔泳池里以后，我发现我更喜欢他了」"}]
     */

    private String date;
    /**
     * images : ["http://pic4.zhimg.com/95625b7bacfff0f6b1105ff3e71f37cb.jpg"]
     * type : 0
     * id : 8675619
     * ga_prefix : 081122
     * title : 小事 · 我在戒网瘾学校里经历了什么
     */

    private List<StoriesBean> stories;
    /**
     * image : http://pic4.zhimg.com/1f45c1ed56116f21955878dff00e8c37.jpg
     * type : 0
     * id : 8673847
     * ga_prefix : 081120
     * title : 整点儿奥运 · 别走啊，我用锅铲跟你打，行吗？
     */

    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;
        private boolean readState;

        public boolean getReadState() {
            return readState;
        }

        public void setReadState(boolean readState) {
            this.readState = readState;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
