package com.example.board.repository;

import com.example.board.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {

    @PersistenceContext
    private EntityManager em;

    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    public Post findById(Long id) {
        return em.find(Post.class, id);
    }

    public List<Post> findAll() {
        // EM => 단일 엔티티 조작만 기본 제공
        String jpql = "SELECT p FROM Post p";
        return em.createQuery(jpql, Post.class).getResultList();
    }

    public Post update(Post post) {
        return em.merge(post);
    }

    public void delete(Post post) {
        em.remove(post);
    }

}











//package com.example.board.repository;
//
//import com.example.board.dto.PostDTO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class PostRepository {s
//
//    private final JdbcTemplate jdbcTemplate;
//
//    // ↓ 아래 생성자는 @RequiredArgsConstructor로 자동생성
////    public PostRepository(JdbcTemplate jdbcTemplate) {
////        this.jdbcTemplate = jdbcTemplate;
////    }
//
//    private final RowMapper<PostDTO> rowMapper = (rs, rowNum) -> {
//        return new PostDTO(
//                rs.getLong("id"),
//                rs.getString("title"),
//                rs.getString("content"),
//                rs.getTimestamp("created_at").toLocalDateTime()
//        );
//    };
//
//    //전체 조회
//    public List<PostDTO> findAll() {
//        String sql= "SELECT * FROM post";
//        return jdbcTemplate.query(sql, rowMapper);
//    }
//
//    //상세 조회
//    public PostDTO findById(Long id) {
//        String sql= "SELECT * FROM post WHERE id = ?";
//
//        // queryForObject => 단일 행 조회
//        PostDTO post = jdbcTemplate.queryForObject(sql, rowMapper, id);
//
//        return post;
//    }
//
//    public void save(PostDTO postDTO) {
//        String sql = "INSERT INTO post (title, content) VALUES (?, ?)";
//        jdbcTemplate.update(sql, postDTO.getTitle(), postDTO.getContent());
//    }
//
//    public void update(Long id, PostDTO postDTO) {
//        String sql = "UPDATE post SET title = ?, content = ? WHERE id = ?";
//        jdbcTemplate.update(sql, postDTO.getTitle(), postDTO.getContent(), id);
//    }
//
//    public void delete(Long id){
//        String sql = "DELETE FROM post WHERE id = ?";
//        jdbcTemplate.update(sql, id);
//    }
//
//}
