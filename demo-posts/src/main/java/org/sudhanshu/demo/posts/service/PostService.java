package org.sudhanshu.demo.posts.service;

import org.sudhanshu.demo.posts.dto.PostDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author Sudhanshu Sharma
 *
 */
public interface PostService {

	/**
	 * Returns the Post with the specified Id.
	 * 
	 * @param postId	ID of the post to be retrieve
	 * @return					The required post if found
	 */
	Optional<PostDTO> findById(String postId);

	/**
	 * Returns the post with the specified slug
	 * @param slug	slug
	 * @return		requested post if found
	 */
	Optional<PostDTO> findBySlug(String slug);

	/**
	 * Returns all the post in the database
	 * 
	 * @return					All posts in the database
	 */
	List<PostDTO> findAll();

	/**
	 * Saves the specified post into the database
	 * 
	 * @param post		The post to be save
	 * @return					The saved post
	 */
	Optional<PostDTO> save(PostDTO post);

	/**
	 * Update the specified post, identified by its ID.
	 * 
	 * @param post		The post to update
	 * @return					True if the update succeeded, else false
	 */
	boolean update(PostDTO post);

	/**
	 * Deletes the post with the specified ID.
	 * 
	 * @param postId	The post to be deleted
	 * @return					True if the deletion is succeeded, else false
	 */
	boolean delete(String postId);

}
