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
public class SOSelectAllBills extends SystemOperation {

    public SOSelectAllBills() {
        super();
    }

    @Override
    protected void operation() throws Exception {
        listOdo = dbbr.selectAllBills();
    }

}
