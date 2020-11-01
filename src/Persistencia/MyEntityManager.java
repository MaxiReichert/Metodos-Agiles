/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Maxi
 */
public class MyEntityManager {
     private static EntityManagerFactory _entityManagerFactory = null;
    
    private static void inicializar(){
        if(_entityManagerFactory==null)
        _entityManagerFactory = Persistence.createEntityManagerFactory("metodos_agiles");
    }

    public static EntityManager get(){
        inicializar();
        return _entityManagerFactory.createEntityManager();
    }
    
    public static void close(){        
        _entityManagerFactory.close();
        _entityManagerFactory=null;
    }
    
    private MyEntityManager(){
        
    }
}
