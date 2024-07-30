package org.lrdm.mapekloop;

import org.lrdm.TimedRDMSim;
import org.lrdm.effectors.Action;
import org.lrdm.effectors.MirrorChange;
import org.lrdm.effectors.TargetLinkChange;
import org.lrdm.util.IDGenerator;

import java.util.List;

public class Plan {

    public static int addMirror(int mirrors, boolean add){
        if(add) return mirrors+=1;
        else return mirrors-=1;
    }

    public static int addLinksPerMirror(int lpm, boolean add){
        if(add) return lpm+=1;
        else return lpm-=1;
    }

    public static MirrorChange mirrorAction(TimedRDMSim sim, int mirrors, int iteration){
       return new MirrorChange(sim.getNetwork(), IDGenerator.getInstance().getNextID(), iteration + 1, mirrors);

        //move it to execute
        //return sim.getEffector().setMirrors(mirrors, iteration+1);
    }

    public static TargetLinkChange linksPerMirrorAction(TimedRDMSim sim, int lpm, int iteration){
        return new TargetLinkChange(sim.getNetwork(), IDGenerator.getInstance().getNextID(), iteration + 1, lpm);
       // return sim.getEffector().setTargetLinksPerMirror(lpm, iteration+1);
    }

}
