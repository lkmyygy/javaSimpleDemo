package com.example.web;

import com.example.dto.UserDto;
//import com.example.entity.Page;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import com.example.entity.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.List;
import java.util.HashMap;

/**
 * @Author:abc
 * @Description:
 * @params:$params$
 * @return: $returns$
 * @Date: $date$ $time$
 */

@Controller
public class IndexController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index(){
        return "redirect:/list";
    }


    /*
    public String list(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "2") int pageSize) {
        System.out.println("============================");
    * */
    @RequestMapping("/list")
    public String hello123(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "3")  int pageSize){
        List<User> all = userService.findAll();


        System.out.println("============================");
        Page<User> users = userService.getUserList(pageNum, pageSize);

        System.out.println("总页数" + users.getTotalPages());
        System.out.println("当前页是：" + pageNum);
        Iterator<User> u = users.iterator();
        while (u.hasNext()){
            System.out.println("next=="+u.next().toString());
        }
        model.addAttribute("all",users);
        return "index";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "add";
    }

    @RequestMapping("/add")
    public String add(UserDto userDto){
        userService.add(userDto);
        return "redirect:/list";
    }

    @RequestMapping("/del/{userId}")
    public String del(@PathVariable String userId){
        userService.del(userId);
        return "redirect:/list";
    }

    @RequestMapping("/edit/{userId}")
    public String edit(@PathVariable String userId, Model model){
        UserDto sel = userService.sel(userId);
        model.addAttribute("user",sel);
        return "edit";
    }

    @RequestMapping("/seach")
    public String seach(){
        return "seach";
    }

    @RequestMapping("/seachAll")
    public String seachAll(UserDto userDto,Model model){
        String userName = userDto.getUserName();
        String phone = userDto.getPhone();
        String realName = userDto.getRealName();
        List<User> user = userService.seach(userName,phone, realName);
        model.addAttribute("seachAll",user);
        return "seach";
    }
}
