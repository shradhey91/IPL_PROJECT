package analysis;

import java.util.HashMap;
import java.util.List;

import model.Delivery;
import model.Match;

public class Review {

    public void playerWithHighestStrikeRateAgainstRcbIn2016ByVenue(List<Match> matches, List<Delivery> delivery) {

        HashMap<Integer, String> venues = new HashMap<>();

        for (var m : matches) {
            if (m.season == 2016
                    && (m.team1.equals("Royal Challengers Bangalore") || m.team2.equals("Royal Challengers Bangalore"))) {
                venues.put(m.id, m.venue);
            }
        }

        HashMap<String, HashMap<String, Integer>> totalRuns = new HashMap<>();

        HashMap<String, HashMap<String, Integer>> totalBalls = new HashMap<>();

        for (var d : delivery) {    
            if (venues.containsKey(d.matchId)
                    && !d.battingTeam.equals("Royal Challengers Bangalore")) {
                String venue = venues.get(d.matchId);

                totalRuns.putIfAbsent(venue, new HashMap<>());
                totalBalls.putIfAbsent(venue, new HashMap<>());

                HashMap<String, Integer> runmap = totalRuns.get(venue);

                runmap.put(d.batsman, runmap.getOrDefault(d.batsman, 0) + d.batsmanRuns);

                if (d.wideRuns == 0) {
                    HashMap<String, Integer> ballmap = totalBalls.get(venue);
                    ballmap.put(d.batsman, ballmap.getOrDefault(d.batsman, 0) + 1);
                }

            }
        }

        for (var venue : totalRuns.keySet()) {
            HashMap<String, Integer> runmap = totalRuns.get(venue);
            HashMap<String, Integer> ballmap = totalBalls.get(venue);

            String player = "";
            float beststrikeRate = 0;

            for (var batsman : runmap.keySet()) {

                int runs = runmap.get(batsman);

                int balls = ballmap.getOrDefault(batsman, 0);

                if (balls > 0) {
                    float strikeRate = (float) (runs * 100) / balls;

                    if (strikeRate > beststrikeRate) {
                        beststrikeRate = strikeRate;
                         player = batsman; 

                    }
                }

            }

            System.out.println(venue + " " + player + " " + beststrikeRate);
        }
    }
}
