package com.nebu.goldmine.web;

import com.nebu.goldmine.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {
    private int pageSize = 50;

    @Autowired
    private PostService postService;

    @GetMapping("/new/{page}")
    public ResponseEntity newPosts(@PathVariable int page, @RequestParam(value = "search", required = false) String search, HttpServletRequest request) {
        if(search==null) {
            return new ResponseEntity<>(postService.newPosts(page, pageSize, request.getRemoteAddr()), HttpStatus.OK);
        }
        return new ResponseEntity<>(postService.newSearch(page, pageSize, search, request.getRemoteAddr()), HttpStatus.OK);
    }

    @GetMapping("/best/{page}")
    public ResponseEntity bestPosts(@PathVariable int page, @RequestParam(value = "search", required = false) String search, HttpServletRequest request) {
        if(search==null) {
            return new ResponseEntity<>(postService.bestPosts(page, pageSize, request.getRemoteAddr()), HttpStatus.OK);
        }
        return new ResponseEntity<>(postService.bestSearch(page, pageSize, search, request.getRemoteAddr()), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity post(
            @RequestParam(value = "story") String story,
            @RequestParam(value = "protagonist") String protagonist,
            @RequestParam(value = "context") String context,
            @RequestParam(value = "data", required = false) MultipartFile data,
            HttpServletRequest request
            ) throws IOException {
        return new ResponseEntity<>(postService.post(story, protagonist, context, data, request.getRemoteAddr()), HttpStatus.OK);
    }

    @PostMapping("/vote/{id}")
    public ResponseEntity vote(@PathVariable Long id, HttpServletRequest request){
        return new ResponseEntity<>(postService.vote(id, request.getRemoteAddr()), HttpStatus.OK);
    }
}
