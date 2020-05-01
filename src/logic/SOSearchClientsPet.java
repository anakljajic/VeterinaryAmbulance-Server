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
public class SOSearchClientsPet extends SystemOperation {

    private Long id;

    public SOSearchClientsPet(Long id) {
        this.id = id;
    }

    @Override
    protected void operation() throws Exception {
        listOdo = dbbr.searchClientsPet(id);
    }

}
