package me.shaw.mall.service.impl;

import me.shaw.mall.model.Order;
import me.shaw.mall.model.UserAdmin;
import me.shaw.mall.model.UserOrderRelation;
import me.shaw.mall.repository.OrderRepository;
import me.shaw.mall.repository.UserAdminRepository;
import me.shaw.mall.repository.UserOrderRelationRepository;
import me.shaw.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserOrderRelationRepository userOrderRelationRepository;

    @Autowired
    private UserAdminRepository userAdminRepository;

    @Override
    public Order createOrder(int transaction, String status) {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principle instanceof UserDetails && ((UserDetails) principle).getUsername() != null){
            Order newOrder = new Order();
            newOrder.setStatus(status);
            newOrder.setTransactionValue(transaction);
            newOrder.setExpiredTime(new Date(System.currentTimeMillis() + 1000*60));
            orderRepository.saveAndFlush(newOrder);
            UserOrderRelation newRelation = new UserOrderRelation();
            newRelation.setStatus("Processing");
            newRelation.setOrderId(newOrder.getId());
            String username = ((UserDetails) principle).getUsername();
            UserAdmin user = userAdminRepository.findByUsername(username).get(0);
            newRelation.setUserId(user.getId());
            userOrderRelationRepository.save(newRelation);
            return newOrder;
        }

        return null;
    }

    @Override
    public void cancelOrder(Long id) {
        orderRepository.deleteById(id);
        userOrderRelationRepository.deleteByOrderId(id);
    }
}
