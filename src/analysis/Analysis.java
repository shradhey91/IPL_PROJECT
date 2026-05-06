package analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import model.Delivery;
import model.Match;

public class Analysis {

    public void matchesPlayedPerYear(List<Match> matches) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (var m : matches) {
            map.put(m.season, map.getOrDefault(m.season, 0) + 1);
        }

        System.out.println(map);
    }

    public void matchesWonPerTeam(List<Match> matches) {
        HashMap<String, Integer> map = new HashMap<>();

        for (var m : matches) {
            map.put(m.winner, map.getOrDefault(m.winner, 0) + 1);
        }

        System.out.println(map);
    }

    public void extraRunsPerTeam(List<Match> matches, List<Delivery> deliverys) {

        Set<Integer> matchId = new HashSet<>();
        for (var m : matches) {
            if (m.season == 2016) {
                matchId.add(m.id);
            }
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (Delivery d : deliverys) {
            if (matchId.contains(d.matchId)) {
                map.put(d.bowlingTeam, map.getOrDefault(d.bowlingTeam, 0) + d.extraRuns);
            }
        }
        System.out.println(map);
    }

    public void bowlersEconomyArrange(List<Match> matches, List<Delivery> deliveries) {

        Set<Integer> matchIds = new HashSet<>();
        for (Match m : matches) {
            if (m.season == 2015) {
                matchIds.add(m.id);
            }
        }

        HashMap<String, Integer> runsMap = new HashMap<>();
        HashMap<String, Integer> ballsMap = new HashMap<>();

        for (Delivery d : deliveries) {
            if (matchIds.contains(d.matchId)) {

                runsMap.put(d.bowler,
                        runsMap.getOrDefault(d.bowler, 0) + d.totalRuns);

                if (d.wideRuns == 0) {
                    ballsMap.put(d.bowler,
                            ballsMap.getOrDefault(d.bowler, 0) + 1);
                }
            }
        }

        HashMap<String, Double> economyMap = new HashMap<>();

        for (String bowler : runsMap.keySet()) {
            int runs = runsMap.get(bowler);
            int balls = ballsMap.getOrDefault(bowler, 0);

            if (balls > 0) {
                double economy = (runs * 6.0) / balls;
                economyMap.put(bowler, economy);
            }
        }

        List<Map.Entry<String, Double>> list = new ArrayList<>(economyMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> a, Map.Entry<String, Double> b) {
                return Double.compare(a.getValue(), b.getValue());
            }
        });

        int count = 0;
        for (Map.Entry<String, Double> e : list) {
            System.out.println(e.getKey() + " -> " + e.getValue());
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    public void matchesPlayedPerCity(List<Match> matches) {
        HashMap<String, Integer> ans = new HashMap<>();

        for (var m : matches) {
            ans.put(m.city, ans.getOrDefault(m.city, 0) + 1);
        }

        System.out.println(ans);
    }

    public void tosswinnerMostTimes(List<Match> matches) {
        HashMap<String, Integer> map = new HashMap<>();

        for (var m : matches) {
            map.put(m.tossWinner, map.getOrDefault(m.tossWinner, 0) + 1);
        }

        System.out.println(map);

    }

    // public void batVsField(List<Match> matches){
    //     HashMap<String,Integer> bat = new HashMap<>();
    //     HashMap<String,Integer> field = new HashMap<>();
    //     for(var m : matches){
    //         if(m.tossDecision.equals("field")){
    //             field.put(m.tossDecision,field.getOrDefault(m.tossDecision, 0)+1);
    //         }
    //         if(m.tossDecision.equals("bat")){
    //             bat.put(m.tossDecision,bat.getOrDefault(m.tossDecision, 0)+1);
    //         }
    //     }
    //         for(var d : bat.entrySet()){
    //             System.out.println(d.getKey() + " -->" + d.getValue());
    //         }
    //         System.out.println();
    //         for(var d : field.entrySet()){
    //             System.out.println(d.getKey() + " -->" + d.getValue());
    //         }
    // }
    public void batVsField(List<Match> matches) {

        HashMap<String, int[]> map = new HashMap<>();

        for (Match m : matches) {

            String team = m.tossWinner;

            map.putIfAbsent(team, new int[2]);

            if (m.tossDecision.equals("bat")) {
                map.get(team)[0]++;
            } else if (m.tossDecision.equals("field")) {
                map.get(team)[1]++;
            }
        }

        for (Map.Entry<String, int[]> entry : map.entrySet()) {
            String team = entry.getKey();
            int batCount = entry.getValue()[0];
            int fieldCount = entry.getValue()[1];

            System.out.println(team + " -> Bat: " + batCount + ", Field: " + fieldCount);
        }
    }

    // public void matchesEndedByRunsWickets(List<Match> matches) {
    //     HashMap<String,Integer> byWicket = new HashMap<>();
    //     HashMap<String,Integer> byRun = new HashMap<>();
    //     for(var m : matches){
    //         byWicket.put("winByWickets",byWicket.getOrDefault(m.win_by_wickets, 0)+1);
    //         byRun.put("ByRun",byRun.getOrDefault(m.win_by_runs, 0)+1);
    //     }
    //     for(var ans : byWicket.entrySet()){
    //         System.out.println(ans.getKey() + "-->" + ans.getValue());
    //     }
    //     for(var ans : byRun.entrySet()){
    //         System.out.println(ans.getKey() + "-->" + ans.getValue());
    //     }
    // }
    public void matchesEndedByRunsWickets(List<Match> matches) {
        int endedByRuns = 0;
        int endedByWickets = 0;

        for (var m : matches) {
            if (m.win_by_runs > 0) {
                endedByRuns++;
            } else {
                endedByWickets++;
            }
        }
        System.out.println("Ended By Runs --> " + endedByRuns);
        System.out.println("Ended By Wickets --> " + endedByWickets);
    }

    public void mostPlayerOfTheMatch(List<Match> matches) {
        HashMap<String, Integer> ans = new HashMap<>();

        for (var m : matches) {
            ans.put(m.playerOfMatch, ans.getOrDefault(m.playerOfMatch, 0) + 1);
        }

        Map<String, Integer> sorted = new TreeMap<>(ans);

        for (var m : sorted.entrySet()) {
            System.out.println(m.getKey() + " --> " + m.getValue());
        }

    }

    public void mostTotalRuns(List<Delivery> deliverys) {

        HashMap<String, Integer> ans = new HashMap<>();

        for (var d : deliverys) {
            String team = d.battingTeam;
            ans.put(team, ans.getOrDefault(team, 0) + d.totalRuns);
        }

        String maxTeam = "";
        Integer maxRuns = 0;

        for (var m : ans.entrySet()) {
            if (m.getValue() > maxRuns) {
                maxRuns = m.getValue();
                maxTeam = m.getKey();
            }
        }
        System.out.println("MaxRuns -->" + maxRuns + "maxTeam -->" + maxTeam);
    }

    public void totalSixAndFour(List<Delivery> deliverys) {

        HashMap<String, Integer> six = new HashMap<>();

        for (var d : deliverys) {
            if (d.batsmanRuns == 6) {
                six.put(d.battingTeam, six.getOrDefault(d.battingTeam, 0) + 1);
            }
        }
        HashMap<String, Integer> four = new HashMap<>();
        for (var d : deliverys) {
            if (d.batsmanRuns == 4) {
                four.put(d.battingTeam, four.getOrDefault(d.battingTeam, 0) + 1);
            }
        }

        for (var s : six.entrySet()) {
            System.out.println("SIXES  " + s.getKey() + " : " + s.getValue());
        }

        for (var s : four.entrySet()) {
            System.out.println("FOUR  " + s.getKey() + " : " + s.getValue());
        }
    }

    public void mostWicketsBowler(List<Delivery> deliverys) {
        HashMap<String, Integer> ans = new HashMap<>();

        for (var d : deliverys) {
            if (!d.playerDismissed.isEmpty() && !d.dismissalKind.equals("run out")) {
                ans.put(d.bowler, ans.getOrDefault(d.bowler, 0) + 1);
            }
        }

        for (var a : ans.entrySet()) {
            System.out.println("Bowler -> " + a.getKey() + "   Wickets ->" + a.getValue());
        }
    }

    public void winPercentOfTeams(List<Match> matches) {

        HashMap<String, Integer> matchesPlayed = new HashMap<>();
        HashMap<String, Integer> totalWins = new HashMap<>();

        for (Match m : matches) {

            matchesPlayed.put(m.team1,
                    matchesPlayed.getOrDefault(m.team1, 0) + 1);

            matchesPlayed.put(m.team2,
                    matchesPlayed.getOrDefault(m.team2, 0) + 1);

            if (m.winner != null && !m.winner.isEmpty()) {
                totalWins.put(m.winner,
                        totalWins.getOrDefault(m.winner, 0) + 1);
            }
        }

        HashMap<String, Double> winPercent = new HashMap<>();

        for (String team : matchesPlayed.keySet()) {

            int played = matchesPlayed.get(team);
            int wins = totalWins.getOrDefault(team, 0);

            double percentage = (wins * 100.0) / played;

            winPercent.put(team, percentage);
        }

        System.out.println(winPercent);
    }

    public void top5batsmanWithStrikeRate(List<Delivery> deliveries) {

        HashMap<String, Integer> runsMap = new HashMap<>();
        HashMap<String, Integer> ballsMap = new HashMap<>();

        for (Delivery d : deliveries) {

            runsMap.put(d.batsman,
                    runsMap.getOrDefault(d.batsman, 0) + d.batsmanRuns);

            if (d.wideRuns == 0) {
                ballsMap.put(d.batsman,
                        ballsMap.getOrDefault(d.batsman, 0) + 1);
            }
        }

        HashMap<String, Double> srMap = new HashMap<>();

        for (String batsman : runsMap.keySet()) {

            int runs = runsMap.get(batsman);
            int balls = ballsMap.getOrDefault(batsman, 0);

            if (balls > 0) {
                double sr = (runs * 100.0) / balls;

                if (balls >= 100) {
                    srMap.put(batsman, sr);
                }
            }
        }

        List<Map.Entry<String, Double>> list
                = new ArrayList<>(srMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> a,
                    Map.Entry<String, Double> b) {
                return Double.compare(b.getValue(), a.getValue());
            }
        });

        int count = 0;
        for (Map.Entry<String, Double> e : list) {
            System.out.println(e.getKey() + " -> " + e.getValue());
            count++;
            if (count == 5) {
                break;
            }
        }
    }

    public void mostCatchesDeath2016(List<Match> matches,
            List<Delivery> deliveries) {

        HashMap<Integer, Match> map = new HashMap<>();

        for (Match m : matches) {

            if (m.season == 2016) {
                map.put(m.id, m);
            }
        }

        HashMap<String, Integer> catches = new HashMap<>();

        for (Delivery d : deliveries) {

            if (map.containsKey(d.matchId)
                    && d.over >= 16
                    && d.dismissalKind.equals("caught")) {

                Match m = map.get(d.matchId);

                String fieldingTeam;

                if (d.battingTeam.equals(m.team1)) {
                    fieldingTeam = m.team2;
                } else {
                    fieldingTeam = m.team1;
                }

                catches.put(fieldingTeam,
                        catches.getOrDefault(fieldingTeam, 0) + 1);
            }
        }

        String topTeam = "";
        int max = 0;

        for (var e : catches.entrySet()) {

            if (e.getValue() > max) {
                max = e.getValue();
                topTeam = e.getKey();
            }
        }

        System.out.println(topTeam + " -> " + max);
    }

    public void bestDeathOverBowlerPerSeason(List<Match> matches,
            List<Delivery> deliveries) {

        HashMap<Integer, Integer> matchSeason = new HashMap<>();

        for (Match m : matches) {
            matchSeason.put(m.id, m.season);
        }

        HashMap<Integer, HashMap<String, Integer>> runsMap = new HashMap<>();
        HashMap<Integer, HashMap<String, Integer>> ballsMap = new HashMap<>();

        for (Delivery d : deliveries) {

            if (d.over >= 16) {

                int season = matchSeason.get(d.matchId);

                runsMap.putIfAbsent(season, new HashMap<>());
                ballsMap.putIfAbsent(season, new HashMap<>());

                HashMap<String, Integer> rMap = runsMap.get(season);
                HashMap<String, Integer> bMap = ballsMap.get(season);

                rMap.put(d.bowler,
                        rMap.getOrDefault(d.bowler, 0) + d.totalRuns);

                if (d.wideRuns == 0) {

                    bMap.put(d.bowler,
                            bMap.getOrDefault(d.bowler, 0) + 1);
                }
            }
        }

        for (Integer season : runsMap.keySet()) {

            String bestBowler = "";
            double bestEconomy = Double.MAX_VALUE;

            HashMap<String, Integer> rMap = runsMap.get(season);
            HashMap<String, Integer> bMap = ballsMap.get(season);

            for (String bowler : rMap.keySet()) {

                int runs = rMap.get(bowler);
                int balls = bMap.getOrDefault(bowler, 0);

                if (balls > 0) {

                    double economy = (runs * 6.0) / balls;

                    if (economy < bestEconomy) {
                        bestEconomy = economy;
                        bestBowler = bowler;
                    }
                }
            }

            System.out.println(season + " -> "
                    + bestBowler + " -> " + bestEconomy);
        }
    }

    public void mostRunsAgainstRCBByVenue2016(List<Match> matches,
            List<Delivery> deliveries) {

        HashMap<Integer, String> venueMap = new HashMap<>();
        HashMap<Integer, Match> matchMap = new HashMap<>();

        for (Match m : matches) {

            if (m.season == 2016
                    && (m.team1.equals("Royal Challengers Bangalore")
                    || m.team2.equals("Royal Challengers Bangalore"))) {

                venueMap.put(m.id, m.venue);
                matchMap.put(m.id, m);
            }
        }

        HashMap<String, HashMap<String, Integer>> map = new HashMap<>();

        for (Delivery d : deliveries) {

            if (venueMap.containsKey(d.matchId)) {

                Match m = matchMap.get(d.matchId);

        
                if (!d.battingTeam.equals("Royal Challengers Bangalore")) {

                    String venue = venueMap.get(d.matchId);

                    map.putIfAbsent(venue, new HashMap<>());

                    HashMap<String, Integer> batsmanMap = map.get(venue);

                    batsmanMap.put(d.batsman,
                            batsmanMap.getOrDefault(d.batsman, 0)
                            + d.batsmanRuns);
                }
            }
        }

        for (String venue : map.keySet()) {

            HashMap<String, Integer> batsmanMap = map.get(venue);

            String topPlayer = "";
            int maxRuns = 0;

            for (var e : batsmanMap.entrySet()) {

                if (e.getValue() > maxRuns) {
                    maxRuns = e.getValue();
                    topPlayer = e.getKey();
                }
            }

            System.out.println(venue + " -> "
                    + topPlayer + " -> " + maxRuns);
        }
    }

    public void highestSinglesAgainstCSK2015(List<Match> matches,
            List<Delivery> deliveries) {

        HashMap<Integer, String> map = new HashMap<>();

        for (Match m : matches) {

            if (m.season == 2015
                    && (m.team1.equals("Chennai Super Kings")
                    || m.team2.equals("Chennai Super Kings"))) {

                if (m.team1.equals("Chennai Super Kings")) {
                    map.put(m.id, m.team2);
                } else {
                    map.put(m.id, m.team1);
                }
            }
        }

        HashMap<String, Integer> singles = new HashMap<>();

        for (Delivery d : deliveries) {

            if (map.containsKey(d.matchId)
                    && d.over >= 16
                    && d.batsmanRuns == 1) {

                singles.put(d.battingTeam,
                        singles.getOrDefault(d.battingTeam, 0) + 1);
            }
        }

        String topTeam = "";
        int max = 0;

        for (var e : singles.entrySet()) {

            if (e.getValue() > max) {
                max = e.getValue();
                topTeam = e.getKey();
            }
        }

        System.out.println(topTeam + " -> " + max);
    }

    public void headToHead(List<Match> matches) {

        Map<String, Map<String, Integer>> map = new HashMap<>();

        for (Match m : matches) {

            if (m.winner == null || m.winner.isEmpty()) {
                continue;
            }

            String winner = m.winner;
            String loser;

            if (winner.equals(m.team1)) {
                loser = m.team2;
            } else {
                loser = m.team1;
            }

            map.putIfAbsent(winner, new HashMap<>());

            Map<String, Integer> inner = map.get(winner);

            inner.put(loser, inner.getOrDefault(loser, 0) + 1);
        }

        for (Map.Entry<String, Map<String, Integer>> e : map.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }

}
