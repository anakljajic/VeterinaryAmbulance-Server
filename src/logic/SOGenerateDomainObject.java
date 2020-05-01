/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.DomainObject;

/**
 *
 * @author anakl
 */
public class SOGenerateDomainObject extends SystemOperation {

    public SOGenerateDomainObject(DomainObject odo) {
        super();
        this.odo = odo;
    }

    @Override
    protected void operation() throws Exception {
        odo = dbbr.generateDomainObject(odo);
    }

}
