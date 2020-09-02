package com.lishuai.spring.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lishuai.spring.mapper.TestMapper;
import com.lishuai.spring.pojo.TestPojo;
import com.lishuai.spring.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestServiceImpl extends ServiceImpl<TestMapper, TestPojo> implements TestService {
}
