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

        model.addAttribute("imageCount", mainService.getImageCount());
        return "ebook/ebook_copy2";
    }

    @GetMapping("/epub")
    public String epubPage(Model model){

        return "ebook/swipe";
    }

    @GetMapping("/pdf")
    @ResponseBody
    public String[] getPdf(@RequestParam("fileName") String fileName){
        return mainService.getPdf(fileName);
    }
}
