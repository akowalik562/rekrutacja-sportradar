package org.example;

import org.example.exception.InvalidGameIndexException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Scoreboard {
    private final List<Game> games;

    public Scoreboard() {
        this.games = new ArrayList<>();
    }

    public void startNewGame(String homeTeam, String awayTeam) {
        games.add(new Game(homeTeam, awayTeam));
    }

    public void updateScore(int gameIndex, int homeScore, int awayScore) {
        if (gameIndex >= 0 && gameIndex < games.size()) {
            Game game = games.get(gameIndex);
            game.setScores(homeScore, awayScore);
        } else {
            throw new InvalidGameIndexException(gameIndex);
        }
    }

    public void finishGame(int gameIndex) {
        if (gameIndex >= 0 && gameIndex < games.size()) {
            games.remove(gameIndex);
        } else {
            throw new InvalidGameIndexException(gameIndex);
        }
    }

    public List<Game> getGamesInProgressOrderedByTotalScore() {
        games.sort(Comparator.comparingInt(Game::getTotalScore)
                .reversed());
        return games;
    }
}
