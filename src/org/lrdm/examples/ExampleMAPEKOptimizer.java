package org.lrdm.examples;

import org.lrdm.MeanSquaredErrorBoxPlot;
import org.lrdm.TimedRDMSim;
import org.lrdm.mapekloop.LoopIteration;
import org.lrdm.topologies.NConnectedTopology;

import java.awt.*;
import java.util.*;
import java.util.List;


public class ExampleMAPEKOptimizer {
    private static final Integer CURRENT_SITUATION_CODE = 4;

    // TODO for the next week (19.06 meeting)
    //  0. DOCUMENT ALL THE TESTING
    //  1.!  box plot (mean squared error. 20 times run)
    //  1.1  latenz bewusst
    //  1.2  latenz unbewusst
    //  2. why delta function does not work
    //  3.! no more links per mirrors than lpm (error by 60 40 20)
    //  4.
    //  5.! work with template
    //  6.  ? how long should we wait till achieving the goal AL. durchschnitt -->
    //  6.1. wie die tatsaechliche latenz ist --> kann eine extra metrik sein
    //  6.2. nachher berechnen --> wie gut die optimierung ist
    //          (fuer vergleichen latenzbewusst/unbewusst)
    //  7.! +- no latency for all situation
    //  8.~ play with start time (and +-some mirrors): two different exercises
    //  * am ende kann man verschiedene versionen von optimierer machen


    public static void main(String[] args) {
        List<Double> meanSquaredErrorList= new ArrayList<>();
        for (int i = 0; i < 3; i++) {
           meanSquaredErrorList.add(automaticRun());
        }
        System.out.println("\n MEAN SQUARED ERROR LIST:");
        meanSquaredErrorList.forEach(System.out::println);

        EventQueue.invokeLater(() -> MeanSquaredErrorBoxPlot.display(meanSquaredErrorList));
    }

    private static double automaticRun() {

        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %5$s %n");

        TimedRDMSim sim = new TimedRDMSim();
        sim.initialize(new NConnectedTopology());
        LoopIteration loopIteration = new LoopIteration(sim);

        List<Integer> listOfIterations = getIterations();
        double meanSquaredError = 0;
        for (int i = 1; i < sim.getSimTime(); i++) {
            sim.getEffector().setTargetAL(i, listOfIterations.get(i));
            sim.runStepForOptimizer(i, listOfIterations.get(i));
            meanSquaredError = loopIteration.runMAPEKCheckOnIteration(i, sim, CURRENT_SITUATION_CODE);
        }
        return meanSquaredError;
    }

    private static List<Integer> getIterations() {
        Map<Integer, Integer> loopMap = setCurrentSituationMap(CURRENT_SITUATION_CODE);

        Integer prevMapIteration = 0;

        List<Integer> listOfIterations = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : loopMap.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            prevMapIteration = key - prevMapIteration;
            for (int j = 0; j < prevMapIteration; j++) {
                listOfIterations.add(value);
            }
            if (prevMapIteration == 0) listOfIterations.remove(listOfIterations.size() - 1);
            prevMapIteration = key;
        }
        return listOfIterations;
    }

    public static Map<Integer, Integer> setCurrentSituationMap(Integer situation) {
        return switch (situation) {
            case 1 ->
                //pick situation
                    new TreeMap<>(Map.of(
                            50, 35,
                            60, 60,
                            150, 35
                    ));
            case 2 ->
                //high low high
                    new TreeMap<>(Map.of(
                            50, 35,
                            100, 20,
                            150, 50
                    ));
            case 3 ->
                //high high high
                    new TreeMap<>(Map.of(
                            50, 20,
                            100, 40,
                            150, 60
                    ));
            case 4 ->
                // continuously high
                    new TreeMap<>(Map.of(
                            30, 20,
                            50, 40,
                            60, 50,
                            90, 70,
                            120, 80,
                            150, 100
                    ));
            case 5 ->
                //high low low
                    new TreeMap<>(Map.of(
                            50, 60,
                            100, 40,
                            150, 20
                    ));
            case 6 ->
                //continuously low
                    new TreeMap<>(Map.of(
                            30, 100,
                            60, 80,
                            90, 60,
                            120, 40,
                            150, 20
                    ));
            case 7 ->
                //reversed pick situation
                    new TreeMap<>(Map.of(
                            50, 40,
                            60, 20,
                            150, 40
                    ));
            default -> null;
        };


    }
}
