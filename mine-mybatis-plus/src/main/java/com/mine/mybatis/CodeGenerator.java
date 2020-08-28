package com.mine.mybatis;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/8/28 0028 16:20
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class CodeGenerator {

    public static void main(String[] args) {
        StringBuilder tableList = new StringBuilder();
        tableList.append("t_user,");
        String parentPackage = "com.mine.mybatis";
        String module = "\\mine-mybatis-plus";
        String tablePrefix = "t_";

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://106.52.179.22:8020/mine?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2b8&useSSL=false");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //是否覆盖已有文件,默认false
        gc.setFileOverride(false);
        //生成文件的输出目录
        gc.setOutputDir(System.getProperty("user.dir") + module + "/src/main/java");
        //开发人员
        gc.setAuthor("jqd");
        //是否打开输出目录
        gc.setOpen(false);
        //配置实体类后缀
        gc.setEntityName("%sDo");
        mpg.setGlobalConfig(gc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //父包名,如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setParent(parentPackage);
        //entity包名
        pc.setEntity("model");
        //service包名
//        pc.setService("service");
        //serviceImpl包名
//        pc.setServiceImpl("service.impl");
        //mapper包名
//        pc.setServiceImpl("mapper");
        //controller包名
//        pc.setController("controller");
        mpg.setPackageInfo(pc);

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
        strategy.setRestControllerStyle(false);
        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        //需要包含的表名，当enableSqlFilter为false时，允许正则表达式（与exclude二选一配置）
        strategy.setInclude(tableList.toString().split(","));
        mpg.setStrategy(strategy);

        mpg.execute();
    }

}
