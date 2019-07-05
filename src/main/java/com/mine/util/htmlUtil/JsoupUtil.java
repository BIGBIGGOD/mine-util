package com.mine.util.htmlUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description 利用Jsoup解析html文本
 * @DATE 2019/7/5 15:32
 */
public class JsoupUtil {

    public static String analysisHTML(String html) {
        //将HTML文本转换成Document对象
        Document document = Jsoup.parse(html);

        //根据HTML文本中的元素标签进行解析获取对应数据
        Elements body  = document.getElementsByTag("body");

        //上述获取到的Elements对象相当于一个集合，可以遍历获取子元素标签
        Elements div  = body.get(0).getElementsByTag("div");
        Element firstDiv = div.get(0);

        //利用元素标签对象获取该元素标签的属性，如class、id、style等
        String classType = firstDiv.attr("class");
        String id = firstDiv.attr("data-uuid");

        //根据HTML文本中元素标签的属性值进行解析获取对应数据（这里以class属性为例）
        Elements h2  = firstDiv.getElementsByClass("homeimg-title");

        //利用元素标签对象更改该元素标签的属性
        firstDiv.attr("class", "测试class");

        //利用元素标签对象更改该元素标签的html文本内容
        firstDiv.html("html文本内容");

        //返回的内容去除body和html元素标签
        return document.body().html();
    }

    public static void main(String[] args) {
        String html = "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <div data-v-53b74298=\"\" data-uuid=\"7e77c7b6ff9e488fb8870eedb55374d5\" class=\"homeimg-uuid\"> \n" +
                "   <img data-v-53b74298=\"\" src=\"\" class=\"homeimg-img\" /> \n" +
                "   <h2 data-v-53b74298=\"\" class=\"homeimg-title text-2line\" style=\"color:自定义红色\"> 主标题 </h2> \n" +
                "   <div data-v-53b74298=\"\" class=\"sub-title text-1line\" style=\"color: rgb(255, 255, 255);\">\n" +
                "     副标题 \n" +
                "   </div> \n" +
                "   <div data-v-53b74298=\"\" class=\"digest text-1line\" style=\"color: rgb(255, 255, 255);\">\n" +
                "     我是新的测试xxxxxxxxxxxxxxxxxxxxxxxxxxxx \n" +
                "   </div> \n" +
                "  </div> \n" +
                " </body>\n" +
                "</html>";
        System.out.println(analysisHTML(html));
    }
}
