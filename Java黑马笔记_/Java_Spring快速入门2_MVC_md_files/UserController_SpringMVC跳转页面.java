package com.example.spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller //放至容器当中,还需要配置文件扫一下该注解
@RequestMapping("/user")
public class UserController {
    
    @RequestMapping(value = "/quick",method = RequestMethod.GET,params = {"username"}) //请求映射
    public String save(){
        System.out.println("Controller save running..");
        return "success.jsp";
    }

    @RequestMapping(value = "/quick2") //请求映射
    public ModelAndView save2(){
        /**
         * Model:模型，用于封装数据
         * View:视图，用于展示数据
         */
        ModelAndView modelAndView = new ModelAndView();
        //设置模型数据
        modelAndView.addObject("username","itcaset");//相当于放到域当中
        //设置试图
        modelAndView.setViewName("success");//相当于jsp目录下的success.jsp（xml配置视频解析器）
        return modelAndView;
    }

    @RequestMapping(value = "/quick3") //请求映射
    public ModelAndView save3(ModelAndView modelAndView){//SpringMVC自动注入ModelAndView
        modelAndView.addObject("username","itcaset");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping(value = "/quick4") //请求映射
    public ModelAndView save4(ModelAndView modelAndView){
        modelAndView.addObject("username","itcaset");
        modelAndView.setViewName("success");
        return modelAndView;
    }


    @RequestMapping(value = "/quick5")
    public String save5(Model model){//Model和View拆开使用,Model为SpringMVC封装的对象
        model.addAttribute("username","boxuegu");
        return "success";//相当于/jsp/success.jsp，在xml配置视图解析器
    }

    @RequestMapping(value = "/quick6")
    public String save6(HttpServletRequest request){ //HttpServletRequest为JavaWeb自带，不常用，用框架就用框架带的（方便解耦）
        request.setAttribute("username","ladingyu");
        return "success";//相当于/jsp/success.jsp，在xml配置视图解析器
    }
}

