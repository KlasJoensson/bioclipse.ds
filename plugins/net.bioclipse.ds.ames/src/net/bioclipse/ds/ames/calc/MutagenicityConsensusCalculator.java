package net.bioclipse.ds.ames.calc;

import net.bioclipse.ds.prop.calc.DSConsensusCalculator;

/**
 * 
 * @author ola
 *
 */
public class MutagenicityConsensusCalculator extends DSConsensusCalculator {
    
    

    @Override
    public String getPropertyName() {
        return "Mutagenicity Consensus";
    }
    
    @Override
    protected String getTestID() {
        return "ames.consensus";
    }
    
    protected String getEndpoint(){
        return "net.bioclipse.ds.mutagenicity";
    }


}