package edu.upc.dsa.Model.Relation;

public class UserItem {

    private int id;
    private int userId;
    private int itemId;

    public UserItem() {}

    public UserItem(int userId, int itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }


}
