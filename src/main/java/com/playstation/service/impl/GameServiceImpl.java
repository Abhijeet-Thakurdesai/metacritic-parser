package com.playstation.service.impl;

import com.playstation.exception.GameNotFoundException;
import com.playstation.model.Game;
import com.playstation.service.CriticProvider;
import com.playstation.service.GameService;
import com.playstation.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    CriticProvider criticProvider;

    @Override
    public Response<List<Game>> getAllGames() {
        Response<List<Game>> response = new Response<>();

        try {
            response.setPayload(criticProvider.getListOfGames());

            if (response.getPayload().isEmpty())
                throw new IOException();

            return response;
        } catch (IOException e) {
            response.setErrorToSourceNotFound();
            return response;
        }
    }

    @Override
    public Response<Game> getGameByTitle(String title) {
        Response<Game> response = new Response<>();

        try {
            response.setPayload(criticProvider.getGameByTitle(title));
            if (response.getPayload() == null)
                response.setError(new GameNotFoundException());

            return response;
        } catch (IOException e) {
            response.setErrorToSourceNotFound();
            return response;
        }
    }
}
