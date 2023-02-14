package nus.iss.tfip.pafday27.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.tfip.pafday27.model.Comment;
import nus.iss.tfip.pafday27.model.Game;
import nus.iss.tfip.pafday27.repository.GameRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepo;

    public List<Game> getGames(Integer limit, Integer offset) {
        return gameRepo.getGames(limit, offset);
    }

    public List<Comment> getGameComments(Integer gid) {
        return gameRepo.getGameComments(gid);
    }
}
