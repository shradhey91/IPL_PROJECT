import analysis.Analysis;
import java.util.List;
import loader.CsvLoader;
import model.Delivery;
import model.Match;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Match> matches = CsvLoader.loadMatches("data/matches.csv");
        List<Delivery> deliveries = CsvLoader.loadDeliveries("data/deliveries.csv");

        Analysis a = new Analysis();

        System.out.println("Matches loaded: " + matches.size());
        System.out.println("Deliveries loaded: " + deliveries.size());

        // a.matchesPlayedPerYear(matches);
        // a.matchesWonPerTeam(matches);
        // a.extraRunsPerTeam(matches, deliveries);
        // a.bowlersEconomyArrange(matches, deliveries);
        // a.matchesPlayedPerCity(matches);
        // a.tosswinnerMostTimes(matches);
        // a.batVsField(matches);
        // a.matchesEndedByRunsWickets(matches);
        // a.mostPlayerOfTheMatch(matches);
        // a.totalSixAndFour(deliveries);
        // a.mostTotalRuns(deliveries);
        // a.mostWicketsBowler(deliveries);
        // a.winPercentOfTeams(matches);
        // a.headToHead(matches);
        // a.top5batsmanWithStrikeRate(deliveries);
        a.highestSinglesAgainstCSK2015(matches, deliveries);
    }
}