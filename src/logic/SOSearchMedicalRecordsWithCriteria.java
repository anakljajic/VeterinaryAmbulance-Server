/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author anakl
 */
public class SOSearchMedicalRecordsWithCriteria extends SystemOperation {

    private String criteria;

    public SOSearchMedicalRecordsWithCriteria(String criteria) {
        super();
        this.criteria = criteria;
    }

    @Override
    protected void operation() throws Exception {
        listOdo = dbbr.searchMedicalRecordsWithCriteria(criteria);
    }

}
