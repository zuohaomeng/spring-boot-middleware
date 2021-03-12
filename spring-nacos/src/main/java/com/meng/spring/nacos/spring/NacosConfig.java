package com.meng.spring.nacos.spring;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZuoHao
 * @date 2021/3/12
 */
@NacosPropertySource(dataId = "application",groupId = "DEFAULT_GROUP",autoRefreshed = true)
@Configuration
public class NacosConfig {

    @NacosValue(value = "${hello.name:ban}",autoRefreshed = true)
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
