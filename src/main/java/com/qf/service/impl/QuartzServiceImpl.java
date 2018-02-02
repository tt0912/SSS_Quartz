package com.qf.service.impl;

import com.alibaba.fastjson.JSON;
import com.qf.common.RedisUtils;
import com.qf.domain.Rank;
import com.qf.service.QuartzService;
import com.qf.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.qf.common.RedisUtils.getFirst;

/**
 *  @author 田宇
 *  @date 2018/1/30 0030 21:56
 *  @description 定时任务
 */
@Service
public class QuartzServiceImpl implements QuartzService{

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RankService rankService;

    private Integer id=0;
    @Override
    public void barkRank() {

        if(redisUtils.exists("ranks")){
            //通过Redis缓存中的数据，得到json格式下的第一条Rank，取出对应的最大id
            String json = redisUtils.getFirst("ranks");
            if(json.length()>0) {
                Rank rank = JSON.parseObject(json, Rank.class);
                Integer id = rank.getId();
            }
        }
        List<Rank> ranks = rankService.selectById(id);
        if(ranks.size()>0){//数据库中有数据我才推进Redis
            for (Rank rank : ranks) {
                RedisUtils.lpush("ranks",JSON.toJSONString(rank));
            }
        }

    }
}
