package edu.upc.dsa.Controller.GameDB.Repository;

import edu.upc.dsa.Controller.GameDB.DAO.DAOImpl;
import edu.upc.dsa.ExceptionHandler.DAOChestException;
import edu.upc.dsa.ExceptionHandler.DAOException;
import edu.upc.dsa.Model.Main.Chest;

public abstract class DAOChestImpl {
    public Chest insertChest(Chest c) throws DAOChestException {
        try{
            return (Chest) DAOImpl.getInstance().insert(c);
        }
        catch (DAOException e) {
            throw new DAOChestException(e);
        }
    }
}
