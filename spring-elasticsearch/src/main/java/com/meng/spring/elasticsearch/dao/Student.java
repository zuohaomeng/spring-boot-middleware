package com.meng.spring.elasticsearch.dao;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author ZuoHao
 * @date 2021/4/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document(indexName = "student")
public class Student implements Serializable {
    private static final long serialVersionUID = 3448400110312641320L;
    private Long id;
    private String name;
    private String nickName;
    private String sex;
    private Integer age;
}
