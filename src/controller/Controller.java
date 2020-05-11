/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.DomainObject;
import domain.Racun;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.swing.JFrame;
import logic.*;

/**
 *
 * @author anakl
 */
public class Controller {

    private static Controller instance;
    private boolean defaultConfiguration;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public boolean getDefaultConfiguration() {
        return defaultConfiguration;
    }

    public void setDefaultConfiguration(boolean defaultConfiguration) {
        this.defaultConfiguration = defaultConfiguration;
    }

    public boolean isDefaultConfiguration() {
        if (defaultConfiguration == true) {
            return true;
        }
        return false;
    }

    public Properties readPropertiesFile() throws IOException {
        FileInputStream in = new FileInputStream("db.properties");
        Properties props = new Properties();
        props.load(in);

        return props;
    }

    public void writeIntoPropertiesFile(String port, String driver, String url, String user, String password) throws IOException {
        FileInputStream in = new FileInputStream("conn.properties");
        Properties props = new Properties();
        props.load(in);

        props.setProperty("port", port);
        props.setProperty("driver", driver);
        props.setProperty("url", url);
        props.setProperty("user", user);
        props.setProperty("password", password);
    }

    public void defaultPrepareForm(JFrame form, Dimension dimension) {
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setPreferredSize(dimension);
        form.pack();
        form.setLocationRelativeTo(null);
//        URL imageUrl = ClassLoader.getSystemResource("img/transportation.png");
//        ImageIcon icon = new ImageIcon(imageUrl);
//        form.setIconImage(icon.getImage());
    }

    public List<DomainObject> selectAllWorkers() throws Exception {
        SystemOperation so = new SOSelectAllWorkers();
        so.execute();
        return so.getListDomainObject();
    }

    public List<DomainObject> searchWorkerWithCriteria(String criteria) throws Exception {
        SystemOperation so = new SOSearchWorkerWithCriteria(criteria);
        so.execute();
        return so.getListDomainObject();
    }

    public List<DomainObject> selectAllMedicalRecords() throws Exception {
        SystemOperation so = new SOSelectAllMedicalRecords();
        so.execute();
        return so.getListDomainObject();
    }

    public List<DomainObject> selectAllTaxes() throws Exception {
        SystemOperation so = new SOSelectAllTaxes();
        so.execute();
        return so.getListDomainObject();
    }

    public List<DomainObject> selectAllClients() throws Exception {
        SystemOperation so = new SOSelectAllClients();
        so.execute();
        return so.getListDomainObject();
    }

    public List<DomainObject> searchClientsWithCriteria(String criteria) throws Exception {
        SystemOperation so = new SOSearchClientsWithCriteria(criteria);
        so.execute();
        return so.getListDomainObject();
    }

    public List<DomainObject> searchMedicalRecordsWithCriteria(String criteria) throws Exception {
        SystemOperation so = new SOSearchMedicalRecordsWithCriteria(criteria);
        so.execute();
        return so.getListDomainObject();
    }

    public DomainObject login(String korisnickoIme, String lozinka) throws Exception {
        SystemOperation so = new SOLogin(korisnickoIme, lozinka);
        so.execute();
        return so.getDomainObject();
    }

    public DomainObject insertDomainObject(DomainObject odo) throws Exception {
        SystemOperation so = new SOInsertDomainObject(odo);
        so.execute();
        return so.getDomainObject();
    }

    public DomainObject updateDomainObject(DomainObject odo) throws Exception {
        SystemOperation so = new SOUpdateDomainObject(odo);
        so.execute();
        return so.getDomainObject();
    }

    public void insertListDomainObject(List<DomainObject> listOdo) throws Exception {
        SystemOperation so = new SOInsertListDomainObject(listOdo);
        so.execute();
    }

    public DomainObject generateDomainObject(DomainObject odo) throws Exception {
        SystemOperation so = new SOGenerateDomainObject(odo);
        so.execute();
        return so.getDomainObject();
    }

    public List<DomainObject> searchPetsWithCriteria(String criteria) throws Exception {
        SystemOperation so = new SOSearchPetsWithCriteria(criteria);
        so.execute();
        return so.getListDomainObject();
    }

    public List<DomainObject> searchClientsPet(Long id) throws Exception {
        SystemOperation so = new SOSearchClientsPet(id);
        so.execute();
        return so.getListDomainObject();
    }

    public List<DomainObject> selectAllPets() throws Exception {
        SystemOperation so = new SOSelectAllPets();
        so.execute();
        return so.getListDomainObject();
    }

    public List<DomainObject> selectAllObjectOfSale() throws Exception {
        SystemOperation so = new SOSelectAllObjectOfSale();
        so.execute();
        return so.getListDomainObject();
    }

    public List<DomainObject> selectAllBills() throws Exception {
        SystemOperation so = new SOSelectAllBills();
        so.execute();
        return so.getListDomainObject();
    }

    public List<DomainObject> selectAllBillsFromDate(String date) throws Exception {
        SystemOperation so = new SOSelectAllBillsFromDate(date);
        so.execute();
        return so.getListDomainObject();
    }

    public void deleteDomainObject(DomainObject odo) throws Exception {
        SystemOperation so = new SODeleteDomainObject(odo);
        so.execute();
    }

    public DomainObject setStornoBill(DomainObject odo) throws Exception {
        SystemOperation so = new SOSetStornoBill(odo);
        so.execute();
        return so.getDomainObject();
    }

    public Racun saveBillWithItems(Racun racun) throws Exception {
        SystemOperation so = new SOSaveBillWithItems(racun);
        so.execute();
        return (Racun) so.getDomainObject();
    }
}
