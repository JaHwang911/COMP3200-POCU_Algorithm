package academy.pocu.comp3500.assignment1.app;

import academy.pocu.comp3500.assignment1.PocuBasketballAssociation;
import academy.pocu.comp3500.assignment1.pba.GameStat;
import academy.pocu.comp3500.assignment1.pba.Player;

public class Program {

    public static void main(String[] args) {
//        TestProcessGameStats();
//        TestFindPlayerPointsPerGame();
//        TestFindPlayerShootingPercentage();
//        TestFind3ManDreamTeam();

        temp();
    }

    private static void temp() {
        Player[] players = new Player[] {
                new Player("Player 2", 5, 12, 14, 50),
                new Player("Player 6", 15, 2, 5, 40),
                new Player("Player 5", 11, 1, 11, 54),
                new Player("Player 4", 10, 3, 51, 88),
                new Player("Player 7", 16, 8, 5, 77),
                new Player("Player 1", 1, 15, 2, 22),
                new Player("Player 3", 7, 5, 8, 66)
        };

        PocuBasketballAssociation.sortByAssistRecursive(players, 0, players.length - 1);

        Player[] players2 = new Player[] {
                new Player("Player 2", 5, 5, 17, 50),
                new Player("Player 6", 15, 4, 10, 40),
                new Player("Player 5", 11, 3, 25, 54),
                new Player("Player 4", 10, 9, 1, 88),
                new Player("Player 7", 16, 7, 5, 77),
                new Player("Player 1", 1, 2, 8, 22),
                new Player("Player 9", 42, 15, 4, 56),
                new Player("Player 8", 33, 11, 3, 72),
        };

        PocuBasketballAssociation.sortByAssistRecursive(players2, 0, players2.length - 1);

        Player[] players3 = new Player[] {
                new Player("Player 1", 2, 5, 10, 78),
                new Player("Player 2", 10, 4, 5, 66),
                new Player("Player 3", 3, 3, 2, 22),
                new Player("Player 4", 1, 9, 8, 12),
                new Player("Player 5", 11, 1, 12, 26),
                new Player("Player 6", 7, 2, 10, 15),
                new Player("Player 7", 8, 15, 3, 11),
                new Player("Player 8", 5, 7, 13, 5),
                new Player("Player 9", 8, 2, 7, 67),
                new Player("Player 10", 1, 11, 1, 29),
                new Player("Player 11", 2, 6, 9, 88)
        };

        PocuBasketballAssociation.sortByAssistRecursive(players3, 0, players3.length - 1);
    }

    private static void TestFind3ManDreamTeam() {
        Player[] players = new Player[] {
                new Player("Player 2", 5, 12, 14, 50),
                new Player("Player 6", 15, 2, 5, 40),
                new Player("Player 5", 11, 1, 11, 54),
                new Player("Player 4", 10, 3, 51, 88),
                new Player("Player 7", 16, 8, 5, 77),
                new Player("Player 1", 1, 15, 2, 22),
                new Player("Player 3", 7, 5, 8, 66)
        };

        Player[] outPlayers = new Player[3];
        Player[] scratch = new Player[3];

        long maxTeamwork = PocuBasketballAssociation.find3ManDreamTeam(players, outPlayers, scratch); // maxTeamwork: 219, outPlayers: [ Player 4, Player 2, Player 3 ]
    }
}
