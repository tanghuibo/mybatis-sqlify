package io.github.tanghuibo.mybatissqlify.controller;

import io.github.tanghuibo.mybatissqlify.bean.BizResult;
import io.github.tanghuibo.mybatissqlify.bean.SqlifyResponse;
import io.github.tanghuibo.mybatissqlify.service.SqlifyService;
import io.github.tanghuibo.mybatissqlify.bean.SqlifyRequest;
import io.github.tanghuibo.mybatissqlify.tools.BizResultBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tanghuibo
 * @date 2019/12/21下午7:20
 */
@RestController
@RequestMapping("/sqlify")
public class SqlifyController {

    @Resource
    SqlifyService sqlifyService;

    @PostMapping("/process")
    public BizResult<SqlifyResponse> process(@RequestBody SqlifyRequest sqlifyRequest) {
        try {
            SqlifyResponse response = sqlifyService.process(sqlifyRequest);
            return BizResultBuilder.buildSuccess(response);
        } catch (Exception e) {
            e.printStackTrace();
           return BizResultBuilder.buildFailed(e.getMessage());
        }

    }
}
