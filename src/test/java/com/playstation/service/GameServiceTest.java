package com.playstation.service;

import com.playstation.exception.GameNotFoundException;
import com.playstation.exception.SourceNotFoundException;
import com.playstation.model.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTest {
    @Autowired
    private GameService gameService;

    @MockBean
    private CriticProvider criticProvider;

    List<Game> games;

    @Before
    public void setup() {
        games = new ArrayList<>();
    }

    @Test
    public void responseContainsListOfGamesIfListIsNotEmptyWithoutException() throws IOException {
        games.add(new Game("Far cry 3", 99));
        games.add(new Game("Call of Duty", 95));

        Mockito.when(criticProvider.getListOfGames()).thenReturn(games);

        Assert.assertTrue(gameService.getAllGames().getError() == null
            && gameService.getAllGames().getPayload() == games);
    }

    @Test
    public void responseContainsSourceNotFoundExceptionIfListIsEmpty() throws IOException {

        Mockito.when(criticProvider.getListOfGames()).thenReturn(games);

        assertThat(gameService.getAllGames().getError().getClass())
                .isEqualTo(SourceNotFoundException.class);
    }

    @Test
    public void responseContainsGameNotFoundExceptionIfTitleDoesNotExist() throws IOException {
        Mockito.when(criticProvider.getGameByTitle(Mockito.anyString())).thenReturn(null);

        assertThat(gameService.getGameByTitle(Mockito.anyString())
                .getError().getClass()).isEqualTo(GameNotFoundException.class);
    }

    @Test
    public void responseContainsSourceNotFoundExceptionIfTitleDoesNotExist() {
        try {
            Mockito.when(criticProvider.getGameByTitle(Mockito.anyString()))
                    .thenThrow( new IOException());
        } catch (IOException e) {

            assertThat(gameService.getGameByTitle(Mockito.anyString())
                    .getError().getClass()).isEqualTo(SourceNotFoundException.class);
        }
    }
}
