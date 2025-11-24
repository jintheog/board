package com.example.board.controller;

import com.example.board.dto.PostDTO;
import com.example.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;

//    public PostController(PostRepository postRepository) { //롬복으로 자동생성
//        this.postRepository = postRepository;
//    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "posts/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        PostDTO post = postRepository.findById(id);
        model.addAttribute("post", post);
        return "posts/detail";
    }

    @GetMapping("/new")
    public String newPost(Model model) {
        model.addAttribute("post", new PostDTO());
        return "posts/form";
    }

    @PostMapping
    public String create(@ModelAttribute PostDTO postDTO) {
        postRepository.save(postDTO);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        PostDTO post = postRepository.findById(id);
        model.addAttribute("post", post);
        return "posts/form";
    }

    @PostMapping("/{id}")
    public String update(
        @PathVariable Long id,
        @ModelAttribute PostDTO postDTO
    ){

        postRepository.update(id, postDTO);

        return "redirect:/posts/" + id;
    }

    @PostMapping("/id/delete")
    public String delete(@PathVariable Long id){
        postRepository.delete(id);
        return "redirect:/posts";
    }

}
