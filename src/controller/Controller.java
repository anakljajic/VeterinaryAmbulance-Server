/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.DomainObject;
import java.util.List;
import logic.*;

/**
 *
 * @author anakl
 */
public class Controller {

    private static Controller instance;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
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

    public void deleteDomainObject(DomainObject odo) throws Exception {
        SystemOperation so = new SODeleteDomainObject(odo);
        so.execute();
    }
}
