package com.playstation.service;

import com.playstation.model.Game;
import com.playstation.util.Response;

import java.util.List;

public interface GameService {
    public Response<List<Game>> getAllGames();

    public Response<Game> getGameByTitle(String title);
}
