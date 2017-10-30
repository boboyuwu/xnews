package com.boboyuwu.xnews.beans;

import java.util.List;

/**
 * Created by wubo on 2017/9/29.
 */

public class VideoNews {

    private List<VideoNewsBean> mVideoNewsBeanList;

    public List<VideoNewsBean> getVideoNewsBeanList() {
        return mVideoNewsBeanList;
    }

    public void setVideoNewsBeanList(List<VideoNewsBean> videoNewsBeanList) {
        this.mVideoNewsBeanList = videoNewsBeanList;
    }

    public static class VideoNewsBean {
        /**
         * cover : http://vimg3.ws.126.net/image/snapshot/2017/9/R/5/VCUITDLR5.jpg
         * description : 冯提莫用这首《易燃易爆炸》，保住了自己斗鱼一姐的地位
         * length : 85
         * m3u8_url : http://flv.bn.netease.com/videolib3/1709/29/Blsay3062/SD/movie_index.m3u8
         * mp4_url : http://flv3.bn.netease.com/videolib3/1709/29/Blsay3062/SD/Blsay3062-mobile.mp4
         * playCount : 0
         * playersize : 0
         * ptime : 2017-09-29 13:31:34
         * replyBoard : video_bbs
         * replyCount : 0
         * replyid : GUISJG8Q050835RB
         * sectiontitle :
         * sizeHD : 0
         * sizeSD : 6375
         * sizeSHD : 0
         * title : 冯提莫用这首《易燃易爆炸》，保住了自己斗鱼一姐的地位
         * topicDesc : 最新，最潮的时尚穿搭。原创搭配技巧，让女孩子轻松秀出女神风采，把握时尚风向。
         * topicImg : http://vimg3.ws.126.net/image/snapshot/2017/7/K/B/VCPGH5VKB.jpg
         * topicName : 百搭女神范
         * topicSid : VCPGH5VK9
         * vid : VGUISJG8Q
         * videoTopic : {"alias":"潮流搭配，轻松秀出女神范","ename":"T1495461373492","tid":"T1495461373492","tname":"百搭女神范","topic_icons":"http://dingyue.nosdn.127.net/1qSurFUi1T1RAgUshPPWmazlq947vY3PipELyxiHJpJQk1498060387039.jpg"}
         * videosource : 新媒体
         * votecount : 0
         * m3u8Hd_url : http://flv.bn.netease.com/videolib3/1709/29/lMfbh2972/HD/movie_index.m3u8
         * mp4Hd_url : http://flv3.bn.netease.com/videolib3/1709/29/lMfbh2972/HD/lMfbh2972-mobile.mp4
         */

        private String cover;
        private String description;
        private int length;
        private String m3u8_url;
        private String mp4_url;
        private int playCount;
        private int playersize;
        private String ptime;
        private String replyBoard;
        private int replyCount;
        private String replyid;
        private String sectiontitle;
        private int sizeHD;
        private int sizeSD;
        private int sizeSHD;
        private String title;
        private String topicDesc;
        private String topicImg;
        private String topicName;
        private String topicSid;
        private String vid;
        private VideoTopicBean videoTopic;
        private String videosource;
        private int votecount;
        private String m3u8Hd_url;
        private String mp4Hd_url;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public String getM3u8_url() {
            return m3u8_url;
        }

        public void setM3u8_url(String m3u8_url) {
            this.m3u8_url = m3u8_url;
        }

        public String getMp4_url() {
            return mp4_url;
        }

        public void setMp4_url(String mp4_url) {
            this.mp4_url = mp4_url;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public int getPlayersize() {
            return playersize;
        }

        public void setPlayersize(int playersize) {
            this.playersize = playersize;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getReplyBoard() {
            return replyBoard;
        }

        public void setReplyBoard(String replyBoard) {
            this.replyBoard = replyBoard;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public String getReplyid() {
            return replyid;
        }

        public void setReplyid(String replyid) {
            this.replyid = replyid;
        }

        public String getSectiontitle() {
            return sectiontitle;
        }

        public void setSectiontitle(String sectiontitle) {
            this.sectiontitle = sectiontitle;
        }

        public int getSizeHD() {
            return sizeHD;
        }

        public void setSizeHD(int sizeHD) {
            this.sizeHD = sizeHD;
        }

        public int getSizeSD() {
            return sizeSD;
        }

        public void setSizeSD(int sizeSD) {
            this.sizeSD = sizeSD;
        }

        public int getSizeSHD() {
            return sizeSHD;
        }

        public void setSizeSHD(int sizeSHD) {
            this.sizeSHD = sizeSHD;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTopicDesc() {
            return topicDesc;
        }

        public void setTopicDesc(String topicDesc) {
            this.topicDesc = topicDesc;
        }

        public String getTopicImg() {
            return topicImg;
        }

        public void setTopicImg(String topicImg) {
            this.topicImg = topicImg;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public String getTopicSid() {
            return topicSid;
        }

        public void setTopicSid(String topicSid) {
            this.topicSid = topicSid;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public VideoTopicBean getVideoTopic() {
            return videoTopic;
        }

        public void setVideoTopic(VideoTopicBean videoTopic) {
            this.videoTopic = videoTopic;
        }

        public String getVideosource() {
            return videosource;
        }

        public void setVideosource(String videosource) {
            this.videosource = videosource;
        }

        public int getVotecount() {
            return votecount;
        }

        public void setVotecount(int votecount) {
            this.votecount = votecount;
        }

        public String getM3u8Hd_url() {
            return m3u8Hd_url;
        }

        public void setM3u8Hd_url(String m3u8Hd_url) {
            this.m3u8Hd_url = m3u8Hd_url;
        }

        public String getMp4Hd_url() {
            return mp4Hd_url;
        }

        public void setMp4Hd_url(String mp4Hd_url) {
            this.mp4Hd_url = mp4Hd_url;
        }

        public static class VideoTopicBean {
            /**
             * alias : 潮流搭配，轻松秀出女神范
             * ename : T1495461373492
             * tid : T1495461373492
             * tname : 百搭女神范
             * topic_icons : http://dingyue.nosdn.127.net/1qSurFUi1T1RAgUshPPWmazlq947vY3PipELyxiHJpJQk1498060387039.jpg
             */

            private String alias;
            private String ename;
            private String tid;
            private String tname;
            private String topic_icons;

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public String getEname() {
                return ename;
            }

            public void setEname(String ename) {
                this.ename = ename;
            }

            public String getTid() {
                return tid;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public String getTname() {
                return tname;
            }

            public void setTname(String tname) {
                this.tname = tname;
            }

            public String getTopic_icons() {
                return topic_icons;
            }

            public void setTopic_icons(String topic_icons) {
                this.topic_icons = topic_icons;
            }
        }
    }
}
