package com.meng.spring.elasticsearch.controller;

import com.meng.spring.elasticsearch.dao.Student;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZuoHao
 * @date 2021/4/30
 */
@RestController
@RequestMapping("es")
public class ESController {
    @Resource
    private ElasticsearchRestTemplate restTemplate;
    @RequestMapping("save")
    public String save(){
        Student student = Student.builder()
                .id(111111L)
                .age(2)
                .sex("男")
                .nickName("ben")
                .name("ben")
                .build();
        restTemplate.save(student);
        return "SUCCESS";
    }
    @RequestMapping("/delete")
    public String delete(){
        IndexCoordinates student = IndexCoordinates.of("student");
        restTemplate.delete("_jKBIXkBVeyowKKWzRn6",student);
        return "SUCCESS";
    }
    @RequestMapping("/query")
    public String query(){
        return restTemplate.get("3213213", Student.class).toString();
    }

    /**
     * 查看索引
     */
    @RequestMapping("index")
    public Object index(){
        return restTemplate.indexOps(IndexCoordinates.of("student"));
    }


}
