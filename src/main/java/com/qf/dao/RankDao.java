package com.qf.dao;

import com.qf.domain.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RankDao extends JpaRepository<Rank,Integer>,JpaSpecificationExecutor<Rank>{

    //这是自己加的语句
    @Modifying
    @Query("update Rank R set R.score=?1 where R.groupNo=?2")
    public int update(Integer score,Integer groupNo);

    @Query(value = "select * from t_rank where groupNo=?",nativeQuery = true)
    public Rank getRankByGno(Integer groupNo);

}
