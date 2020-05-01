/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.Klijent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anakl
 */
public class SOSelectAllClients extends SystemOperation {

    public SOSelectAllClients() {

        super();

    }

    @Override
    protected void operation() throws Exception {
        listOdo = dbbr.selectAllClients();
    }

}
