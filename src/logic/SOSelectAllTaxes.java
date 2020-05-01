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
public class SOSelectAllTaxes extends SystemOperation {

    public SOSelectAllTaxes() {
        super();

    }

    @Override
    protected void operation() throws Exception {
        listOdo = dbbr.selectAllTaxes();
    }

}
