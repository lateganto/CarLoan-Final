package business.to;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by antonio on 13/10/15.
 */
public class TOFattura implements TransferObject {
    private String numeroFattura;
    private String agenzia;
    private String cliente;
    private double importo;
    private String stato;
    private LocalDate dataEmissione;
    private String descrizione;
    private String contratto;


    public TOFattura() {}

    public TOFattura(TOFattura to) {
        numeroFattura = to.getNumeroFattura();
        agenzia = to.getAgenzia();
        cliente = to.getCliente();
        importo = to.getImporto();
        stato = to.getStato();
        dataEmissione = to.getDataEmissione();
        descrizione = to.getDescrizione();
        contratto = to.getContratto();
    }

    @Override
    public List<Object> getAttributes() {
        List<Object> allAttributes = new LinkedList<>();

        allAttributes.add(agenzia);
        allAttributes.add(cliente);
        allAttributes.add(importo);
        allAttributes.add(stato);
        allAttributes.add(dataEmissione);
        allAttributes.add(descrizione);
        allAttributes.add(contratto);

        return allAttributes;
    }

    @Override
    public TransferObject getData() {
        return new TOFattura(this);
    }

    @Override
    public String getId() {
        return numeroFattura;
    }

    @Override
    public void setId(String numeroFattura) {
        this.numeroFattura = numeroFattura;
    }

    public String getNumeroFattura() {
        return numeroFattura;
    }

    public void setNumeroFattura(String numeroFattura) {
        this.numeroFattura = numeroFattura;
    }

    public String getAgenzia() {
        return agenzia;
    }

    public void setAgenzia(String agenzia) {
        this.agenzia = agenzia;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getContratto() {
        return contratto;
    }

    public void setContratto(String contratto) {
        this.contratto = contratto;
    }

}
