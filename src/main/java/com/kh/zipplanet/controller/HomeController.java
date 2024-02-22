package com.kh.zipplanet.controller;

import com.kh.zipplanet.model.SalaryModel;
import com.kh.zipplanet.service.SalaryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    SalaryService salaryService;
    @RequestMapping(value = "/home", method= RequestMethod.GET)
    @ResponseBody
    public List<SalaryModel> goHome(HttpServletRequest request){
        System.out.println("enter");
        List<SalaryModel> salaryList = salaryService.getSalary();
		return salaryList;
    }
}
