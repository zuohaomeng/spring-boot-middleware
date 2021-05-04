package com.meng.spring.elasticsearch.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import javax.annotation.Resource;

/**
 * @author ZuoHao
 * @date 2021/4/30
 */
@Resource
public interface StudentRepository extends ElasticsearchRepository<Student, Long> {
    /**
     * 根据名称统计
     * @param name
     * @return
     */
    long countByName(String name);
}
