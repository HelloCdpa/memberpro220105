package com.icia.member.controller;

import com.icia.member.dto.MemberSaveDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
        @GetMapping("/")
        public String index(){
        return "index";
        }



}
