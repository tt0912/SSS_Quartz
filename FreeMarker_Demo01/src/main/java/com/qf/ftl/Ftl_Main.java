package com.qf.ftl;

import com.qf.domain.News;
import com.qf.utils.FtlUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Ftl_Main {

    public static void main(String[] args) throws Exception{

        /*test01();*/
        //testUtils();
        //createHtml();
        createList();
    }
    public static void test01() throws IOException, TemplateException {

        //1.加载配置文件
        Configuration configuration = new Configuration(Configuration.getVersion());
        //2.指定模板文件夹
        configuration.setDirectoryForTemplateLoading(new File("FreeMarker_Demo01/ftl"));
        //3.设置模板编码格式
        configuration.setDefaultEncoding("UTF-8");
        //4.获取指定模板对象
        Template template = configuration.getTemplate("test01.ftl");
        Map<String,String> map = new HashMap<>();
        map.put("h","大家好，我是科比");
        //取出，根据模板生成静态文件
        template.process(map,new FileWriter("FreeMarker_Demo01/tem/hello.txt"));
    }


    public static void testUtils(){

        String ftlDir="FreeMarker_Demo01/ftl";
        String ftlName="offer.ftl";
        String fileName="FreeMarker_Demo01/tem/offer.doc";
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("No", UUID.randomUUID().toString().replaceAll("-",""));
        modelMap.put("name","科比·布莱恩特");
        modelMap.put("time",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        System.out.println(FtlUtils.createStaticFile(ftlDir,ftlName,fileName,modelMap));
    }

    //通过模板生成静态html
    public static void createHtml(){
        String ftlDir="FreeMarker_Demo01/ftl";
        String ftlName="news.ftl";
        String fileName="FreeMarker_Demo01/tem/1.html";
        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("title", "凌晨4点的洛杉矶");
        modelMap.put("content","凌晨4点的洛杉矶就是科比·布莱恩特一个人的球场");
        modelMap.put("time",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        System.out.println(FtlUtils.createStaticFile(ftlDir,ftlName,fileName,modelMap));
    }

    private static void createList(){
        List<News> newsList = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            News news = new News();
            news.setTitle("工作日调整");
            news.setContent("工作日调为4天"+i);
            news.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("news",news);

            news.setUrlhtml(FtlUtils.createStaticFile(
                    "FreeMarker_Demo01/ftl","news.ftl","FreeMarker_Demo01/tem/"+i+".html",dataMap));
            newsList.add(news);
        }

        Map<String,Object> modelMap = new HashMap<>();
        modelMap.put("newsList",newsList);
        System.out.println(FtlUtils.createStaticFile("FreeMarker_Demo01/ftl","newslist.ftl","FreeMarker_Demo01/tem/list.html",modelMap));
    }
}
