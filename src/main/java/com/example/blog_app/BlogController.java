package com.example.blog_app;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        List<Blog> blogs = blogService.findAll();
        List<BlogAll> blogsall = blogService.changeSubText(blogs);
        model.addAttribute("blog", blogsall);
        return "blog";
    }

    @PostMapping("/search")
    public String searchblog(@RequestParam String search, Model model) {
        List<Blog> blogs = blogService.findSearch(search);
        List<BlogAll> blogsearch = blogService.changeSubText(blogs);
        model.addAttribute("blog", blogsearch);
        return "blog";
    }

    @PostMapping("/postblog")
    public String postblog(@ModelAttribute BlogForm form, Model model) {
        blogService.add(form);
        List<Blog> blogs = blogService.findAll();
        model.addAttribute("blogs", blogs);
        return "redirect:/blog";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Blog> blogOpt = blogService.findById(id);
        if (blogOpt.isEmpty()) {
            return "redirect:/books";
        }
        model.addAttribute("blog", blogOpt.get());
        return "blog/detail";
    }

    @GetMapping("/post")
    public String post() {
        return "blog/post";
    }

    @PostMapping("/blog/{id}/delete")
    public String delete(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/blog";
    }
}
