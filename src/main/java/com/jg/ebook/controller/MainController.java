package com.jg.ebook.controller;

import com.jg.ebook.service.MainService;
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
        return "ebook/ebook_copy";
    }

    @GetMapping("/pdf")
    @ResponseBody
    public String getPdf(){
        return mainService.getPdf();
    }
}
