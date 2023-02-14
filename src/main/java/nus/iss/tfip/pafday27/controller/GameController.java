package nus.iss.tfip.pafday27.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nus.iss.tfip.pafday27.model.Comment;
import nus.iss.tfip.pafday27.model.Game;
import nus.iss.tfip.pafday27.service.GameService;

@Controller
@RequestMapping
public class GameController {

    @Autowired
    private GameService gameSvc;

    @GetMapping(path = { "/", "index.html" })
    public String getGames(@RequestParam(defaultValue="100") Integer limit,@RequestParam(defaultValue="0") Integer offset,  Model model) {
        List<Game> gameList = gameSvc.getGames(limit, offset);
        model.addAttribute("gameList", gameList);
        return "games";
    }

    @GetMapping(path = "/game/{gid}")
    public String getGames(@PathVariable Integer gid, Model model) {
        List<Comment> commentList = gameSvc.getGameComments(gid);
        model.addAttribute("commentList", commentList);
        return "comments";
    }
}
