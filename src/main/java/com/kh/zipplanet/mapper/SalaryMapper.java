package com.kh.zipplanet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.kh.zipplanet.model.SalaryModel;

@Repository
@Mapper
public interface SalaryMapper {
    List<SalaryModel> getSalary();
}
