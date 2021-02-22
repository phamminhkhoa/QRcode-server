package com.test.gigantec.service;

import com.test.gigantec.entity.Customer;
import com.test.gigantec.utils.AirTableUtil;
import com.test.gigantec.utils.ThreadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private AirTableUtil airTableUtil;


    @Override
    public Customer create(Customer customer) {
        sendDataToAirTable(customer);
        return customer;
    }

    private Map<String, Object> generateAirTablePost(Customer customer) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> values = new HashMap<>();
        values.put("Name", customer.getName());
        result.put("fields", values);
        return result;
    }

    private void sendDataToAirTable(Customer customer) {
        try {
            Runnable runnable = () -> {
                Map<String, Object> dataAirTable = null;
                dataAirTable = airTableUtil.createDataAirTable(generateAirTablePost(customer));
            };
            ThreadUtil.executor.execute(runnable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
