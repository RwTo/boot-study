package com.rwto.spring.controller;

import com.alibaba.fastjson.JSONObject;
import com.rwto.spring.pojo.AuthVerify;
import com.rwto.spring.pojo.ContentResp;
import com.rwto.spring.pojo.PageResp;
import com.rwto.spring.pojo.Result;
import com.rwto.spring.pojo.req.PageReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author renmw
 * @create 2024/9/6 11:16
 **/
@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/filter")
    public String filter(){
        return "hello filter";
    }

    @RequestMapping("/interceptor")
    public String interceptor(){
        return "hello interceptor";
    }

    @RequestMapping("/auth")
    public Result<AuthVerify> auth(@RequestParam("sig") String sig,
                                   @RequestParam("sig_method") String sig_method,
                                   @RequestParam("FORMAT") String FORMAT,
                                   @RequestParam("access_key") String access_key,
                                   @RequestParam("cmd") String cmd,
                                   @RequestParam("tokenId") String tokenId,
                                   @RequestParam("timestamp") String timestamp){

        log.info("sig:{},sig_method:{},FORMAT:{},access_key:{},cmd:{},tokenId:{},timestamp:{}",sig,sig_method,FORMAT,access_key,cmd,tokenId,timestamp);


        AuthVerify authVerify = new AuthVerify(tokenId, tokenId, access_key, "true");
        return Result.success(authVerify);
    }

    @RequestMapping("/page")
    public Result<PageResp> page(@RequestBody PageReq req){

        log.info("PageReq: {}",req);

        PageResp pageResp = new PageResp();
        if(req.getStartIndex() > 5){
            return Result.success(pageResp);
        }

        List<ContentResp> list = new ArrayList<>();
        for (int i = 0; i < req.getPageSize(); i++) {
            ContentResp contentResp = new ContentResp();
            contentResp.setPageSize(req.getPageSize());
            contentResp.setSource(req.getSource());
            contentResp.setStartIndex(req.getStartIndex());
            contentResp.setDatetime("Sun Dec 01 00:00:00 CST 2024");
            list.add(contentResp);
        }
        pageResp.setResults(list);

        return Result.success(pageResp);
    }

    @RequestMapping("/cookie")
    public Result<Void> cookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info("cookieName:{},value:{}",cookie.getName(),cookie.getValue());
            }
        }
        return Result.success();
    }
}
