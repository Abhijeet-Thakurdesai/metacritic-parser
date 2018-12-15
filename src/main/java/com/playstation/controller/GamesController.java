package com.playstation.controller;

import com.playstation.model.Game;
import com.playstation.service.GameService;
import com.playstation.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GamesController {
    @Autowired
    GameService gameService;

    @GetMapping
    public List<Game> getAllGames() {
        Response<List<Game>> response = gameService.getAllGames();
        response.throwErrorIfUnsuccessful();

        return response.getPayload();
    }

    @GetMapping("/{title}")
    public Game getGameByName(@PathVariable String title) {
        Response<Game> response = gameService.getGameByTitle(title);
        response.throwErrorIfUnsuccessful();

        return response.getPayload();
    }
}
