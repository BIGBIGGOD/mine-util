package com.mine.utils.springUtil;

import java.util.Arrays;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/8/19 14:55
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class SpringBeanDefinitionTest {

    /*public static void main(String[] args) {
        BeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
        DefaultResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("spring-test.xml");
        reader.loadBeanDefinitions(resource);
        System.out.println(Arrays.toString(registry.getBeanDefinitionNames()));
    }*/

    public static void main(String[] args) {
        BeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
        reader.loadBeanDefinitions("spring-test.xml");
        System.out.println(Arrays.toString(registry.getBeanDefinitionNames()));
//        System.exit(0);
    }
}
