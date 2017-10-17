package com.mavenMVC.web.jobs;

import com.mavenMVC.entity.Order;
import com.mavenMVC.service.IDoctorService;
import com.mavenMVC.service.IOrderService;
import com.mavenMVC.service.IPayService;
import com.mavenMVC.util.OrderCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizai on 15/7/9.
 */

@Component
public class OrderAutoCommentJob {

    protected final Logger logger = Logger.getLogger(String.valueOf(OrderAutoCommentJob.class));

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IDoctorService doctorService;

    @Autowired
    private IPayService payService;

    public OrderAutoCommentJob(){
        logger.info("OrderAutoCommentJob init.......");
    }

    @Scheduled(cron="0 0 0 * * ? ")
    public void run() {
        logger.info("OrderAutoCommentJob running");
        List<Integer> statues = new ArrayList<Integer>();
        statues.add(OrderCode.ORDER_NORMAL_FINISHED);
        List<Integer> payStatues = new ArrayList<Integer>();
        payStatues.add(OrderCode.ORDER_PAID);
        List<Order> orders = orderService.getOrders(statues,payStatues,0);
        for(Order order : orders){
            if(order.getOrderScore() == null || order.getOrderComment() == null){
                if(order.getOrderScore() == null){
                    order.setOrderScore(5);
                }
                if(order.getOrderComment() == null){
                    order.setOrderComment("满意");
                }
                payService.refundOrder(order);
            }
        }
        logger.info("OrderAutoCommentJob finished");
    }
}
