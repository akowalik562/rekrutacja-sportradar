package org.example;

import org.example.exception.InvalidGameIndexException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreboardTest {

    private Scoreboard scoreboard;

    @BeforeEach
    void setUp() {
        scoreboard = new Scoreboard();
    }

    @Test
    void testStartNewGame() {
        scoreboard.startNewGame("Team A", "Team B");
        List<Game> games = scoreboard.getGamesInProgressOrderedByTotalScore();
        assertEquals(1, games.size());
    }

    @Test
    void testUpdateScore() {
        scoreboard.startNewGame("Team A", "Team B");
        scoreboard.updateScore(0, 2, 1);
        List<Game> games = scoreboard.getGamesInProgressOrderedByTotalScore();
        assertEquals(3, games.get(0).getTotalScore());
    }

    @Test
    void testShouldNotUpdateScore_InvalidGameIndexException() {
        scoreboard.startNewGame("Team A", "Team B");
        assertThrows(InvalidGameIndexException.class, () -> scoreboard.updateScore(1, 2, 1));
    }

    @Test
    void testFinishGame() {
        scoreboard.startNewGame("Team A", "Team B");
        scoreboard.finishGame(0);
        List<Game> games = scoreboard.getGamesInProgressOrderedByTotalScore();
        assertEquals(0, games.size());
    }

    @Test
    void testShouldNotFinishGame_InvalidGameIndexException() {
        scoreboard.startNewGame("Team A", "Team B");
        assertThrows(InvalidGameIndexException.class, () -> scoreboard.finishGame(1));

    }

    @Test
    void testGetGamesInProgressOrderedByTotalScore() {
        scoreboard.startNewGame("Mexico", "Canada");
        scoreboard.startNewGame("Spain", "Brazil");
        scoreboard.startNewGame("Germany", "France");

        scoreboard.updateScore(0, 0, 5);
        scoreboard.updateScore(1, 10, 2);
        scoreboard.updateScore(2, 2, 2);

        List<Game> games = scoreboard.getGamesInProgressOrderedByTotalScore();

        assertEquals("Spain 10 - 2 Brazil", games.get(0).toString());
        assertEquals("Mexico 0 - 5 Canada", games.get(1).toString());
        assertEquals("Germany 2 - 2 France", games.get(2).toString());
    }

}