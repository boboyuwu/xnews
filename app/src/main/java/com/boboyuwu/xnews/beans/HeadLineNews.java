package com.boboyuwu.xnews.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wubo on 2017/8/30.
 */

public class HeadLineNews implements Parcelable {
    //
    // list T1348649580692   headline T1348647909107
    private List<HeadLineNewsBean> mHeadLineNewsBeanList;

    public List<HeadLineNewsBean> getHeadLineNewsList() {
        return mHeadLineNewsBeanList;
    }

    public void setHeadLineNewsList(List<HeadLineNewsBean> headLineNewsList) {
        mHeadLineNewsBeanList = headLineNewsList;
    }
    public static class HeadLineNewsBean implements Parcelable {
        /**
         * imgextra : [{"imgsrc":"http://cms-bucket.nosdn.127.net/5283488080eb434c9b44124b4ce7db8020170831120016.jpeg"},{"imgsrc":"http://cms-bucket.nosdn.127.net/1b714caa0d93484087b71a85cc2eb3d220170831120015.png"}]
         * template : normal1
         * skipID : 00AN0001|2273208
         * lmodify : 2017-08-31 13:15:20
         * postid : PHOT25BTO000100A
         * source : 中国新闻网
         * title : 郑州一高校花600万打造“高颜值”食堂
         * mtime : 2017-08-31 13:15:20
         * hasImg : 1
         * topic_background : http://img2.cache.netease.com/m/newsapp/reading/cover1/C1348646712614.jpg
         * digest :
         * photosetID : 00AN0001|2273208
         * boardid : photoview_bbs
         * alias : Top News
         * hasAD : 1
         * imgsrc : http://cms-bucket.nosdn.127.net/475cf8c094a0481584b882572633ca5c20170831120016.jpeg
         * ptime : 2017-08-31 11:57:53
         * daynum : 17409
         * hasHead : 1
         * order : 1
         * votecount : 1137
         * hasCover : false
         * docid : 9IG74V5H00963VRO_0001set2273208updateDoc
         * tname : 头条
         * priority : 355
         * ads : [{"subtitle":"","skipType":"photoset","skipID":"00AP0001|2273205","tag":"photoset","title":"农民办乡村\"红色记忆馆\" 藏品500多件","imgsrc":"http://cms-bucket.nosdn.127.net/e80425e73dba469cbf807e4fd12328ae20170831112620.jpeg","url":"00AP0001|2273205"},{"subtitle":"","skipType":"photoset","skipID":"00AO0001|2273157","tag":"photoset","title":"朝射弹画面曝光 金正恩:报复日本侵略","imgsrc":"http://cms-bucket.nosdn.127.net/d206a940945d4d548d3cb5fdf6d7a05c20170831005246.jpeg","url":"00AO0001|2273157"},{"subtitle":"","skipType":"photoset","skipID":"00AO0001|2273203","tag":"photoset","title":"纽约民众抗议特朗普终止人道主义救援","imgsrc":"http://cms-bucket.nosdn.127.net/344a42701b8a4496b1964f53bda115ba20170831111135.jpeg","url":"00AO0001|2273203"},{"subtitle":"","skipType":"photoset","skipID":"00AP0001|2273198","tag":"photoset","title":"\"瘾君子\"抢劫并捅死醉酒男 10天后落网","imgsrc":"http://cms-bucket.nosdn.127.net/308c821f2f57435b9240e5e78785c55d20170831102854.jpeg","url":"00AP0001|2273198"},{"subtitle":"","skipType":"photoset","skipID":"00AO0001|2273169","tag":"photoset","title":"美国休斯敦洪灾肆虐 避难所不堪重负","imgsrc":"http://cms-bucket.nosdn.127.net/a58953f34b924734a9c0ca222158575620170831082627.png","url":"00AO0001|2273169"}]
         * ename : androidnews
         * replyCount : 1270
         * imgsum : 6
         * hasIcon : false
         * skipType : photoset
         * cid : C1348646712614
         * url_3w : http://news.163.com/17/0830/20/CT452320000189FH.html
         * url : http://3g.163.com/news/17/0830/20/CT452320000189FH.html
         * ltitle : 习近平将主持金砖国家领导人第九次会晤
         * subtitle :
         * specialID : S1504147939219
         */

        private String template;
        private String skipID;
        private String lmodify;
        private String postid;
        private String source;
        private String title;
        private String mtime;
        private int hasImg;
        private String topic_background;
        private String digest;
        private String photosetID;
        private String boardid;
        private String alias;
        private int hasAD;
        private String imgsrc;
        private String ptime;
        private String daynum;
        private int hasHead;
        private int order;
        private int votecount;
        private boolean hasCover;
        private String docid;
        private String tname;
        private int priority;
        private String ename;
        private int replyCount;
        private int imgsum;
        private boolean hasIcon;
        private String skipType;
        private String cid;
        private String url_3w;
        private String url;
        private String ltitle;
        private String subtitle;
        private String specialID;
        private List<ImgextraBean> imgextra;
        private List<AdsBean> ads;

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public String getSkipID() {
            return skipID;
        }

        public void setSkipID(String skipID) {
            this.skipID = skipID;
        }

        public String getLmodify() {
            return lmodify;
        }

        public void setLmodify(String lmodify) {
            this.lmodify = lmodify;
        }

        public String getPostid() {
            return postid;
        }

        public void setPostid(String postid) {
            this.postid = postid;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMtime() {
            return mtime;
        }

        public void setMtime(String mtime) {
            this.mtime = mtime;
        }

        public int getHasImg() {
            return hasImg;
        }

        public void setHasImg(int hasImg) {
            this.hasImg = hasImg;
        }

        public String getTopic_background() {
            return topic_background;
        }

        public void setTopic_background(String topic_background) {
            this.topic_background = topic_background;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getPhotosetID() {
            return photosetID;
        }

        public void setPhotosetID(String photosetID) {
            this.photosetID = photosetID;
        }

        public String getBoardid() {
            return boardid;
        }

        public void setBoardid(String boardid) {
            this.boardid = boardid;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public int getHasAD() {
            return hasAD;
        }

        public void setHasAD(int hasAD) {
            this.hasAD = hasAD;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getDaynum() {
            return daynum;
        }

        public void setDaynum(String daynum) {
            this.daynum = daynum;
        }

        public int getHasHead() {
            return hasHead;
        }

        public void setHasHead(int hasHead) {
            this.hasHead = hasHead;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getVotecount() {
            return votecount;
        }

        public void setVotecount(int votecount) {
            this.votecount = votecount;
        }

        public boolean isHasCover() {
            return hasCover;
        }

        public void setHasCover(boolean hasCover) {
            this.hasCover = hasCover;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public int getImgsum() {
            return imgsum;
        }

        public void setImgsum(int imgsum) {
            this.imgsum = imgsum;
        }

        public boolean isHasIcon() {
            return hasIcon;
        }

        public void setHasIcon(boolean hasIcon) {
            this.hasIcon = hasIcon;
        }

        public String getSkipType() {
            return skipType;
        }

        public void setSkipType(String skipType) {
            this.skipType = skipType;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getUrl_3w() {
            return url_3w;
        }

        public void setUrl_3w(String url_3w) {
            this.url_3w = url_3w;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getLtitle() {
            return ltitle;
        }

        public void setLtitle(String ltitle) {
            this.ltitle = ltitle;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getSpecialID() {
            return specialID;
        }

        public void setSpecialID(String specialID) {
            this.specialID = specialID;
        }

        public List<ImgextraBean> getImgextra() {
            return imgextra;
        }

        public void setImgextra(List<ImgextraBean> imgextra) {
            this.imgextra = imgextra;
        }

        public List<AdsBean> getAds() {
            return ads;
        }

        public void setAds(List<AdsBean> ads) {
            this.ads = ads;
        }

        public static class ImgextraBean implements Parcelable {
            /**
             * imgsrc : http://cms-bucket.nosdn.127.net/5283488080eb434c9b44124b4ce7db8020170831120016.jpeg
             */

            private String imgsrc;

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.imgsrc);
            }

            public ImgextraBean() {
            }

            protected ImgextraBean(Parcel in) {
                this.imgsrc = in.readString();
            }

            public static final Creator<ImgextraBean> CREATOR = new Creator<ImgextraBean>() {
                @Override
                public ImgextraBean createFromParcel(Parcel source) {
                    return new ImgextraBean(source);
                }

                @Override
                public ImgextraBean[] newArray(int size) {
                    return new ImgextraBean[size];
                }
            };
        }

        public static class AdsBean implements Parcelable {
            /**
             * subtitle :
             * skipType : photoset
             * skipID : 00AP0001|2273205
             * tag : photoset
             * title : 农民办乡村"红色记忆馆" 藏品500多件
             * imgsrc : http://cms-bucket.nosdn.127.net/e80425e73dba469cbf807e4fd12328ae20170831112620.jpeg
             * url : 00AP0001|2273205
             */

            private String subtitle;
            private String skipType;
            private String skipID;
            private String tag;
            private String title;
            private String imgsrc;
            private String url;

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getSkipType() {
                return skipType;
            }

            public void setSkipType(String skipType) {
                this.skipType = skipType;
            }

            public String getSkipID() {
                return skipID;
            }

            public void setSkipID(String skipID) {
                this.skipID = skipID;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.subtitle);
                dest.writeString(this.skipType);
                dest.writeString(this.skipID);
                dest.writeString(this.tag);
                dest.writeString(this.title);
                dest.writeString(this.imgsrc);
                dest.writeString(this.url);
            }

            public AdsBean() {
            }

            protected AdsBean(Parcel in) {
                this.subtitle = in.readString();
                this.skipType = in.readString();
                this.skipID = in.readString();
                this.tag = in.readString();
                this.title = in.readString();
                this.imgsrc = in.readString();
                this.url = in.readString();
            }

            public static final Creator<AdsBean> CREATOR = new Creator<AdsBean>() {
                @Override
                public AdsBean createFromParcel(Parcel source) {
                    return new AdsBean(source);
                }

                @Override
                public AdsBean[] newArray(int size) {
                    return new AdsBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.template);
            dest.writeString(this.skipID);
            dest.writeString(this.lmodify);
            dest.writeString(this.postid);
            dest.writeString(this.source);
            dest.writeString(this.title);
            dest.writeString(this.mtime);
            dest.writeInt(this.hasImg);
            dest.writeString(this.topic_background);
            dest.writeString(this.digest);
            dest.writeString(this.photosetID);
            dest.writeString(this.boardid);
            dest.writeString(this.alias);
            dest.writeInt(this.hasAD);
            dest.writeString(this.imgsrc);
            dest.writeString(this.ptime);
            dest.writeString(this.daynum);
            dest.writeInt(this.hasHead);
            dest.writeInt(this.order);
            dest.writeInt(this.votecount);
            dest.writeByte(this.hasCover ? (byte) 1 : (byte) 0);
            dest.writeString(this.docid);
            dest.writeString(this.tname);
            dest.writeInt(this.priority);
            dest.writeString(this.ename);
            dest.writeInt(this.replyCount);
            dest.writeInt(this.imgsum);
            dest.writeByte(this.hasIcon ? (byte) 1 : (byte) 0);
            dest.writeString(this.skipType);
            dest.writeString(this.cid);
            dest.writeString(this.url_3w);
            dest.writeString(this.url);
            dest.writeString(this.ltitle);
            dest.writeString(this.subtitle);
            dest.writeString(this.specialID);
            dest.writeList(this.imgextra);
            dest.writeList(this.ads);
        }

        public HeadLineNewsBean() {
        }

        protected HeadLineNewsBean(Parcel in) {
            this.template = in.readString();
            this.skipID = in.readString();
            this.lmodify = in.readString();
            this.postid = in.readString();
            this.source = in.readString();
            this.title = in.readString();
            this.mtime = in.readString();
            this.hasImg = in.readInt();
            this.topic_background = in.readString();
            this.digest = in.readString();
            this.photosetID = in.readString();
            this.boardid = in.readString();
            this.alias = in.readString();
            this.hasAD = in.readInt();
            this.imgsrc = in.readString();
            this.ptime = in.readString();
            this.daynum = in.readString();
            this.hasHead = in.readInt();
            this.order = in.readInt();
            this.votecount = in.readInt();
            this.hasCover = in.readByte() != 0;
            this.docid = in.readString();
            this.tname = in.readString();
            this.priority = in.readInt();
            this.ename = in.readString();
            this.replyCount = in.readInt();
            this.imgsum = in.readInt();
            this.hasIcon = in.readByte() != 0;
            this.skipType = in.readString();
            this.cid = in.readString();
            this.url_3w = in.readString();
            this.url = in.readString();
            this.ltitle = in.readString();
            this.subtitle = in.readString();
            this.specialID = in.readString();
            this.imgextra = new ArrayList<ImgextraBean>();
            in.readList(this.imgextra, ImgextraBean.class.getClassLoader());
            this.ads = new ArrayList<AdsBean>();
            in.readList(this.ads, AdsBean.class.getClassLoader());
        }

        public static final Creator<HeadLineNewsBean> CREATOR = new Creator<HeadLineNewsBean>() {
            @Override
            public HeadLineNewsBean createFromParcel(Parcel source) {
                return new HeadLineNewsBean(source);
            }

            @Override
            public HeadLineNewsBean[] newArray(int size) {
                return new HeadLineNewsBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mHeadLineNewsBeanList);
    }

    public HeadLineNews() {
    }

    protected HeadLineNews(Parcel in) {
        this.mHeadLineNewsBeanList = in.createTypedArrayList(HeadLineNewsBean.CREATOR);
    }

    public static final Creator<HeadLineNews> CREATOR = new Creator<HeadLineNews>() {
        @Override
        public HeadLineNews createFromParcel(Parcel source) {
            return new HeadLineNews(source);
        }

        @Override
        public HeadLineNews[] newArray(int size) {
            return new HeadLineNews[size];
        }
    };
}
