package org.lrdm.mapekloop;

import org.lrdm.TimedRDMSim;
import org.lrdm.effectors.Action;
import org.lrdm.effectors.MirrorChange;
import org.lrdm.effectors.TargetLinkChange;
import org.lrdm.examples.ExampleMAPEKOptimizer;

import java.util.logging.Logger;

public class Execute {
    //do i need it at all


    public static Action execute(TimedRDMSim sim, Action action, int iteration, boolean toIncrease) {

        //TODO in the future add TopologyChange
        if (action instanceof MirrorChange) {
            if (toIncrease)
                Logger.getLogger(ExampleMAPEKOptimizer.class.getName()).info("\t-> remove mirrors to increase AL%");
            else Logger.getLogger(ExampleMAPEKOptimizer.class.getName()).info("\t-> add mirrors to decrease AL%");
            return sim.getEffector().setSetMirrorChanges(iteration + 1, (MirrorChange) action);
        } else {
            if (toIncrease)
                Logger.getLogger(ExampleMAPEKOptimizer.class.getName()).info("\t-> add lpm to increase AL%");
            else Logger.getLogger(ExampleMAPEKOptimizer.class.getName()).info("\t-> remove lpm to decrease AL%");
            return sim.getEffector().setSetTargetLinksPerMirror(iteration + 1, (TargetLinkChange) action);
        }


        //sim.getEffector().removeAction(action);
    }

//    public static Action removeSomeMirrors(TimedRDMSim sim, Action action, int iteration, int newMirrorsDiff){
//        Logger.getLogger(ExampleMAPEKOptimizer.class.getName()).info("\t-> remove mirrors to increase AL%");
//
//    }
}
