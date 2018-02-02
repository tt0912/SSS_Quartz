package com.qf.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 *  @author 田宇
 *  @date 2018/2/1 0001 14:09
 *  @description FreeMarker工具类
 */
public class FtlUtils {

    private static Configuration configuration;
    static {
        configuration = new Configuration(Configuration.getVersion());
    }
    //生成指定模板的指定文件
    public static String createStaticFile(String ftlDir, String ftlName, String fileName, Map<String,Object> modelMap){
        try {
            //1.指定模板文件夹
            configuration.setDirectoryForTemplateLoading(new File(ftlDir));
            //2.设置目标编码格式
            configuration.setDefaultEncoding("UTF-8");
            //3.生成模板
            Template template = configuration.getTemplate(ftlName);
            //4.生成静态文件
            template.process(modelMap,new FileWriter(fileName));
            return new File(fileName).getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
