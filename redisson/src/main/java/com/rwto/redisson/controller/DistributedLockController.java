package com.rwto.redisson.controller;

import com.rwto.redisson.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author renmw
 * @create 2024/3/27 15:02
 **/
@Slf4j
@RestController
@RequestMapping("/distributedLock")
public class DistributedLockController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/reduceStocks")
    public String reduceStocks(String stock){
        //return stockService.reduceStocks(stock);
        return stockService.reduceStocksBySNX(stock);
    }

    @RequestMapping("/incrStocks")
    public String incrStocks(String stock,Integer addNum){
        return stockService.incrStocks(stock,addNum);
    }
}
