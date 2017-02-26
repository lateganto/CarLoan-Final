package business.to;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by salvatore on 13/10/15.
 */
public class TOAuto implements TransferObject {

    private String id;
    private String targa;
    private String marca;
    private String modello;
    private int classeEmissioni;
    private Date scadenzaPolizza;
    private Date scadenzaRevisione;
    private String stato;
    private int cilindrata;
    private int numeroPorte;
    private int potenza;
    private String alimentazione;
    private int numeroPosti;
    private String fascia;
    private String agenzia;
    private String note;
    private String cambio;
    private int chilometri;
    private String carburante;

    public TOAuto() {

    }

    public TOAuto(TOAuto to) {
        this.id = to.getId();
        this.targa = to.getTarga();
        this.marca = to.getMarca();
        this.modello = to.getModello();
        this.classeEmissioni = to.getClasseEmissioni();
        this.scadenzaPolizza = to.getScadenzaPolizza();
        this.scadenzaRevisione = to.getScadenzaRevisione();
        this.stato = to.getStato();
        this.cilindrata = to.getCilindrata();
        this.numeroPorte = to.getNumeroPorte();
        this.potenza = to.getPotenza();
        this.alimentazione = to.getAlimentazione();
        this.numeroPosti = to.getNumeroPosti();
        this.fascia = to.getFascia();
        this.agenzia = to.getAgenzia();
        this.note = to.getNote();
        this.cambio = to.getCambio();
        this.chilometri = to.getChilometri();
        this.carburante = to.getCarburante();
    }

    @Override
    public List<Object> getAttributes() {
        List<Object> allAttributes = new LinkedList<>();

        allAttributes.add(targa);
        allAttributes.add(marca);
        allAttributes.add(modello);
        allAttributes.add(classeEmissioni);
        allAttributes.add(scadenzaPolizza);
        allAttributes.add(scadenzaRevisione);
        allAttributes.add(stato);
        allAttributes.add(cilindrata);
        allAttributes.add(numeroPorte);
        allAttributes.add(potenza);
        allAttributes.add(alimentazione);
        allAttributes.add(numeroPosti);
        allAttributes.add(fascia);
        allAttributes.add(agenzia);
        allAttributes.add(note);
        allAttributes.add(cambio);
        allAttributes.add(chilometri);
        allAttributes.add(carburante);

        return allAttributes;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public TransferObject getData() {
        return new TOAuto(this);
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public int getClasseEmissioni() {
        return classeEmissioni;
    }

    public void setClasseEmissioni(int classeEmissioni) {
        this.classeEmissioni = classeEmissioni;
    }

    public Date getScadenzaPolizza() {
        return scadenzaPolizza;
    }

    public void setScadenzaPolizza(Date scadenzaPolizza) {
        this.scadenzaPolizza = scadenzaPolizza;
    }

    public Date getScadenzaRevisione() {
        return scadenzaRevisione;
    }

    public void setScadenzaRevisione(Date scadenzaRevisione) {
        this.scadenzaRevisione = scadenzaRevisione;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getCilindrata() {
        return cilindrata;
    }

    public void setCilindrata(int cilindrata) {
        this.cilindrata = cilindrata;
    }

    public int getNumeroPorte() {
        return numeroPorte;
    }

    public void setNumeroPorte(int numeroPorte) {
        this.numeroPorte = numeroPorte;
    }

    public int getPotenza() {
        return potenza;
    }

    public void setPotenza(int potenza) {
        this.potenza = potenza;
    }

    public String getAlimentazione() {
        return alimentazione;
    }

    public void setAlimentazione(String alimentazione) {
        this.alimentazione = alimentazione;
    }

    public int getNumeroPosti() {
        return numeroPosti;
    }

    public void setNumeroPosti(int numeroPosti) {
        this.numeroPosti = numeroPosti;
    }

    public String getFascia() {
        return fascia;
    }

    public void setFascia(String fascia) {
        this.fascia = fascia;
    }

    public String getAgenzia() {
        return agenzia;
    }

    public void setAgenzia(String agenzia) {
        this.agenzia = agenzia;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    public int getChilometri() {
        return chilometri;
    }

    public void setChilometri(int chilometri) {
        this.chilometri = chilometri;
    }

    public String getCarburante() {
        return carburante;
    }

    public void setCarburante(String carburante) {
        this.carburante = carburante;
    }
}
