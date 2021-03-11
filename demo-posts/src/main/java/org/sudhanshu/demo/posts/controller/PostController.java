package org.sudhanshu.demo.posts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sudhanshu.demo.posts.dto.ResultsPost;

@RestController
public class PostController {
    private ResultsPost resultsPost = new ResultsPost("1", "slug", "", "Heading",
            "SubHeading", "Meta", "Body..." );

    @GetMapping("/allpost")
 public ResponseEntity<?> getAllPosts(){
     //ResultsPost resultsPost = new ResultsPost("1", "slug", "", "Heading",
     //        "SubHeading", "Meta", "Body..." );
     return ResponseEntity.ok(resultsPost);
 }

}
