package com.example.blog_app;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        List<Blog> blogs = blogService.findAll();
        model.addAttribute("blogs", blogs);
        return "blog";
    }

    @PostMapping("/search")
    public String searchblog() {
        return "redirect:/blog";
    }

    @PostMapping("/postblog")
    public String postblog() {
        return "redirect:/blog";
    }
    

    @GetMapping("/detail")
    public String detail() {
        return "blog/detail";
    }

    @GetMapping("/post")
    public String post() {
        return "blog/post";
    }
}
