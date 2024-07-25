package com.jg.ebook.controller;

import com.jg.ebook.service.MainService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MainService mainService;

    @RequestMapping(value = {"/"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String mainPage(Model model){
        return "main";
    }

    @PostMapping("/login")
    @ResponseBody
    public boolean epubPage(@RequestParam("password") String password){
        System.out.println("==============================: "+password);
        boolean isMatch;

        isMatch = password.equals("1234");

        return isMatch;
    }

    @GetMapping("/ebook_p")
    public String phoneEbookPage(HttpServletRequest req, Model model){
        System.out.println("폰폰");
        model.addAttribute("ebookImageInfo", mainService.getEbookImageInfo(req));
        return "ebook/ebook_p";
    }

    @GetMapping("/ebook")
    public String ebookPage(HttpServletRequest req, Model model){
        System.out.println("pc태블");
        model.addAttribute("ebookImageInfo", mainService.getEbookImageInfo(req));
        return "ebook/ebook";
    }


    @GetMapping("/pdf")
    @ResponseBody
    public String[] getPdf(@RequestParam("fileName") String fileName){
        return mainService.getPdf(fileName);
    }
}
