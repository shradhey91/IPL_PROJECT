import analysis.*;
import java.util.List;
import loader.CsvLoader;
import model.Delivery;
import model.Match;


public class Main {

    public static void main(String[] args) throws Exception {

        List<Match>    matches    = CsvLoader.loadMatches("data/matches.csv");
        List<Delivery> deliveries = CsvLoader.loadDeliveries("data/deliveries.csv");

        System.out.println("Matches loaded:    " + matches.size());
        System.out.println("Deliveries loaded: " + deliveries.size());
        System.out.println();

        Analysis analysis = new Analysis();

        analysis.matchesPlayedPerYear(matches);
        // analysis.matchesWonPerTeam(matches);
        // analysis.extraRunsPerTeam(matches, deliveries);
        // analysis.bowlerEconomyTop10(matches, deliveries);
        // analysis.matchesPlayedPerCity(matches);
        // analysis.tossWinsPerTeam(matches);
        // analysis.batVsFieldChoice(matches);
        // analysis.matchesEndedByRunsVsWickets(matches);
        // analysis.playerOfTheMatchCounts(matches);
        // analysis.winPercentageOfTeams(matches);
        // analysis.headToHeadRecord(matches);
        // analysis.sixesAndFoursPerTeam(deliveries);
        // analysis.teamWithMostRuns(deliveries);
        // analysis.wicketsPerBowler(deliveries);
        // analysis.top5BatsmanByStrikeRate(deliveries);
    }
}
