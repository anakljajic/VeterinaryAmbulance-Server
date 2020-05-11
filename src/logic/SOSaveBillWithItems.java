/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Racun;

/**
 *
 * @author anakl
 */
public class SOSaveBillWithItems extends SystemOperation{
    
    private Racun racun;
     public SOSaveBillWithItems(Racun racun) {
        super();
        this.racun = racun;

    }

    @Override
    protected void operation() throws Exception {
        racun = dbbr.saveBillWithItems(racun);
    }
    
   
    
}
