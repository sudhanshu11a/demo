package org.sudhanshu.demo.posts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sudhanshu.demo.posts.dto.PostDTO;
import org.sudhanshu.demo.posts.service.PostService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/post")
@CrossOrigin
public class PostController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PostController.class);

    @Autowired
    PostService postService;

    @GetMapping("/read/{slug}")
    public ResponseEntity<?> getPostData(@PathVariable String slug) {
        return postService.findBySlug(slug).map(post -> {
            return ResponseEntity.ok(post);
        }).orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/read/all")
    public ResponseEntity<?> getAllPostName() {
        LOGGER.info("Initiating request for fetching all Posts");
        List<PostDTO> posts = postService.findAll();
        try {
            return ResponseEntity.ok().location(new URI("/post")).body(posts);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO post) {
        LOGGER.info("Creating new post with name {} ", post.getHeading());

        // Create the new Post

        try {
            Optional<PostDTO> newPost = postService.save(post);
            if (newPost.isPresent()) {
                return ResponseEntity.created(new URI("/post/" + newPost.get().getSlug())).body(newPost.get());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    /**
     * Update the Post with the specified ID
     *
     * @param newPost Post details
     * @param id      Post ID
     * @return New Post details
     */
    @PutMapping("/save/{id}")
    public ResponseEntity<?> updatePost(@RequestBody PostDTO newPost, @PathVariable String id) {
        LOGGER.info("Post ID {} is updating.", id);
        newPost.setId(id);
        if (postService.update(newPost)) {
            //update the Post and return a OK response
            return ResponseEntity.ok().body(newPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete the Post with the specified ID.
     *
     * @param id The Id of the Post to delete
     * @return A responseEntity with one of the following status code:
     * 200 OK if the delete was successful
     * 404 Not Found if the the Post with the specified Id not found
     * 500 Internal Server Error if the error occurs during deletion
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable String id) {
        LOGGER.info("Deleting the post with ID : {} ", id);

        //Get the existing Post
        Optional<PostDTO> existingPost = postService.findById(id);

        return existingPost.map(org -> {
            if (postService.delete(org.getId())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }).orElse(ResponseEntity.notFound().build());
    }


}
