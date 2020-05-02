/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import controller.Controller;
import domain.DomainObject;
import domain.Karton;
import domain.Klijent;
import domain.PoreskaStopa;
import domain.PredmetProdaje;
import domain.Racun;
import domain.Radnik;
import domain.StavkaRacuna;
import domain.Zivotinja;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student1
 */
public class DatabaseBroker {

    Connection connection;

    public void connect() throws Exception {
        try {
            FileInputStream in = new FileInputStream("db.properties");
            Properties props = new Properties();
            props.load(in);

            String driver = props.getProperty("default_driver");
            String url = props.getProperty("default_url");
            String user = props.getProperty("default_user");
            String password = props.getProperty("default_password");

            if (!Controller.getInstance().isDefaultConfiguration()) {

                driver = props.getProperty("driver");
                url = props.getProperty("url");
                user = props.getProperty("user");
                password = props.getProperty("password");

            }

            Class.forName(driver);

            System.out.println(url + " , " + user + " , " + password);
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            System.out.println("Uspesno uspostavljena konekcija sa bazom!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska prilikom uspostavljanja konekcije sa bazom!\n" + ex.getMessage());
        }
    }

    public void disconnect() throws Exception {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Greska prilikom raskidanja konekcije sa bazom!\n" + ex.getMessage());
            }
        }
    }

    public void commit() throws Exception {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Greska prilikom potvrdjivanja transakcije!\n" + ex.getMessage());
            }
        }
    }

    public void rollback() throws Exception {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Greska prilikom ponistavanja transakcije!\n" + ex.getMessage());
            }
        }
    }

    public List<DomainObject> selectAllWorkers() throws Exception {
        try {
            String upit = "SELECT * from radnik";
            System.out.println(upit);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            List<DomainObject> radnici = new ArrayList<>();

            while (rs.next()) {
                Radnik r = new Radnik();
                r.setIme(rs.getString("Ime"));
                r.setPrezime(rs.getString("Prezime"));
                r.setDatumRodjenja(new Date(rs.getDate("DatumRodjenja").getTime()));
                r.setAdresa(rs.getString("Adresa"));
                r.setTelefon(rs.getString("Telefon"));
                r.setAdministrator(rs.getInt("Administrator"));
                r.setKorisnikoIme(rs.getString("KorisnickoIme"));
                r.setLozinka(rs.getString("Lozinka"));
                r.setRadnikID(rs.getLong("RadnikID"));
                radnici.add(r);
            }
//            System.out.println("Uspesno kreiran " + odo.getTableName() + " u bazi!");
            return radnici;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }

    }

    public List<DomainObject> searchWorkerWithCriteria(String criteria) throws Exception {
        try {
            String upit = "SELECT * FROM radnik WHERE Ime LIKE '%" + criteria + "%' OR Prezime LIKE '%" + criteria + "%' ";

            System.out.println(upit);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            List<DomainObject> radnici = new ArrayList<>();
            DomainObject odo = new Radnik();
            while (rs.next()) {
                Radnik r = new Radnik();
                r.setIme(rs.getString("Ime"));
                r.setPrezime(rs.getString("Prezime"));
                r.setDatumRodjenja(new Date(rs.getDate("DatumRodjenja").getTime()));
                r.setAdresa(rs.getString("Adresa"));
                r.setTelefon(rs.getString("Telefon"));
                r.setAdministrator(rs.getInt("Administrator"));
                r.setKorisnikoIme(rs.getString("KorisnickoIme"));
                r.setLozinka(rs.getString("Lozinka"));
                r.setRadnikID(rs.getLong("RadnikID"));
                radnici.add(r);
            }
            System.out.println("Uspesno kreiran " + odo.getTableName() + " u bazi!");
            return radnici;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }

    public List<DomainObject> selectAllClients() throws Exception {
        try {
            String upit = "SELECT * from klijent";
            System.out.println(upit);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            List<DomainObject> klijenti = new ArrayList<>();

            while (rs.next()) {
                Klijent k = new Klijent();
                k.setAdresa(rs.getString("Adresa"));
                k.setMesto(rs.getString("Mesto"));
                k.setTelefon(rs.getString("Telefon"));
                k.setIme(rs.getString("Ime"));
                k.setPrezime(rs.getString("Prezime"));
                k.setKlijentID(rs.getLong("KlijentID"));
                k.setDatumRodjenja(new Date(rs.getDate("DatumRodjenja").getTime()));
                klijenti.add(k);
            }
//            System.out.println("Uspesno kreiran " + odo.getTableName() + " u bazi!");
            return klijenti;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }

    public List<DomainObject> searchClientsWithCriteria(String criteria) throws Exception {
        try {
            String upit = "SELECT * from klijent WHERE Ime LIKE '%" + criteria + "%' OR Prezime LIKE '%" + criteria + "%'";
            System.out.println(upit);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            List<DomainObject> klijentiFilter = new ArrayList<>();

            while (rs.next()) {
                Klijent k = new Klijent();
                k.setAdresa(rs.getString("Adresa"));
                k.setMesto(rs.getString("Mesto"));
                k.setTelefon(rs.getString("Telefon"));
                k.setIme(rs.getString("Ime"));
                k.setPrezime(rs.getString("Prezime"));
                k.setKlijentID(rs.getLong("KlijentID"));
                k.setDatumRodjenja(new Date(rs.getDate("DatumRodjenja").getTime()));
                klijentiFilter.add(k);
            }
//            System.out.println("Uspesno kreiran " + odo.getTableName() + " u bazi!");
            return klijentiFilter;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }

    public List<DomainObject> searchClientsPet(Long id) throws Exception {
        try {
            String upit = "SELECT k.KlijentID, k.Ime, k.Prezime, k.DatumRodjenja, k.Adresa, k.Telefon, k.Mesto, z.ZivotinjaID, z.Ime, z.Rasa, z.Boja, z.Pol, z.DatumRodjenja, z.KlijentID, z.Vrsta FROM klijent k JOIN zivotinja z ON (k.KlijentID=z.KlijentID) WHERE z.KlijentID =" + id + "";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            List<DomainObject> zivotinje = new ArrayList<>();

            while (rs.next()) {
                Zivotinja z = new Zivotinja();
                Klijent k = new Klijent();

                k.setKlijentID(rs.getLong("k.KlijentID"));
                k.setIme(rs.getString("k.Ime"));
                k.setPrezime(rs.getString("k.Prezime"));
                k.setDatumRodjenja(new Date(rs.getDate("k.DatumRodjenja").getTime()));
                k.setAdresa(rs.getString("k.Adresa"));
                k.setTelefon(rs.getString("k.Telefon"));
                k.setMesto(rs.getString("k.Mesto"));

                z.setKlijent(k);
                z.setZivotinjaID(rs.getLong("z.ZivotinjaID"));
                z.setIme(rs.getString("z.Ime"));
                z.setBoja(rs.getString("z.Boja"));
                z.setRasa(rs.getString("z.Rasa"));
                z.setPol(rs.getString("z.Pol"));
                z.setVrsta(rs.getString("z.Vrsta"));
                z.setDatumRodjenja(new Date(rs.getDate("z.DatumRodjenja").getTime()));
                zivotinje.add(z);
            }
            return zivotinje;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }

    public List<DomainObject> selectAllMedicalRecords() throws Exception {
        try {
            String upit = "SELECT r.RadnikID, r.Ime, r.Prezime, r.DatumRodjenja, r.Adresa, r.Telefon, r.Administrator, r.KorisnickoIme, r.Lozinka, z.ZivotinjaID, z.Ime, z.Rasa, z.Boja, z.Pol, z.DatumRodjenja, z.KlijentID, z.Vrsta, k.KlijentID, k.Ime, k.Prezime, k.Adresa, k.DatumRodjenja, k.Telefon, k.Mesto, ka.KartonID, ka.DatumKreiranja, ka.Napomena, ka.RadnikID, ka.ZivotinjaID FROM radnik r JOIN karton ka ON (r.RadnikID=ka.RadnikID) JOIN zivotinja z ON (ka.ZivotinjaID=z.ZivotinjaID) JOIN klijent k ON (z.KlijentID = k.KlijentID)";
            System.out.println(upit);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            List<DomainObject> kartoni = new ArrayList<>();

            while (rs.next()) {
                Radnik r = new Radnik();
                r.setRadnikID(rs.getLong("r.RadnikID"));
                r.setIme(rs.getString("r.Ime"));
                r.setPrezime(rs.getString("r.Prezime"));
                r.setDatumRodjenja(new Date(rs.getDate("r.DatumRodjenja").getTime()));
                r.setAdresa(rs.getString("r.Adresa"));
                r.setTelefon(rs.getString("r.Telefon"));
                r.setAdministrator(rs.getInt("r.Administrator"));
                r.setKorisnikoIme(rs.getString("r.KorisnickoIme"));
                r.setLozinka(rs.getString("r.Lozinka"));

                Klijent k = new Klijent();
                k.setAdresa(rs.getString("k.Adresa"));
                k.setMesto(rs.getString("k.Mesto"));
                k.setTelefon(rs.getString("k.Telefon"));
                k.setIme(rs.getString("k.Ime"));
                k.setPrezime(rs.getString("k.Prezime"));
                k.setKlijentID(rs.getLong("k.KlijentID"));
                k.setDatumRodjenja(new Date(rs.getDate("k.DatumRodjenja").getTime()));

                Zivotinja z = new Zivotinja();
                z.setKlijent(k);
                z.setZivotinjaID(rs.getLong("z.ZivotinjaID"));
                z.setIme(rs.getString("z.Ime"));
                z.setBoja(rs.getString("z.Boja"));
                z.setRasa(rs.getString("z.Rasa"));
                z.setPol(rs.getString("z.Pol"));
                z.setVrsta(rs.getString("z.Vrsta"));
                z.setDatumRodjenja(new Date(rs.getDate("z.DatumRodjenja").getTime()));

                Karton ka = new Karton();
                ka.setKartonID(rs.getLong("ka.KartonID"));
                ka.setDatumKreiranja(new Date(rs.getDate("ka.DatumKreiranja").getTime()));
                ka.setNapomena(rs.getString("ka.Napomena"));
                ka.setRadnik(r);
                ka.setZivotinja(z);

                kartoni.add(ka);

            }
            return kartoni;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }

    public List<DomainObject> selectAllObjectOfSale() throws Exception {
        try {
            String upit = "SELECT pp.PredmetProdajeID, pp.Naziv, pp.CenaBezPoreza, pp.CenaSaPorezom, pp.PoreskaStopaID, p.PoreskaStopaID, p.Oznaka, p.Vrednost FROM predmet_prodaje pp JOIN poreska_stopa p ON (pp.PoreskaStopaID=p.PoreskaStopaID)";
            System.out.println(upit);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            List<DomainObject> predmeti = new ArrayList<>();

            while (rs.next()) {
                PoreskaStopa p = new PoreskaStopa();
                p.setPoreskaStopaID(rs.getLong("p.PoreskaStopaID"));
                p.setOznaka(rs.getString("p.Oznaka"));
                p.setVrednost(rs.getBigDecimal("p.Vrednost"));

                PredmetProdaje pp = new PredmetProdaje();
                pp.setPredmetProdajeID(rs.getLong("pp.PredmetProdajeID"));
                pp.setCenaBezPoreza(rs.getBigDecimal("pp.CenaBezPoreza"));
                pp.setCenaSaPorezom(rs.getBigDecimal("pp.CenaSaPorezom"));
                pp.setNaziv(rs.getString("pp.Naziv"));
                pp.setPoreskaStopa(p);

                predmeti.add(pp);
            }
            return predmeti;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }

    public List<DomainObject> selectAllBills() throws Exception {
        try {
            String upit = "SELECT ra.RacunID, ra.UkupnaCenaSaPorezom, ra.UkupnaCenaBezPoreza, ra.Porez, ra.Obradjen, ra.Storniran, ra.RadnikID, ra.KartonID, ra.DatumKreiranja, r.RadnikID, r.Ime, r.Prezime, r.DatumRodjenja, r.Adresa, r.Telefon, r.Administrator, r.KorisnickoIme, r.Lozinka, ka.KartonID, ka.DatumKreiranja, ka.Napomena, ka.RadnikID, ka.ZivotinjaID, z.ZivotinjaID, z.Ime, z.Rasa, z.Boja, z.Pol, z.DatumRodjenja, z.KlijentID, z.Vrsta, k.KlijentID, k.Ime, k.Prezime, k.DatumRodjenja, k.Adresa, k.Mesto, k.Telefon FROM racun ra JOIN karton ka ON (ra.KartonID=ka.KartonID) JOIN radnik r ON (ra.RadnikID = r.RadnikID) JOIN zivotinja z ON (ka.ZivotinjaID = z.ZivotinjaID) JOIN klijent k ON (z.KlijentID=k.KlijentID)";
            System.out.println(upit);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            List<DomainObject> racuni = new ArrayList<>();

            while (rs.next()) {
                Klijent k = new Klijent();
                k.setAdresa(rs.getString("k.Adresa"));
                k.setMesto(rs.getString("k.Mesto"));
                k.setTelefon(rs.getString("k.Telefon"));
                k.setIme(rs.getString("k.Ime"));
                k.setPrezime(rs.getString("k.Prezime"));
                k.setKlijentID(rs.getLong("k.KlijentID"));
                k.setDatumRodjenja(new Date(rs.getDate("k.DatumRodjenja").getTime()));

                Zivotinja z = new Zivotinja();
                z.setKlijent(k);
                z.setZivotinjaID(rs.getLong("z.ZivotinjaID"));
                z.setIme(rs.getString("z.Ime"));
                z.setBoja(rs.getString("z.Boja"));
                z.setRasa(rs.getString("z.Rasa"));
                z.setPol(rs.getString("z.Pol"));
                z.setVrsta(rs.getString("z.Vrsta"));
                z.setDatumRodjenja(new Date(rs.getDate("z.DatumRodjenja").getTime()));

                Radnik r = new Radnik();
                r.setRadnikID(rs.getLong("r.RadnikID"));
                r.setIme(rs.getString("r.Ime"));
                r.setPrezime(rs.getString("r.Prezime"));
                r.setDatumRodjenja(new Date(rs.getDate("r.DatumRodjenja").getTime()));
                r.setAdresa(rs.getString("r.Adresa"));
                r.setTelefon(rs.getString("r.Telefon"));
                r.setAdministrator(rs.getInt("r.Administrator"));
                r.setKorisnikoIme(rs.getString("r.KorisnickoIme"));
                r.setLozinka(rs.getString("r.Lozinka"));

                Karton ka = new Karton();
                ka.setKartonID(rs.getLong("ka.KartonID"));
                ka.setDatumKreiranja(new Date(rs.getDate("ka.DatumKreiranja").getTime()));
                ka.setNapomena(rs.getString("ka.Napomena"));
                ka.setRadnik(r);
                ka.setZivotinja(z);

                Racun ra = new Racun();
                ra.setRacunID(rs.getLong("ra.RacunID"));
                ra.setDatumKreiranja(new Date(rs.getDate("ra.DatumKreiranja").getTime()));
                ra.setKarton(ka);
                ra.setObradjen(rs.getBoolean("ra.Obradjen"));
                ra.setPorez(rs.getBigDecimal("ra.Porez"));
                ra.setStorniran(rs.getBoolean("ra.Storniran"));
                ra.setUkupnaCenaBezPoreza(rs.getBigDecimal("ra.UkupnaCenaBezPoreza"));
                ra.setUkupnaCenaSaPorezom(rs.getBigDecimal("ra.UkupnaCenaSaPorezom"));
                ra.setRadnik(r);
                racuni.add(ra);

            }
            return racuni;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }

    public List<DomainObject> selectAllTaxes() throws Exception {
        try {
            String query = "SELECT * FROM poreska_stopa";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            List<DomainObject> poreskeStope = new ArrayList<>();

            while (rs.next()) {
                PoreskaStopa ps = new PoreskaStopa();

                ps.setPoreskaStopaID(rs.getLong("PoreskaStopaID"));
                ps.setOznaka(rs.getString("Oznaka"));
                ps.setVrednost(rs.getBigDecimal("Vrednost"));
                System.out.println(ps.getVrednost() + "");

                poreskeStope.add(ps);
            }

            return poreskeStope;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    public DomainObject login(String korisnickoIme, String lozinka) throws Exception {
        try {
            DomainObject odo = new Radnik();
            String upit = "SELECT * FROM " + odo.getTableName() + " WHERE KorisnickoIme = ? AND Lozinka = ?";
            System.out.println(upit);

            PreparedStatement statement = connection.prepareStatement(upit);
            statement.setString(1, korisnickoIme);
            statement.setString(2, lozinka);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                Radnik r = new Radnik();
                r.setRadnikID(rs.getLong("RadnikID"));
                r.setAdministrator(rs.getInt("Administrator"));
                r.setAdresa(rs.getString("Adresa"));
                r.setDatumRodjenja(new Date(rs.getDate("DatumRodjenja").getTime()));
                r.setIme(rs.getString("Ime"));
                r.setPrezime(rs.getString("Prezime"));
                r.setTelefon(rs.getString("Telefon"));
                r.setKorisnikoIme(rs.getString("KorisnickoIme"));
                r.setLozinka(rs.getString("Lozinka"));
                odo = r;

            }
            return odo;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }

    }

    public DomainObject updateDomainObject(DomainObject odo) throws Exception {
        try {
            String query = "UPDATE " + odo.getTableName() + " SET " + odo.getAttributeNamesForUpdate() + " WHERE " + odo.getObjectIDName() + " = " + odo.getObjectIDValue() + " ";
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
        return odo;
    }

    public void insertListDomainObject(List<DomainObject> listOdo) throws Exception {
        for (DomainObject odo : listOdo) {
            try {
                String upit = "INSERT INTO " + odo.getTableName() + " (" + odo.getAttributeNamesForInsert() + ") VALUES (" + odo.getAttributeValuesForInsert() + ")";
                System.out.println(upit);
                Statement statement = connection.createStatement();
                statement.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);

                if (odo.isAutoincrement()) {
                    ResultSet rs = statement.getGeneratedKeys();
                    if (rs.next()) {
                        odo.setObjectId(rs.getLong(1));
                    }
                }

                System.out.println("Uspesno kreiran " + odo.getTableName() + " u bazi!");
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception();
            }

        }
    }

    public DomainObject generateDomainObject(DomainObject odo) throws Exception {
        try {
            String query = "INSERT INTO " + odo.getTableName() + " () VALUES ()";
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

//            ResultSet rs = statement.getGeneratedKeys();
            if (odo.isAutoincrement()) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    odo.setObjectId(rs.getLong(1));
                }
            }
            System.out.println(odo.getObjectIDValue());
            return odo;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getLocalizedMessage() + "Greska prilikom kreiranja " + odo.getTableName() + " u bazi!\n");
        }
    }

    public DomainObject deleteDomainObject(DomainObject odo) throws Exception {
        try {
            String query = "DELETE FROM " + odo.getTableName() + " WHERE "
                    + odo.getObjectIDName() + " = " + odo.getObjectIDValue();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            return odo;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    public DomainObject insertDomainObject(DomainObject odo) throws Exception {
        try {
            String upit = "INSERT INTO " + odo.getTableName() + " (" + odo.getAttributeNamesForInsert() + ") VALUES (" + odo.getAttributeValuesForInsert() + ")";
            System.out.println(upit);
            Statement statement = connection.createStatement();
            statement.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);

            if (odo.isAutoincrement()) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    odo.setObjectId(rs.getLong(1));
                }
            }

            System.out.println("Uspesno kreiran " + odo.getTableName() + " u bazi!");

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getLocalizedMessage() + "Greska prilikom kreiranja " + odo.getTableName() + " u bazi!\n");
        }
        return odo;
    }

    public List<DomainObject> searchMedicalRecordsWithCriteria(String criteria) throws Exception {
        try {
            String upit = "SELECT r.RadnikID, r.Ime, r.Prezime, r.DatumRodjenja, r.Adresa, r.Telefon, r.Administrator, r.KorisnickoIme, r.Lozinka, z.ZivotinjaID, z.Ime, z.Rasa, z.Boja, z.Pol, z.DatumRodjenja, z.KlijentID, z.Vrsta, k.KlijentID, k.Ime, k.Prezime, k.Adresa, k.DatumRodjenja, k.Telefon, k.Mesto, ka.KartonID, ka.DatumKreiranja, ka.Napomena, ka.RadnikID, ka.ZivotinjaID FROM radnik r JOIN karton ka ON (r.RadnikID=ka.RadnikID) JOIN zivotinja z ON (ka.ZivotinjaID=z.ZivotinjaID) JOIN klijent k ON (z.KlijentID = k.KlijentID) WHERE z.Ime LIKE '%" + criteria + "%'";
            System.out.println(upit);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            List<DomainObject> kartoni = new ArrayList<>();

            while (rs.next()) {
                Radnik r = new Radnik();
                r.setRadnikID(rs.getLong("r.RadnikID"));
                r.setIme(rs.getString("r.Ime"));
                r.setPrezime(rs.getString("r.Prezime"));
                r.setDatumRodjenja(new Date(rs.getDate("r.DatumRodjenja").getTime()));
                r.setAdresa(rs.getString("r.Adresa"));
                r.setTelefon(rs.getString("r.Telefon"));
                r.setAdministrator(rs.getInt("r.Administrator"));
                r.setKorisnikoIme(rs.getString("r.KorisnickoIme"));
                r.setLozinka(rs.getString("r.Lozinka"));

                Klijent k = new Klijent();
                k.setAdresa(rs.getString("k.Adresa"));
                k.setMesto(rs.getString("k.Mesto"));
                k.setTelefon(rs.getString("k.Telefon"));
                k.setIme(rs.getString("k.Ime"));
                k.setPrezime(rs.getString("k.Prezime"));
                k.setKlijentID(rs.getLong("k.KlijentID"));
                k.setDatumRodjenja(new Date(rs.getDate("k.DatumRodjenja").getTime()));

                Zivotinja z = new Zivotinja();
                z.setKlijent(k);
                z.setZivotinjaID(rs.getLong("z.ZivotinjaID"));
                z.setIme(rs.getString("z.Ime"));
                z.setBoja(rs.getString("z.Boja"));
                z.setRasa(rs.getString("z.Rasa"));
                z.setPol(rs.getString("z.Pol"));
                z.setVrsta(rs.getString("z.Vrsta"));
                z.setDatumRodjenja(new Date(rs.getDate("z.DatumRodjenja").getTime()));

                Karton ka = new Karton();
                ka.setKartonID(rs.getLong("ka.KartonID"));
                ka.setDatumKreiranja(new Date(rs.getDate("ka.DatumKreiranja").getTime()));
                ka.setNapomena(rs.getString("ka.Napomena"));
                ka.setRadnik(r);
                ka.setZivotinja(z);

                kartoni.add(ka);

            }
            return kartoni;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }

    public List<DomainObject> searchPetsWithCriteria(String criteria) throws Exception {
        try {
            String upit = "SELECT k.KlijentID, k.Ime, k.Prezime, k.DatumRodjenja, k.Adresa, k.Telefon, k.Mesto, z.ZivotinjaID, z.Ime, z.Rasa, z.Boja, z.Pol, z.DatumRodjenja, z.KlijentID, z.Vrsta FROM klijent k JOIN zivotinja z ON (k.KlijentID=z.KlijentID) WHERE z.Ime LIKE '%" + criteria + "%' OR k.Ime LIKE '%" + criteria + "%' OR k.Prezime LIKE '%" + criteria + "%'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            List<DomainObject> zivotinje = new ArrayList<>();

            while (rs.next()) {
                Zivotinja z = new Zivotinja();
                Klijent k = new Klijent();

                k.setKlijentID(rs.getLong("k.KlijentID"));
                k.setIme(rs.getString("k.Ime"));
                k.setPrezime(rs.getString("k.Prezime"));
                k.setDatumRodjenja(new Date(rs.getDate("k.DatumRodjenja").getTime()));
                k.setAdresa(rs.getString("k.Adresa"));
                k.setTelefon(rs.getString("k.Telefon"));
                k.setMesto(rs.getString("k.Mesto"));

                z.setKlijent(k);
                z.setZivotinjaID(rs.getLong("z.ZivotinjaID"));
                z.setIme(rs.getString("z.Ime"));
                z.setBoja(rs.getString("z.Boja"));
                z.setRasa(rs.getString("z.Rasa"));
                z.setPol(rs.getString("z.Pol"));
                z.setVrsta(rs.getString("z.Vrsta"));
                z.setDatumRodjenja(new Date(rs.getDate("z.DatumRodjenja").getTime()));
                zivotinje.add(z);
            }
            return zivotinje;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }

    public List<DomainObject> selectAllPets() throws Exception, Exception {
        try {
            String upit = "SELECT k.KlijentID, k.Ime, k.Prezime, k.DatumRodjenja, k.Adresa, k.Telefon, k.Mesto, z.ZivotinjaID, z.Ime, z.Rasa, z.Boja, z.Pol, z.DatumRodjenja, z.KlijentID, z.Vrsta FROM klijent k JOIN zivotinja z ON (k.KlijentID=z.KlijentID)";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            List<DomainObject> zivotinje = new ArrayList<>();

            while (rs.next()) {
                Zivotinja z = new Zivotinja();
                Klijent k = new Klijent();

                k.setKlijentID(rs.getLong("k.KlijentID"));
                k.setIme(rs.getString("k.Ime"));
                k.setPrezime(rs.getString("k.Prezime"));
                k.setDatumRodjenja(new Date(rs.getDate("k.DatumRodjenja").getTime()));
                k.setAdresa(rs.getString("k.Adresa"));
                k.setTelefon(rs.getString("k.Telefon"));
                k.setMesto(rs.getString("k.Mesto"));

                z.setKlijent(k);
                z.setZivotinjaID(rs.getLong("z.ZivotinjaID"));
                z.setIme(rs.getString("z.Ime"));
                z.setBoja(rs.getString("z.Boja"));
                z.setRasa(rs.getString("z.Rasa"));
                z.setPol(rs.getString("z.Pol"));
                z.setVrsta(rs.getString("z.Vrsta"));
                z.setDatumRodjenja(new Date(rs.getDate("z.DatumRodjenja").getTime()));
                zivotinje.add(z);
            }
            return zivotinje;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }
}
