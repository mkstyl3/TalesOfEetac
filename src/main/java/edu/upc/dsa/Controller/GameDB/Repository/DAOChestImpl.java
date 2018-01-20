package edu.upc.dsa.Controller.GameDB.Repository;

import edu.upc.dsa.ExceptionHandler.DAOChestException;
import edu.upc.dsa.ExceptionHandler.DAOException;

import edu.upc.dsa.Model.Main.Chest;


import static edu.upc.dsa.Controller.GameDB.DAO.DAOImpl.*;

public abstract class DAOChestImpl {
    public Chest insertChest(Chest c) throws DAOChestException {
        try{
            return (Chest) getInstance().insert(c);
        }
        catch (DAOException e) {
            throw new DAOChestException(e);
        }
    }

    public void deleteChest(Chest chest) throws DAOChestException {
        try {
            getInstance().delete(chest);
        }
        catch (DAOException e) {
            throw new DAOChestException(e);
        }
    }
}
