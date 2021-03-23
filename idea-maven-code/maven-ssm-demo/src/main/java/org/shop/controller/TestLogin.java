package org.shop.controller;

import org.shop.domain.User;
import org.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TestLogin {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/login")
    public String login(String name, String password, String code, Model model, HttpSession session){
        String mycode = (String) session.getAttribute("mycode");
        if (!code.equals(mycode)) {
            model.addAttribute("msg","验证码错误了~~~");
            return "index";
        }else {
            List<User> list = userService.getUser(name, password);

            if (list!=null&&list.size()>0) {
                model.addAttribute("msg","ok");
                return "redirect:/product";
            }else {
                model.addAttribute("msg","nonono!");
                return "index";
            }
        }
    }
}
