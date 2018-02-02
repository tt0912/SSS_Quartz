package com.qf.service.impl;

import com.qf.dao.RankDao;
import com.qf.domain.Rank;
import com.qf.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;
/**
 *  @author 田宇
 *  @date 2018/1/30 0030 21:57
 *  @description 操作MySQL数据库
 */
@Service
@Transactional
public class RankServiceImpl implements RankService {

    @Autowired
    private RankDao rankDao;

    //1.保存(系统)
    @Override
    public boolean save(Rank rank) {
        return rankDao.save(rank) != null;
    }

    //2.有id更新(系统)
    @Override
    public boolean update(Rank rank) {
        return rankDao.save(rank) != null;
    }

    //3.根据组名修改分数(自己)
    @Override
    public boolean update(Integer score, Integer groupNo) {
        return rankDao.update(score, groupNo) > 0;
    }

    //4.删除(系统)
    @Override
    public boolean delete(Integer id) {

        try {
            rankDao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //5.分页(系统)
    @Override
    public Page<Rank> getByPage(int pageNum, int pageSize) {

        Pageable pageable = new PageRequest(pageNum - 1, pageSize);
        return rankDao.findAll(pageable);
    }

    //6.查一个(系统)
    @Override
    public Rank getById(Integer id) {
        return rankDao.findOne(id);
    }

    //7.查大于指定id的数据
    @Override
    public List<Rank> selectById(Integer id) {

        Specification<Rank> specification = new Specification<Rank>() {
            @Override
            public Predicate toPredicate(Root<Rank> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Path idPath = root.get("id");
                return criteriaBuilder.gt(idPath.as(Integer.class),id);
            }
        };
        return rankDao.findAll(specification);
    }

}
