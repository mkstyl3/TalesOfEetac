package edu.upc.dsa.Model.Relation;

public class ChestItem {

    private int id;
    private int chestId;
    private int itemId;

    public ChestItem() {}

    public ChestItem(int chestId, int itemId) {
        this.chestId = chestId;
        this.itemId = itemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChestId() {
        return chestId;
    }

    public void setChestId(int chestId) {
        this.chestId = chestId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
