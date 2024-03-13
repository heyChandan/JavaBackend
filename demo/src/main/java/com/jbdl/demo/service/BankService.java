package com.jbdl.demo.service;
//import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankService {
    private Map<Integer,String> hm = Map.of(1,"SBI",2,"Bank Of Baroda",
                                            3,"Gramya Bank");

    public List<String> fetchBankName(){
            ArrayList<String> bankList=new ArrayList<>();
            for (Map.Entry<Integer,String> e : hm.entrySet()) {
                bankList.add((String)e.getValue());
            }
            return bankList;
    }

    public String getBankNameById(Integer id){
        if(hm.containsKey(id)) return hm.get(id);
        throw new RuntimeException("Id not exist");

    }



}
