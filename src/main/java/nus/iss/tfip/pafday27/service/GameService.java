package nus.iss.tfip.pafday27.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.tfip.pafday27.model.Comment;
import nus.iss.tfip.pafday27.model.Game;
import nus.iss.tfip.pafday27.repository.MongoCommentRepository;
import nus.iss.tfip.pafday27.repository.SQLGameRepository;

@Service
public class GameService {

    @Autowired
    private SQLGameRepository gameRepo;

    @Autowired
    private MongoCommentRepository commentRepo;

    public List<Game> getGames(Integer limit, Integer offset) {
        return gameRepo.getGames(limit, offset);
    }

    public List<Comment> getGameComments(Integer gid) {
        return gameRepo.getGameComments(gid);
    }

    public Double getAvgRating(Integer gid) {
        return gameRepo.getAvgRating(gid);
    }

    public Game getOneGame(Integer gid) {
        return gameRepo.getOneGame(gid);
    }

    public String insertComment(Comment c) {
        return commentRepo.insertComment(c.toDocument());
    }
}
