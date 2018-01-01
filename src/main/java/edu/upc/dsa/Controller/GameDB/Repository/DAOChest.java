package edu.upc.dsa.Controller.GameDB.Repository;

import edu.upc.dsa.ExceptionHandler.DAOChestException;
import edu.upc.dsa.Model.Main.Chest;


public interface DAOChest {
    Chest insertChest(Chest c) throws DAOChestException;
}
