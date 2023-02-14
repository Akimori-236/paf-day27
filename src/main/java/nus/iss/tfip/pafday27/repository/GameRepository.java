package nus.iss.tfip.pafday27.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import nus.iss.tfip.pafday27.model.Comment;
import nus.iss.tfip.pafday27.model.Game;

@Repository
public class GameRepository implements Queries {

    @Autowired
    private JdbcTemplate template;

    public List<Game> getGames() {
        return getGames(100, 0);
    }

    // SELECT gid, name FROM game LIMIT ? OFFSET ?;
    public List<Game> getGames(Integer limit, Integer offset) {
        List<Game> gamelist = template.query(SQLGetGames, BeanPropertyRowMapper.newInstance(Game.class), limit, offset);
        return gamelist;
    }

    // SELECT * FROM comments WHERE gid=?;
    public List<Comment> getGameComments(Integer gid) {
        List<Comment> commentlist = template.query(SQLGetGameComments, BeanPropertyRowMapper.newInstance(Comment.class),
                gid);
        return commentlist;
    }
}
