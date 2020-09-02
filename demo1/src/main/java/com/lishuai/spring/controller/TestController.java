package com.lishuai.spring.controller;

import com.lishuai.spring.pojo.TestPojo;
import com.lishuai.spring.service.TestService;
import com.lishuai.spring.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(value = "TestController")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ApiOperation(value = "TestHome", notes = "TestHome")
    public Result<?> home(TestPojo testPojo) {
        System.out.println("入参" + testPojo);
        testService.saveOrUpdate(testPojo);
        return Result.ok("插入成功");
    }

}
