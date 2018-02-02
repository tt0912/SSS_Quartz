package com.qf.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.domain.Rank;
import com.qf.service.RankService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;

/**
 *  @author 田宇
 *  @date 2018/1/30 0030 10:23
 *  @description 测试
 */
public class RankTest {

    @Test
    public void test01() throws JsonProcessingException {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        RankService rankService = context.getBean(RankService.class);
        /*Rank rank = new Rank();
        rank.setGroupNo(8);
        rank.setProjectName("搭伙儿");
        rank.setScore(100);
        System.out.println("新增:"+rankService.save(rank));*/

        Page<Rank> rankPage = rankService.getByPage(1,3);
        System.out.println("总页数:"+rankPage.getTotalPages());
        System.out.println("当前页:"+(rankPage.getNumber()+1));
        System.out.println("分页内容:"+rankPage.getContent());

        //返回json数据
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("json数据:"+objectMapper.writeValueAsString(rankPage.getContent()));

    }
}
