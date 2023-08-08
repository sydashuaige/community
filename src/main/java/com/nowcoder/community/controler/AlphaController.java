package com.nowcoder.community.controler;

import org.springframework.ui.Model;
import com.nowcoder.community.service.AlphaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping(path = "/alpha")
public class AlphaController {
    //通过controller调用services来访问dao
    @Autowired
    private AlphaService alphaService;
    @RequestMapping("/hello")
    @ResponseBody
    public String say(){
        System.out.println("启动成功了");
        return "hello world!";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request,
                     HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String  name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+":"+value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        //设置返回内容（返回网页内容的文本）
        response.setContentType("text/html;charset=utf-8");
        //通过reponse中封装的输出流，来进行输出内容
        //java7在编译以后会自动添加finally方法
        try(PrintWriter writer = response.getWriter()) {
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //get请求两种传参方式

    //1./students?current=1&limit=20 用?ping参数
    //强制get方法才能请求到
    @RequestMapping(path = "/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current",required = false,defaultValue = "1")int current,
            @RequestParam(name = "limit",required = false,defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    //2./students/123 路径变量参数
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable ("id") int id){
        System.out.println(id);
        return "a student";
    }

    //post请求 向浏览器提交数据
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String addStudent(String name,String age){
        System.out.println(name+":"+age);

        return "success!";
    }

    //向浏览器返回响应数据，响应动态html
    @RequestMapping(path = "teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.addObject("name","张三");
        modelAndView.addObject("age","25");
        //设置模板的路径和名字
        modelAndView.setViewName("/demo/view");
        return modelAndView;
    }

    @RequestMapping(path = "/school",method = RequestMethod.GET)
    //String 指返回的类型是view的路径
    public String getschool(Model model){
        model.addAttribute("name","北大");
        model.addAttribute("age","80");
        return "/demo/view";
    }
    //响应json数据（异步请求）
    //将java对象转成json对象交由js对象解析
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age","30");
        emp.put("salary","8000.0");
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> empList= new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age","30");
        emp.put("salary","8000.0");
        empList.add(emp);

        emp = new HashMap<>();
        emp.put("name","王二");
        emp.put("age","27");
        emp.put("salary","18000.0");
        empList.add(emp);

        emp = new HashMap<>();
        emp.put("name","陈平");
        emp.put("age","23");
        emp.put("salary","13000.0");
        empList.add(emp);
        return empList;
    }
}
