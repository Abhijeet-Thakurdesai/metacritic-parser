package com.playstation.service.impl;

import com.playstation.model.Game;
import com.playstation.service.CriticProvider;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MetaCriticProvider implements CriticProvider, InitializingBean {

    @Value("${url}")
    private String url;

    @Value("${tablecssclass}")
    private String tableCssClass;

    @Value("${gamenamecssclass}")
    private String gameNameCssClass;

    @Value("${gamescorecssclass}")
    private String gameScoreCssClass;

    private Connection metaCriticHandle;

    @Override
    public void afterPropertiesSet() {
        metaCriticHandle = Jsoup.connect(url);
    }

    @Override
    public List<Game> getListOfGames() throws IOException {
        ArrayList<Game> games = new ArrayList<>();
        Elements table = getDataFromHtmlTable();

        for (Element row: table) {
            if (row.html().isEmpty()) {
                continue;
            }

            games.add(new Game(row.select(gameNameCssClass).text(),
                    row.select(gameScoreCssClass).first().text()));
        }
        return games;
    }

    @Override
    public Game getGameByTitle(String title) throws IOException {
        Elements table = getDataFromHtmlTable();

        for (Element row: table) {
            String currentTitle = row.select(gameNameCssClass).text().trim();

            if (!row.html().isEmpty() && title.equals(currentTitle)) {
                return new Game(currentTitle, row.select(gameScoreCssClass).first().text());
            }

        }

        return null;
    }

    private Elements getDataFromHtmlTable() throws IOException {
        Elements table = null;

        Document webPage = metaCriticHandle.get();
        table = webPage.select(tableCssClass).select("tr");
        return table;
    }
}
