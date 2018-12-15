package com.playstation.controller;

import com.playstation.model.Game;
import com.playstation.service.GameService;
import com.playstation.util.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GamesController.class, secure = false)
public class GamesControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Test
    public void returnsListofGames () throws Exception {
        List<Game> games = new ArrayList<>();
        games.add(new Game("Far cry 3", 1));
        Response<List<Game>> response = new Response<>();
        response.setPayload(games);

        Mockito.when(gameService.getAllGames()).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(
                "/games").accept(MediaType.APPLICATION_JSON)).andReturn();


        Mockito.verify(gameService).getAllGames();
    }

    @Test
    public void returnsSingleGameWhenGivenTitle() throws Exception {
        Game game = new Game("Far cry 3", 1);
        Response<Game> response = new Response<>();
        response.setPayload(game);

        Mockito.when(gameService.getGameByTitle("Far cry 3")).thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(
                "/games/{title}","Far cry 3")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Mockito.verify(gameService).getGameByTitle("Far cry 3");
    }
}
