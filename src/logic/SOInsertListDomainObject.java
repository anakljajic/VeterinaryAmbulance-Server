/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domain.DomainObject;
import java.util.List;

/**
 *
 * @author anakl
 */
public class SOInsertListDomainObject extends SystemOperation {

    public SOInsertListDomainObject(List<DomainObject> listOdo) {
        super();
        this.listOdo = listOdo;
    }

    @Override
    protected void operation() throws Exception {
        dbbr.insertListDomainObject(listOdo);
    }

}
