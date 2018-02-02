package com.qf.service;

/**
 *  @author 田宇
 *  @date 2018/1/30 0030 16:47
 *  @description 定时将数据备份在Redis
 */
public interface QuartzService {

    //1.定时备份数据至缓存库
    public void barkRank();
}
