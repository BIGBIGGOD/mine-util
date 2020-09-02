package com.mine.mybatis;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/8/28 0028 16:20
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class CodeGeneratorCompany {

    public static void main(String[] args) {
        //需要生成实体类的表
        StringBuilder tableList = new StringBuilder();
        tableList.append("t_customer_channel,");
        tableList.append("t_customer_system_manager,");
        //模块名
        String module = "\\huaer-platform-customer-data-domain";
        //包名
        String parentPackage = "com.huaer.customer.domain";
        //用户的当前工作目录,文件创建所属模块路径
        String modulePath = System.getProperty("user.dir") + module;
        //公共表前缀
        String tablePrefix = "t_customer_";
        //开发人员
        String author = "jqd";
        //实体类包名
        String entity = "entity";
        //实体类名后缀
        String entityNameSuffix = "Do";

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://120.77.225.202:3306/huaer-platform-customer-data?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2b8&useSSL=false");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("vcxzREWQ");
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert());
        mpg.setDataSource(dsc);

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //是否覆盖已有文件,默认false
        gc.setFileOverride(false);
        //生成文件的输出目录
        gc.setOutputDir(modulePath + "/src/main/java");
        //开发人员
        gc.setAuthor(author);
        //是否打开输出目录
        gc.setOpen(false);
        //配置实体类后缀
        gc.setEntityName("%s" + entityNameSuffix);
        gc.setMapperName("%s" + entityNameSuffix + "Mapper");
        gc.setServiceName("%s" + "Service");
        //时间类型对应策略
        gc.setDateType(DateType.ONLY_DATE);
        //是否开启二级缓存
        gc.setEnableCache(false);
        mpg.setGlobalConfig(gc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //父包名,如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setParent(parentPackage);
        //entity包名
        pc.setEntity(entity);
        //service包名
//        pc.setService("service");
        //serviceImpl包名
//        pc.setServiceImpl("service.impl");
        //mapper包名
//        pc.setServiceImpl("mapper");
        //controller包名
//        pc.setController("controller");
        mpg.setPackageInfo(pc);

        //自定义文件输出路径
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        String templatePath = "/templates/mapper.xml.vm";
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return modulePath + "/src/main/resources/sqlmap/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        injectionConfig.setFileOutConfigList(focList);
        mpg.setCfg(injectionConfig);

        // 配置模板,在对应controller、service、xml等传入空即可不生成其文件
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml("");
        templateConfig.setController("");
        mpg.setTemplate(templateConfig);

        // 数据库表配置，通过该配置，可指定需要生成哪些表或者排除哪些表
        StrategyConfig strategy = new StrategyConfig();
        //数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //表前缀
        strategy.setTablePrefix(tablePrefix);
        //【实体】是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        //驼峰转连字符
//        strategy.setControllerMappingHyphenStyle(true);        //是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        //需要包含的表名，这里用逗号做隔离
        strategy.setInclude(tableList.toString().split(StringPool.COMMA));
        //是否为链式模型（默认 false）
        strategy.setChainModel(true);
        mpg.setStrategy(strategy);

        mpg.execute();
    }

}
