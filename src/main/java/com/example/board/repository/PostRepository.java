package com.example.board.repository;

import com.example.board.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final JdbcTemplate jdbcTemplate;

    // ↓ 아래 생성자는 @RequiredArgsConstructor로 자동생성
//    public PostRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    private final RowMapper<PostDTO> rowMapper = (rs, rowNum) -> {
        return new PostDTO(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getTimestamp("created_at").toLocalDateTime()
        );
    };

    //전체 조회
    public List<PostDTO> findAll() {
        String sql= "SELECT * FROM post";
        return jdbcTemplate.query(sql, rowMapper);
    }

    //상세 조회
    public PostDTO findById(Long id) {
        String sql= "SELECT * FROM post WHERE id = ?";
        
        // queryForObject => 단일 행 조회
        PostDTO post = jdbcTemplate.queryForObject(sql, rowMapper, id);

        return post;
    }

    public void save(PostDTO postDTO) {
        String sql = "INSERT INTO post (title, content) VALUES (?, ?)";
        jdbcTemplate.update(sql, postDTO.getTitle(), postDTO.getContent());
    }

    public void update(Long id, PostDTO postDTO) {
        String sql = "UPDATE post SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, postDTO.getTitle(), postDTO.getContent(), id);
    }

    public void delete(Long id){
        String sql = "DELETE FROM post WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
