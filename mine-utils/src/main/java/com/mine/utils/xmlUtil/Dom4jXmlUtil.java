package com.mine.utils.xmlUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangqd on 2019/2/28.
 */
@Slf4j
public class Dom4jXmlUtil {

    /**
     * @Descibe 传递的对象转化成对应格式的xml字符串
     * DocumentHelper.createDocument() 创建初始对象
     * parentNode.addElement(node) 向上一级节点增加子节点node
     * node.setText(value) 为该对标签之间添加文本内容
     * node.addAttribute() 为该节点添加属性值
     * 注意反射获取属性值时的空指针问题
     * 注意当标签之间的文本为空时会自动闭合标签，如<node />,不为空时为<node>...</node>
     */
    public static String objToXml(Object obj) {
        Document document = DocumentHelper.createDocument();
        try {
            // 创建根节点元素
            Element root = document.addElement("root");
            Element params = root.addElement("params");
            Field[] field = obj.getClass().getDeclaredFields(); // 反射获取实体类b的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) { // 遍历所有有属性
                String name = field[j].getName(); // 获取属属性的名字
                if (!name.equals("serialVersionUID")) {//去除串行化序列属性
                    name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
                    Method m = obj.getClass().getMethod("get" + name);
                    // System.out.println("属性get方法返回值类型:" + m.getReturnType());
                    name = name.substring(0, 1).toLowerCase() + name.substring(1);
                    Object object = m.invoke(obj);// 获取属性值
                    String value = null;
                    String propertievalue = null;
                    if (object != null) {
                        value = object.toString();
                        propertievalue = "name='" + name + "' " + "value='" + value + "'";
                    } else {
                        propertievalue = "name='" + name + "' " + "value=' '";
                    }

                    Element propertie = params.addElement("param ");
                    propertie.setText(propertievalue);
                }
            }
            return document.asXML();
        } catch (Exception e) {
            log.info("Dom4jXmlUtil.objToXml is wrong e={}", e.toString());
            return null;
        }
    }

    /**
     * 将xml格式的字符串转换成Map对象
     * 根据xml格式提取需要的数据
     * 注意在实际业务中某些参数在xml中显示为null字符串，又或者直接为“”
     */
    public static Map<String, Object> xmlToMap(String xmlStr) throws DocumentException {
        if (StringUtils.isEmpty(xmlStr)) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        //将xml格式的字符串转换成Document对象
        Document doc = DocumentHelper.parseText(xmlStr);
        //获取根节点
        Element root = doc.getRootElement();
        //获取根节点下的所有元素
        List<Element> childrenNode = root.elements();
        //循环所有子元素
        if (childrenNode != null && childrenNode.size() > 0) {
            for (int i = 0; i < childrenNode.size(); i++) {
                Element child = childrenNode.get(i);
                if (child.getName().equals("affectedDetail")) {
                    String name = child.getName();
                    List<Element> affectedList = child.elements();
                    List<String> list = new ArrayList<>();
                    for (Element affected : affectedList) {
                        list.add(affected.attributeValue("count"));
                    }
                    map.put(name, list);
                } else {
                    String name = child.getName();
                    String count = child.attributeValue("count");
                    String errormessage = child.attributeValue("errormessage");
                    String content = (count == null) ? errormessage : count;
                    map.put(name, content);
                }
            }
        }
        return map;
    }

}
