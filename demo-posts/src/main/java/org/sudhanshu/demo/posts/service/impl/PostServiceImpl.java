package org.sudhanshu.demo.posts.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sudhanshu.demo.posts.dto.PostDTO;
import org.sudhanshu.demo.posts.entity.Post;
import org.sudhanshu.demo.posts.repository.PostRepository;
import org.sudhanshu.demo.posts.service.PostService;
import org.sudhanshu.demo.posts.utils.ObjectMapperUtils;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Sudhanshu Sharma
 *
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    PostRepository postRepository;

    @Override
    public Optional<PostDTO> findById(String id) {
        Optional<PostDTO> dto = Optional.empty();
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Exception in findById " + id + " with Exception " + ex.getMessage());
            return dto;
        }
        Optional<Post> post = postRepository.findById(uuid);

        if (post.isPresent()) {
            return ObjectMapperUtils.map(post.get(), PostDTO.class);
        }

        return dto;
    }

    /**
     * Returns the post with the specified slug
     *
     * @param slug slug
     * @return requested post if found
     */
    @Override
    public Optional<PostDTO> findBySlug(String slug) {
        Optional<PostDTO> dto = Optional.empty();
        Optional<Post> post = postRepository.findBySlug(slug);
        if (post.isPresent()) {
            return ObjectMapperUtils.map(post.get(), PostDTO.class);
        }
        return dto;
    }

    @Override
    public List<PostDTO> findAll() {
        List<Post> Posts = postRepository.findAll();
        return ObjectMapperUtils.mapAll(Posts, PostDTO.class);
    }

    @Override
    public Optional<PostDTO> save(PostDTO dto) {
        dto.setId(null);
        Optional<Post> existingPost = postRepository.findBySlug(createSlug(dto.getHeading()));
        Optional<Post> post = ObjectMapperUtils.map(dto, Post.class);
        Optional<PostDTO> postDTO = Optional.empty();
        if (post.isPresent() && !existingPost.isPresent()) {
            Post entity = post.get();
            entity.setActive(true);
            entity.setCreatedBy(1L);
            entity.setSlug(createSlug(dto.getHeading()));
            entity = postRepository.saveAndFlush(entity);
            postDTO = ObjectMapperUtils.map(entity, PostDTO.class);
        }
        return postDTO;
    }

    private String createSlug(String heading){
        heading = heading.replace(" ", "-");
        return heading.toLowerCase(Locale.ROOT);
    }

    @Override
    public boolean update(PostDTO dto) {
        try{
            Post post = postRepository.getOne(UUID.fromString(dto.getId()));
            if(post!=null){
                LOGGER.info("Post ID {} exists", dto.getId());
                post.setHeading(dto.getHeading());
                post.setSubHeading(dto.getSubHeading());
                post.setBackgroundImage(dto.getBackgroundImage());
                post.setMeta(dto.getMeta());
                post.setBody(dto.getBody());
                postRepository.save(post);
                return true;
            }
        }catch (RuntimeException ex){
            LOGGER.error(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public boolean delete(String PostId) {
        // TODO Auto-generated method stub
        return false;
    }

}