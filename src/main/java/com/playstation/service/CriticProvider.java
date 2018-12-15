package com.playstation.service;

import com.playstation.model.Game;

import java.io.IOException;
import java.util.List;

public interface CriticProvider {
    public List<Game> getListOfGames() throws IOException;

    public Game getGameByTitle(String title) throws IOException;
}
