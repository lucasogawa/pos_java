import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class B3a2 {

    private static final int NUMBER_ROUNDS = 10;
    private static final int NUMBER_COMPETITORS = 10;

    public static int numberOfArrivals = 0;
    public static Map<String, Integer> scoreBoard = new HashMap<>();

    public static void main(String[] args) {
        new B3a2();
    }

    public B3a2() {
        IntStream.rangeClosed(1, NUMBER_ROUNDS)
            .forEachOrdered(index -> {
                System.out.println("========== Race " + index +  " ==========");
                startRace();
                System.out.println("============================\n");
            });

        orderScoreboard();

        showPodium();
        showPointsTable();
    }

    private void startRace() {
        var competitors = IntStream.rangeClosed(1, NUMBER_COMPETITORS)
            .mapToObj(index -> new Competitor("Competidor #" + index))
            .toList();

        competitors.forEach(Competitor::start);
        competitors.forEach(competitor -> {
            try {
                competitor.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        numberOfArrivals = 0;
    }

    private void orderScoreboard() {
        scoreBoard = scoreBoard.entrySet()
            .stream()
            .sorted((entrySet1, entrySet2) -> Long.compare(entrySet2.getValue(), entrySet1.getValue()))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new)
            );
    }

    private void showPodium() {
        System.out.println("\n========== Podio ==========");

        Collections.list(Collections.enumeration(scoreBoard.entrySet())).subList(0, 3)
            .forEach(entrySet -> System.out.println(entrySet.getKey() + " com " + entrySet.getValue() + " pontos"));

        System.out.println("================================");
    }

    private void showPointsTable() {
        System.out.println("\n========== Tabela de Pontos ==========");

        scoreBoard.forEach((key, value) -> System.out.println(key + " com " + value + " pontos"));

        System.out.println("================================");
    }

    public class Competitor extends Thread {
        public Competitor(String name) {
            super(name);
        }

        @Override
        public void run() {
            FinishLine.sync();
        }
    }

    public class FinishLine {
        synchronized public static void sync() {
            var threadName = Thread.currentThread().getName();

            System.out.println(threadName);

            if (B3a2.scoreBoard.containsKey(threadName)) {
                B3a2.scoreBoard.put(threadName, B3a2.scoreBoard.get(threadName) + 10 - numberOfArrivals);
            } else {
                B3a2.scoreBoard.put(threadName, 10 - numberOfArrivals);
            }

            B3a2.numberOfArrivals++;
        }
    }
}
