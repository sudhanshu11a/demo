/**
 * 
 */
package org.sudhanshu.demo.posts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sudhanshu.demo.posts.entity.Post;

import java.util.Optional;
import java.util.UUID;


/**
 * 
 * 
 * @author Sudhanshu Sharma
 *
 */
@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    public Optional<Post> findBySlug(String slug);
}
