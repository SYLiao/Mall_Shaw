package me.shaw.mall.component;

import me.shaw.mall.model.Order;
import me.shaw.mall.repository.OrderRepository;
import me.shaw.mall.repository.UserOrderRelationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class OrdeTask {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserOrderRelationRepository userOrderRelationRepository;

    private Logger LOGGER = LoggerFactory.getLogger(OrdeTask.class);

    @Scheduled(cron = "0 0/10 * ? * ?")
    public void cancelTimeOutOrder(){
        List<Order> orders = orderRepository.findAll();
        for(Order order : orders){
            if(new Date().after(order.getExpiredTime())){
                orderRepository.delete(order);
                userOrderRelationRepository.deleteByOrderId(order.getId());
            }
        }
    }
}
