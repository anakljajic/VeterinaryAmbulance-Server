/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import validator.impl.ValidatorRadnik;

/**
 *
 * @author anakl
 */
public class SOSearchWorkerWithCriteria extends SystemOperation {

    private String criteria;

    public SOSearchWorkerWithCriteria(String criteria) {
        this.criteria = criteria;
        validator = new ValidatorRadnik();
    }

    @Override
    protected void operation() throws Exception {
        listOdo = dbbr.searchWorkerWithCriteria(criteria);
    }

}
