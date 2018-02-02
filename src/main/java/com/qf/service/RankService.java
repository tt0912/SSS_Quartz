package com.qf.service;

import com.qf.domain.Rank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RankService {

    //1.增
    public boolean save(Rank rank);

    //2.改
    public boolean update(Rank rank);

    //3.根据组名修改分数
    public boolean update(Integer score,Integer groupNo);

    //4.删
    public boolean delete(Integer id);

    //5.分页显示各组数据
    public Page<Rank> getByPage(int pageNum,int pageSize);

    //6.根据id返回组
    public Rank getById(Integer id);

    //7.查询大于指定id的数据放入缓存数据库
    public List<Rank> selectById(Integer id);
}
