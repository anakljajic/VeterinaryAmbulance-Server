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
public class SOSelectAllBillsFromDate extends SystemOperation {

    private String date;

    public SOSelectAllBillsFromDate(String date) {
        this.date = date;
    }

    @Override
    protected void operation() throws Exception {
        listOdo = dbbr.selectAllBillsFromDate(date);
    }

}
