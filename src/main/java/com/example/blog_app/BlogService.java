package com.example.blog_app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    public List<Blog> findSearch(String search) {
        return blogRepository.findSearch(search);
    }

    public void add(BlogForm form) {
        if (form.getTitle() == null || form.getTitle().isBlank()) {
            throw new IllegalArgumentException("タイトルは必須入力です。");
        } else {
            blogRepository.save(new BlogForm(form.getTitle(), form.getMainText()));
        }
    }

    public List<BlogAll> changeSubText(List<Blog> blogs) {
        List<BlogAll> blogAlls = new ArrayList<>();
        for (Blog blog : blogs) {
            blogAlls.add(new BlogAll(blog.getId(), blog.getTitle(), makeSubText(blog.getMainText())));
        }
        return blogAlls;
    }

    public String makeSubText(String mainText) {
        String subText = "";
        for (int i = 0; i < mainText.length(); i++) {
            subText += mainText.charAt(i);
            if (i >= mainText.length() || mainText.charAt(i) == '。' || i > 90) {
                return subText;
            }
        }
        return subText;
    }

    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }
}
