package com.qf.domain;

import javax.persistence.*;

@Entity
@Table(name = "t_rank")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer score;//分数
    @Column(length = 20,unique = true)
    private String projectName;//项目名
    private Integer groupNo;//租号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(Integer groupNo) {
        this.groupNo = groupNo;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "id=" + id +
                ", score=" + score +
                ", projectName='" + projectName + '\'' +
                ", groupNo=" + groupNo +
                '}';
    }
}
