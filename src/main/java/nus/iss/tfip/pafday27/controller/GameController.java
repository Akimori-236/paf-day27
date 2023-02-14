package nus.iss.tfip.pafday27.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import nus.iss.tfip.pafday27.model.Comment;
import nus.iss.tfip.pafday27.model.Game;
import nus.iss.tfip.pafday27.service.GameService;

@Controller
@RequestMapping
public class GameController {

    @Autowired
    private GameService gameSvc;

    @GetMapping(path = { "/", "/index.html", "/game", "/game/" })
    public String getGames(@RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset, Model model) {
        List<Game> gameList = gameSvc.getGames(limit, offset);
        model.addAttribute("gameList", gameList);
        if (offset == 0) {
            model.addAttribute("prev", 0);
        } else {
            model.addAttribute("prev", offset - limit);
        }
        model.addAttribute("next", offset + limit);
        return "index";
    }

    // @GetMapping(path = "/game/{gid}")
    // public String getGames(@PathVariable Integer gid, Model model) {
    // List<Comment> commentList = gameSvc.getGameComments(gid);
    // model.addAttribute("commentList", commentList);
    // return "comments";
    // }

    @GetMapping(path = "/game/{gid}")
    public String getGame(@PathVariable Integer gid, Model model) {
        List<Comment> commentList = gameSvc.getGameComments(gid);
        Double avgRating = gameSvc.getAvgRating(gid);
        Game g = gameSvc.getOneGame(gid);
        Comment c = new Comment();
        model.addAttribute("comment", c);
        model.addAttribute("game", g);
        model.addAttribute("commentList", commentList);
        model.addAttribute("rating", avgRating);
        return "comments";
    }

    @PostMapping(path = "/game/{gid}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    // @RequestBody MultiValueMap<String, String>
    public String postComment(@Valid Comment c, @PathVariable Integer gid, Model model) {
        System.out.println("NEW COMMENT >>> " + c.getGid());
        String c_id = gameSvc.insertComment(c);
        if (c_id.isBlank() || c_id.isEmpty()) {
            return "comments";
        }
        int limit = 10;
        int offset = 0;
        List<Game> gameList = gameSvc.getGames(limit, offset);
        model.addAttribute("gameList", gameList);
        if (offset == 0) {
            model.addAttribute("prev", 0);
        } else {
            model.addAttribute("prev", offset - limit);
        }
        model.addAttribute("next", offset + limit);
        return "index";
    }
}
