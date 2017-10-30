package com.boboyuwu.xnews.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wubo on 2017/9/28.
 */

public class PrettyPhotos implements Parcelable {

    /**
     * error : false
     * results : [{"_id":"59c46011421aa972845f2089","createdAt":"2017-09-22T08:57:53.998Z","desc":"9-22","publishedAt":"2017-09-26T12:12:07.813Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjs25xfq48j20u00mhtb6.jpg","used":true,"who":"daimajia"},{"_id":"59c30b37421aa9727ddd19b4","createdAt":"2017-09-21T08:43:35.381Z","desc":"9-21","publishedAt":"2017-09-21T13:27:15.675Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjqw4n86lhj20u00u01kx.jpg","used":true,"who":"daimajia"},{"_id":"59c1b3e0421aa9727ddd19a8","createdAt":"2017-09-20T08:18:40.702Z","desc":"9-20","publishedAt":"2017-09-20T13:17:38.709Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjppsiclufj20u011igo5.jpg","used":true,"who":"带马甲"},{"_id":"59bf0c37421aa9118887ac33","createdAt":"2017-09-18T07:58:47.204Z","desc":"9-18","publishedAt":"2017-09-19T12:07:31.405Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjndz4dh39j20u00u0ada.jpg","used":true,"who":"daimajia"},{"_id":"59b720df421aa9118887ac18","createdAt":"2017-09-12T07:48:47.73Z","desc":"9-12","publishedAt":"2017-09-14T16:36:51.63Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjgfyxgwgnj20u00gvgmt.jpg","used":true,"who":"daimajia"},{"_id":"59b5cfb5421aa9118887ac0b","createdAt":"2017-09-11T07:50:13.510Z","desc":"9-11","publishedAt":"2017-09-12T08:15:08.26Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjfae1hjslj20u00tyq4x.jpg","used":true,"who":"代码家"},{"_id":"59b0d757421aa901bcb7dc0c","createdAt":"2017-09-07T13:21:27.937Z","desc":"9-7","publishedAt":"2017-09-07T13:25:26.977Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034ly1fjaxhky81vj20u00u0ta1.jpg","used":true,"who":"daimajia"},{"_id":"599f7362421aa901c85e5fc2","createdAt":"2017-08-25T08:46:26.461Z","desc":"8-25","publishedAt":"2017-09-06T12:18:11.687Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fivohbbwlqj20u011idmx.jpg","used":true,"who":"daimajia"},{"_id":"59aca203421aa901c1c0a8d8","createdAt":"2017-09-04T08:44:51.44Z","desc":"09-04","publishedAt":"2017-09-05T11:29:05.240Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fj78mpyvubj20u011idjg.jpg","used":true,"who":"dmj"},{"_id":"59a8cfdc421aa901c1c0a8c7","createdAt":"2017-09-01T11:11:24.81Z","desc":"9-1","publishedAt":"2017-09-01T12:55:52.582Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fj3w0emfcbj20u011iabm.jpg","used":true,"who":"daimajia"},{"_id":"59a755a2421aa901c85e5fea","createdAt":"2017-08-31T08:17:38.117Z","desc":"8-31","publishedAt":"2017-08-31T08:22:07.982Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fj2ld81qvoj20u00xm0y0.jpg","used":true,"who":"daimajia"},{"_id":"59a35f6d421aa901b9dc4643","createdAt":"2017-08-28T08:10:21.141Z","desc":"8-28","publishedAt":"2017-08-29T12:19:18.634Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fiz4ar9pq8j20u010xtbk.jpg","used":true,"who":"代码家"},{"_id":"599e2220421aa901b9dc462d","createdAt":"2017-08-24T08:47:28.949Z","desc":"8-24","publishedAt":"2017-08-24T12:43:10.124Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fiuiw5givwj20u011h79a.jpg","used":true,"who":"daimajia"},{"_id":"599ccace421aa901c85e5fb8","createdAt":"2017-08-23T08:22:38.611Z","desc":"8-23","publishedAt":"2017-08-23T12:12:15.166Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fitcjyruajj20u011h412.jpg","used":true,"who":"daimajia"},{"_id":"599b7cf5421aa901c1c0a867","createdAt":"2017-08-22T08:38:13.732Z","desc":"8-22","publishedAt":"2017-08-22T12:02:15.769Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fis7dvesn6j20u00u0jt4.jpg","used":true,"who":"代码家"},{"_id":"599a299a421aa901b9dc460f","createdAt":"2017-08-21T08:30:18.487Z","desc":"8-21","publishedAt":"2017-08-21T11:38:57.363Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fir1jbpod5j20ip0newh3.jpg","used":true,"who":"daimajia"},{"_id":"599386fe421aa9672cdf0812","createdAt":"2017-08-16T07:42:54.135Z","desc":"8-16","publishedAt":"2017-08-17T11:36:42.967Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fil82i7zsmj20u011hwja.jpg","used":true,"who":"daimajia"},{"_id":"599237dd421aa96729c57246","createdAt":"2017-08-15T07:53:01.962Z","desc":"8-15","publishedAt":"2017-08-15T13:32:36.998Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fik2q1k3noj20u00u07wh.jpg","used":true,"who":"daimajia"},{"_id":"59907386421aa9672cdf07ff","createdAt":"2017-08-13T23:43:02.253Z","desc":"8-13","publishedAt":"2017-08-14T17:04:50.266Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fiiiyfcjdoj20u00u0ju0.jpg","used":true,"who":"dmj"},{"_id":"598bb8f0421aa90ca3bb6c01","createdAt":"2017-08-10T09:37:52.684Z","desc":"8-10","publishedAt":"2017-08-11T14:05:53.749Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fiednrydq8j20u011itfz.jpg","used":true,"who":"带马甲"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Parcelable {
        /**
         * _id : 59c46011421aa972845f2089
         * createdAt : 2017-09-22T08:57:53.998Z
         * desc : 9-22
         * publishedAt : 2017-09-26T12:12:07.813Z
         * source : chrome
         * type : 福利
         * url : https://ws1.sinaimg.cn/large/610dc034ly1fjs25xfq48j20u00mhtb6.jpg
         * used : true
         * who : daimajia
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this._id);
            dest.writeString(this.createdAt);
            dest.writeString(this.desc);
            dest.writeString(this.publishedAt);
            dest.writeString(this.source);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeByte(this.used ? (byte) 1 : (byte) 0);
            dest.writeString(this.who);
        }

        public ResultsBean() {
        }

        protected ResultsBean(Parcel in) {
            this._id = in.readString();
            this.createdAt = in.readString();
            this.desc = in.readString();
            this.publishedAt = in.readString();
            this.source = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.used = in.readByte() != 0;
            this.who = in.readString();
        }

        public static final Creator<ResultsBean> CREATOR = new Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel source) {
                return new ResultsBean(source);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.error ? (byte) 1 : (byte) 0);
        dest.writeList(this.results);
    }

    public PrettyPhotos() {
    }

    protected PrettyPhotos(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = new ArrayList<ResultsBean>();
        in.readList(this.results, ResultsBean.class.getClassLoader());
    }

    public static final Creator<PrettyPhotos> CREATOR = new Creator<PrettyPhotos>() {
        @Override
        public PrettyPhotos createFromParcel(Parcel source) {
            return new PrettyPhotos(source);
        }

        @Override
        public PrettyPhotos[] newArray(int size) {
            return new PrettyPhotos[size];
        }
    };
}
