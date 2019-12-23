package com.lx;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ken
 * @date 2018-11-14 10:21
 * @since v1.0.0
 */
public class CodeGenerator {

    /**
     * Type blow 3 filed that what you want.
     * Generate code in <build> dir.
     */
    private static String moduleName = "user";
    private static String prefix = "sys_";
    private static String[] includeTables = new String[]{
            "sys_user","sys_role","sys_user_role","sys_menu","sys_role_menu"
    };
    /**
     * -----------------------------------------Don't Edit-----------------------------------------
     */
    private static String baseEntity = "com.lx.common.entity.BaseEntity";
    private static String[] baseFiled = new String[]{"id", "create_time"};

    public static void setDatasource(AutoGenerator mpg) {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {

        });
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/shiro_boot?serverTimezone=UTC");
        mpg.setDataSource(dsc);
    }


    public static void main(String[] args) throws IOException {

        AutoGenerator mpg = new AutoGenerator();
        String projectPath = buildGlobalConfig(mpg);
        setDatasource(mpg);
        buildStrategy(mpg);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent("com.lx");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>(1);
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/mappers/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);

        mpg.setCfg(cfg);

        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        mpg.setTemplate(tc);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 执行生成
        mpg.execute();

        // 打印注入设置【可无】
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }

    private static String buildGlobalConfig(AutoGenerator mpg) throws IOException {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = buildPath();

        gc.setOutputDir(projectPath);
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(false);

        gc.setAuthor("zhaowei");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%s-mapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        mpg.setGlobalConfig(gc);
        return projectPath;
    }

    private static String buildPath() {
        String projectPath = System.getProperty("user.dir");
        String sep = System.getProperty("file.separator");
        projectPath += sep + "lx-code-generator" + sep + "build";
        return projectPath;
    }

    private static void buildStrategy(AutoGenerator mpg) {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表前缀
        strategy.setTablePrefix(prefix);
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        strategy.setInclude(includeTables);
        // 自定义实体父类
        strategy.setSuperEntityClass(baseEntity);
        // 自定义实体，公共字段
        strategy.setSuperEntityColumns(baseFiled);
        strategy.setEntityLombokModel(true);
        // 自定义 mapper 父类
        strategy.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        // 自定义 service 父类
        strategy.setSuperServiceClass("com.lx.core.module.service.mybatis.BaseService");
        // 自定义 service 实现类父类
        strategy.setSuperServiceImplClass("com.lx.core.module.service.mybatis.impl.BaseServiceImpl");
        mpg.setStrategy(strategy);
    }


}
