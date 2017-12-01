package edu.upc.dsa.Model.Cell;

/**
 * Created by Josean on 07/11/2017.
 */
public class NPC extends Cell {

    final protected String SYMBOL = "N";
    private String dialogue;

    public NPC () {};

    public String getDialogue() {
        return dialogue;
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    @Override
    public String getSYMBOL() {return this.SYMBOL; }
}
