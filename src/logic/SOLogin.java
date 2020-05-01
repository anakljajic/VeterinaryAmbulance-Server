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
public class SOLogin extends SystemOperation {

    private String username;
    private String password;

    public SOLogin(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    @Override
    protected void operation() throws Exception {
        odo = dbbr.login(username, password);
    }

}
