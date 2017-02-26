package business.to;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by antonio on 13/10/15.
 */
public class TOPrenotazione implements TransferObject {

    String numeroPrenotazione;
    String cliente;
    String auto;
    String tipo;
    LocalDate dataPrenotazione;
    LocalDateTime scadenza;
    String agenzia;
    Double acconto;


    public TOPrenotazione() {}

    public TOPrenotazione(TOPrenotazione to) {

        numeroPrenotazione = to.getNumeroPrenotazione();
        cliente = to.getCliente();
        auto = to.getAuto();
        tipo = to.getTipo();
        scadenza = to.getScadenza();
        dataPrenotazione = to.getDataPrenotazione();
        agenzia = to.getAgenzia();
        acconto = to.getAcconto();
    }

    @Override
    public List<Object> getAttributes() {
        List<Object> allAttributes = new LinkedList<>();

        allAttributes.add(cliente);
        allAttributes.add(auto);
        allAttributes.add(tipo);
        allAttributes.add(dataPrenotazione);
        allAttributes.add(scadenza);
        allAttributes.add(agenzia);
        allAttributes.add(acconto);

        return allAttributes;

    }

    @Override
    public TransferObject getData() {
        return new TOPrenotazione(this);
    }

    @Override
    public String getId() {
        return numeroPrenotazione;
    }

    @Override
    public void setId(String numeroPrenotazione) {
        this.numeroPrenotazione = numeroPrenotazione;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNumeroPrenotazione() {
        return numeroPrenotazione;
    }

    public void setNumeroPrenotazione(String numeroPrenotazione) {
        this.numeroPrenotazione = numeroPrenotazione;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(LocalDate dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public LocalDateTime getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDateTime scadenza) {
        this.scadenza = scadenza;
    }

    public String getAgenzia() {
        return agenzia;
    }

    public void setAgenzia(String agenzia) {
        this.agenzia = agenzia;
    }

    public Double getAcconto() {
        return acconto;
    }

    public void setAcconto(Double acconto) {
        this.acconto = acconto;
    }
}
