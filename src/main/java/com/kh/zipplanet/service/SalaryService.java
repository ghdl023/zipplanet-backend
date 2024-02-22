package com.kh.zipplanet.service;

import com.kh.zipplanet.mapper.SalaryMapper;
import com.kh.zipplanet.model.SalaryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryService {
    @Autowired
    public SalaryMapper mapper;

    public List<SalaryModel> getSalary(){
        return mapper.getSalary();
    }
}
