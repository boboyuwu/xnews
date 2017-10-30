package com.boboyuwu.xnews.common.utils;

import android.text.TextUtils;

import com.boboyuwu.xnews.beans.NewsDetailBean.ImgBean;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wubo on 2017/9/27.
 */

public class HtmlUtils {

     /**
      *删除html标签
      */
     public static String removeHtmlTag(String inputString) {
          if (inputString == null)
               return null;
          String htmlStr = inputString; // 含html标签的字符串
          String textStr = "";
          java.util.regex.Pattern p_script;
          java.util.regex.Matcher m_script;
          java.util.regex.Pattern p_style;
          java.util.regex.Matcher m_style;
          java.util.regex.Pattern p_html;
          java.util.regex.Matcher m_html;
          java.util.regex.Pattern p_special;
          java.util.regex.Matcher m_special;
          try {
               //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
               String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
               //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
               String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
               // 定义HTML标签的正则表达式
               String regEx_html = "<[^>]+>";
               // 定义一些特殊字符的正则表达式 如：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               String regEx_special = "\\&[a-zA-Z]{1,10};";

               p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
               m_script = p_script.matcher(htmlStr);
               htmlStr = m_script.replaceAll(""); // 过滤script标签
               p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
               m_style = p_style.matcher(htmlStr);
               htmlStr = m_style.replaceAll(""); // 过滤style标签
               p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
               m_html = p_html.matcher(htmlStr);
               htmlStr = m_html.replaceAll(""); // 过滤html标签
               p_special = Pattern.compile(regEx_special, Pattern.CASE_INSENSITIVE);
               m_special = p_special.matcher(htmlStr);
               htmlStr = m_special.replaceAll(""); // 过滤特殊标签
               textStr = htmlStr;
          } catch (Exception e) {
               e.printStackTrace();
          }
          return textStr;// 返回文本字符串
     }

     /**
      *替换html标签中IMG标签
      */
     public static String replaceHtmlTag(String inputString, List<ImgBean> list){
          String htmlStr = inputString; // 含IMG标签的字符串
          String str = "";
          for (ImgBean imgBean : list) {
               String pattern = imgBean.getRef();
               Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
               Matcher m = r.matcher(htmlStr);
              String[] split = imgBean.getPixel().split("\\*");
               htmlStr=m.replaceAll(
                       "<div style=\"text-align: center\">"
                       +"<img src="+"\""+imgBean.getSrc()+"\""+"width="+"\""+split[0]+"\""+
                       "height="+"\""+split[1]+"\"" +">"
                       +"</div>"
                      );
          }
          if(TextUtils.isEmpty(str)){
               str=htmlStr;
          }
          Logger.i("NewHtmlBody:"+str);
          return str;
     }
}
