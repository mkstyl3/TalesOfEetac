package edu.upc.dsa.Model.Cell;

/**
 * Created by Josean on 07/11/2017.
 */
public class NPC extends Cell {

    final protected String symbol = "N";

    public NPC () {};

    private String dialogue;
    
    public String getDialogue(){
        return dialogue;
    }
    
    public void setDialogue (String dialogue){
        this.dialogue=dialogue;
    }

    @Override
    public String getSymbol () {return this.symbol; }
}
