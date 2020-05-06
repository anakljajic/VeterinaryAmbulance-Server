/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import controller.Controller;
import domain.DomainObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.Operation;

/**
 *
 * @author anakl
 */
public class ClientHandlerThread extends Thread {

    private Socket socket;

    public ClientHandlerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        RequestObject request = null;
        ResponseObject response = null;
        while (!socket.isClosed()) {
            try {
                request = receiveRequest();

                switch (request.getOperation()) {
                    case Operation.OPERATION_LOGIN:
                        response = operationLogin(request);
                        break;
                    case Operation.OPERATION_INSERT_DOMAIN_OBJECT:
                        response = operationInsertDomainObject(request);
                        break;
                    case Operation.OPERATION_INSERT_LIST_DOMAIN_OBJECT:
                        response = operationInsertListDomainObject(request);
                        break;
                    case Operation.OPERATION_GENERATE_DOMAIN_OBJECT:
                        response = operationGenerateDomainObject(request);
                        break;
                    case Operation.OPERATION_UPDATE_DOMAIN_OBJECT:
                        response = operationUpdateDomainObject(request);
                        break;
                    case Operation.OPERATION_SEARCH_CLIENTS_WITH_CRITERIA:
                        response = operationSearchClientsWithCriteria(request);
                        break;
                    case Operation.OPERATION_SELECT_ALL_CLIENTS:
                        response = operationSelectAllClients(request);
                        break;
                    case Operation.OPERATION_SELECT_ALL_MEDICAL_RECORDS:
                        response = operationSelectAllMedicalRecords(request);
                        break;
                    case Operation.OPERATION_SEARCH_MEDICAL_RECORDS_WITH_CRITERIA:
                        response = operationSearchMedicalRecordsWithCriteria(request);
                        break;
                    case Operation.OPERATION_SEARCH_PETS_WITH_CRITERIA:
                        response = operationSearchPetsWithCriteria(request);
                        break;
                    case Operation.OPERATION_SEARCH_CLIENTS_PET:
                        response = operationSearchClientsPet(request);
                        break;
                    case Operation.OPERATION_SELECT_ALL_PETS:
                        response = operationSelectAllPets(request);
                        break;
                    case Operation.OPERATION_SELECT_ALL_WORKERS:
                        response = operationSelectAllWorkers(request);
                        break;
                    case Operation.OPERATION_SEARCH_WORKER_WITH_CRITERIA:
                        response = operationSearchWorkerWithCriteria(request);
                        break;
                    case Operation.OPERATION_SELECT_ALL_OBJECT_OF_SALE:
                        response = operationSelectAllObjectOfSale(request);
                        break;
                    case Operation.OPERATION_SELECT_ALL_BILLS:
                        response = operationSelectAllBills(request);
                        break;
                    case Operation.OPERATION_DELETE_DOMAIN_OBJECT:
                        response = operationDeleteDomainObject(request);
                        break;
                    case Operation.OPERATION_SELECT_ALL_TAXES:
                        response = operationSelectAllTaxes(request);
                        break;
                    case Operation.OPERATION_SET_STORNO_BILL:
                        response = operationSetStornoBill(request);
                        break;
                    case Operation.OPERATION_SEARCH_BILL_WITH_CRITERIA:
                        response = operationSearchBillWithCriteria(request);
                        break;
                }
                sendResponse(response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public RequestObject receiveRequest() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        return (RequestObject) in.readObject();
    }

    public void sendResponse(ResponseObject response) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(response);
        out.flush();
    }

    public ResponseObject operationLogin(RequestObject request) {
        ResponseObject response = null;
        Map<String, String> data = (Map) request.getData();
        String username = data.get("username");
        String password = data.get("password");

        try {
            response = new ResponseObject();
            DomainObject odo = Controller.getInstance().login(username, password);
            response.setData(odo);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    public Socket getSocket() {
        return socket;
    }

    private ResponseObject operationSelectAllClients(RequestObject request) {
        ResponseObject response = null;

        try {
            response = new ResponseObject();
            List<DomainObject> klijenti = Controller.getInstance().selectAllClients();
            response.setData(klijenti);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationSearchClientsWithCriteria(RequestObject request) {
        ResponseObject response = null;
        String criteria = (String) request.getData();

        try {
            response = new ResponseObject();
            List<DomainObject> klijenti = Controller.getInstance().searchClientsWithCriteria(criteria);
            response.setData(klijenti);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationSelectAllMedicalRecords(RequestObject request) {
        ResponseObject response = null;

        try {
            response = new ResponseObject();
            List<DomainObject> kartoni = Controller.getInstance().selectAllMedicalRecords();
            response.setData(kartoni);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationUpdateDomainObject(RequestObject request) {
        ResponseObject response = null;
        DomainObject odo = (DomainObject) request.getData();

        try {
            response = new ResponseObject();
            Controller.getInstance().updateDomainObject(odo);
            response.setData(odo);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationInsertListDomainObject(RequestObject request) {
        ResponseObject response = null;
        List<DomainObject> listOdo = (List<DomainObject>) request.getData();

        try {
            response = new ResponseObject();
            Controller.getInstance().insertListDomainObject(listOdo);
            response.setData(listOdo);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationInsertDomainObject(RequestObject request) {
        ResponseObject response = null;
        DomainObject odo = (DomainObject) request.getData();

        try {
            response = new ResponseObject();
            odo = Controller.getInstance().insertDomainObject(odo);
            response.setData(odo);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationGenerateDomainObject(RequestObject request) {
        ResponseObject response = null;
        DomainObject odo = (DomainObject) request.getData();

        try {
            response = new ResponseObject();
            odo = (DomainObject) Controller.getInstance().generateDomainObject(odo);
            response.setData(odo);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }

        return response;
    }

    private ResponseObject operationSearchMedicalRecordsWithCriteria(RequestObject request) {
        ResponseObject response = null;
        String criteria = (String) request.getData();

        try {
            response = new ResponseObject();
            List<DomainObject> kartoni = Controller.getInstance().searchMedicalRecordsWithCriteria(criteria);
            response.setData(kartoni);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationSearchPetsWithCriteria(RequestObject request) {
        ResponseObject response = null;
        String criteria = (String) request.getData();

        try {
            response = new ResponseObject();
            List<DomainObject> zivotinje = Controller.getInstance().searchPetsWithCriteria(criteria);
            response.setData(zivotinje);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationSearchClientsPet(RequestObject request) {
        ResponseObject response = null;
        Long id = (Long) request.getData();

        try {
            response = new ResponseObject();
            List<DomainObject> zivotinje = Controller.getInstance().searchClientsPet(id);
            response.setData(zivotinje);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationSelectAllPets(RequestObject request) {
        ResponseObject response = null;

        try {
            response = new ResponseObject();
            List<DomainObject> zivotinje = Controller.getInstance().selectAllPets();
            response.setData(zivotinje);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationSearchWorkerWithCriteria(RequestObject request) {
        ResponseObject response = null;
        String criteria = (String) request.getData();
        try {
            response = new ResponseObject();
            List<DomainObject> radnik = Controller.getInstance().searchWorkerWithCriteria(criteria);
            response.setData(radnik);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationSelectAllWorkers(RequestObject request) {
        ResponseObject response = null;

        try {
            response = new ResponseObject();
            List<DomainObject> radnici = Controller.getInstance().selectAllWorkers();
            response.setData(radnici);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationSelectAllObjectOfSale(RequestObject request) {
        ResponseObject response = null;

        try {
            response = new ResponseObject();
            List<DomainObject> objectOfSale = Controller.getInstance().selectAllObjectOfSale();
            response.setData(objectOfSale);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationSelectAllBills(RequestObject request) {
        ResponseObject response = null;

        try {
            response = new ResponseObject();
            List<DomainObject> racuni = Controller.getInstance().selectAllBills();
            response.setData(racuni);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationDeleteDomainObject(RequestObject request) {
        ResponseObject response = null;
        DomainObject odo = (DomainObject) request.getData();

        try {
            response = new ResponseObject();
            Controller.getInstance().deleteDomainObject(odo);
            response.setData(odo);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationSelectAllTaxes(RequestObject request) {
        ResponseObject response = null;

        try {
            response = new ResponseObject();
            List<DomainObject> pdv = Controller.getInstance().selectAllTaxes();
            response.setData(pdv);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationSetStornoBill(RequestObject request) {
        ResponseObject response = null;
        DomainObject odo = (DomainObject) request.getData();

        try {
            response = new ResponseObject();
            Controller.getInstance().setStornoBill(odo);
            response.setData(odo);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

    private ResponseObject operationSearchBillWithCriteria(RequestObject request) {
        ResponseObject response = null;
        String criteria = (String) request.getData();
        try {
            response = new ResponseObject();
            List<DomainObject> racun = Controller.getInstance().selectAllBillsFromDate(criteria);
            response.setData(racun);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setException(ex);
        }
        return response;
    }

}
