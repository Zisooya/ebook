package com.jg.ebook.controller;

import com.jg.ebook.service.MainService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MainService mainService;

    @Value("${ebook.value.img.dir}")
    private String IMG_DIR;

    @RequestMapping(value = {"/"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String mainPage(Model model){
        model.addAttribute("imagePath", IMG_DIR);
        return "main";
    }

    @PostMapping("/login")
    @ResponseBody
    public boolean epubPage(@RequestParam("password") String password){
        boolean isMatch;

        isMatch = password.equals("Dog2024*");

        return isMatch;
    }

    @GetMapping("/ebook_p")
    public String phoneEbookPage(HttpServletRequest req, Model model){
        model.addAttribute("ebookImageInfo", mainService.getEbookImageInfo(req));
        return "ebook/ebook_p";
    }

    @RequestMapping(value = {"/ebook"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String ebookPage(HttpServletRequest req, Model model, @RequestParam(value = "pno", required = false) String pno){
        model.addAttribute("ebookImageInfo", mainService.getEbookImageInfo(req));
        model.addAttribute("pno", pno);
        return "ebook/ebook";
    }


    @GetMapping("/pdf")
    @ResponseBody
    public String[] getPdf(@RequestParam("fileName") String fileName){
        return mainService.getPdf(fileName);
    }
}
