package com.meng.spirng.sentinel.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.meng.spirng.sentinel.service.SentinelService;
import org.springframework.stereotype.Service;

/**
 * @author ZuoHao
 * @date 2021/3/18
 */

@Service
public class SentinelServiceImpl implements SentinelService {
    @Override
    public void get() {
        System.out.println("sentinel");
    }
}
