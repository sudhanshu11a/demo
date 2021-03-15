package org.sudhanshu.demo.posts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sudhanshu.demo.posts.dto.ResultsPost;

import java.util.Arrays;

@RestController
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:4200"})
public class PostController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PostController.class);

    private ResultsPost resultsPost = new ResultsPost("1", "slug", "", "Heading",
            "SubHeading", "Meta", "Body..." );

    @GetMapping()
    public ResponseEntity<?> getAllPosts(){
     //ResultsPost resultsPost = new ResultsPost("1", "slug", "", "Heading",
     //        "SubHeading", "Meta", "Body..." );
     return ResponseEntity.ok(Arrays.asList(resultsPost));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<?> getPostBySlug(@PathVariable String slug, @RequestParam(required = false) String findBy){
        //ResultsPost resultsPost = new ResultsPost("1", "slug", "", "Heading",
        //        "SubHeading", "Meta", "Body..." );
        return ResponseEntity.ok(resultsPost);
    }

    @PostMapping("/save-post")
    public ResponseEntity<ResultsPost> saveOrganization(@RequestBody ResultsPost postDto) {
        LOGGER.info("Creating new Post with name {} ", postDto.getHeading());

        // Create the new Organization
        return ResponseEntity.ok(resultsPost);

//        try {
//            //Optional<ResultsPost> resultsPost = organizationService.save(postDto);
//            if(resultsPost.isPresent()){
//                return ResponseEntity.created(new URI("/post/" + newOrganization.get().getOrgName())).body(newOrganization.get());
//            }else{
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            }
//        } catch (URISyntaxException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }


    }


}
