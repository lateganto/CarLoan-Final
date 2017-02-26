package business.to;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by salvatore on 13/10/15.
 */
public class TOContratto implements TransferObject {

    private String numeroContratto;
    private String agenzia;
    private String auto;
    private String[] optional;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String cliente;
    private String agenziaRestituzione;
    private String chilometraggio;
    private int chilometriPercorsi;
    private double tariffaBase;
    private double tariffaFinale;
    private String stato;
    private int chilometriPrevisti;

    public TOContratto() {

    }

    public TOContratto(TOContratto to) {
        numeroContratto = to.getId();
        agenzia = to.getAgenzia();
        auto = to.getAuto();
        optional = to.getOptional();
        dataInizio = to.getDataInizio();
        dataFine = to.getDataFine();
        cliente = to.getCliente();
        agenziaRestituzione = to.getAgenziaRestituzione();
        chilometraggio = to.getChilometraggio();
        chilometriPercorsi = to.getChilometriPercorsi();
        tariffaBase = to.getTariffaBase();
        tariffaFinale = to.getTariffaFinale();
        stato = to.getStato();
        chilometriPrevisti = to.getChilometriPrevisti();
    }

    @Override
    public List<Object> getAttributes() {
        List<Object> allAttributes = new LinkedList<>();

        allAttributes.add(agenzia);
        allAttributes.add(auto);
        allAttributes.add(optional);
        allAttributes.add(dataInizio);
        allAttributes.add(dataFine);
        allAttributes.add(cliente);
        allAttributes.add(agenziaRestituzione);
        allAttributes.add(chilometraggio);
        allAttributes.add(chilometriPercorsi);
        allAttributes.add(tariffaBase);
        allAttributes.add(tariffaFinale);
        allAttributes.add(stato);
        allAttributes.add(chilometriPrevisti);

        return allAttributes;
    }

    @Override
    public String getId() {
        return numeroContratto;
    }

    @Override
    public void setId(String id) {
        numeroContratto = id;
    }

    @Override
    public TransferObject getData() {
        return new TOContratto(this);
    }

    public String getNumeroContratto() {
        return numeroContratto;
    }

    public void setNumeroContratto(String numeroContratto) {
        this.numeroContratto = numeroContratto;
    }

    public String getAgenzia() {
        return agenzia;
    }

    public void setAgenzia(String agenzia) {
        this.agenzia = agenzia;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public String[] getOptional() {
        return optional;
    }

    public void setOptional(String[] optional) {
        this.optional = optional;
    }

    public LocalDateTime getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDateTime getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getAgenziaRestituzione() {
        return agenziaRestituzione;
    }

    public void setAgenziaRestituzione(String agenziaRestituzione) {
        this.agenziaRestituzione = agenziaRestituzione;
    }

    public String getChilometraggio() {
        return chilometraggio;
    }

    public void setChilometraggio(String chilometraggio) {
        this.chilometraggio = chilometraggio;
    }

    public int getChilometriPercorsi() {
        return chilometriPercorsi;
    }

    public void setChilometriPercorsi(int chilometriPercorsi) {
        this.chilometriPercorsi = chilometriPercorsi;
    }

    public double getTariffaBase() {
        return tariffaBase;
    }

    public void setTariffaBase(double tariffaBase) {
        this.tariffaBase = tariffaBase;
    }

    public double getTariffaFinale() {
        return tariffaFinale;
    }

    public void setTariffaFinale(double tariffaFinale) {
        this.tariffaFinale = tariffaFinale;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getChilometriPrevisti() {
        return chilometriPrevisti;
    }

    public void setChilometriPrevisti(int chilometriPrevisti) {
        this.chilometriPrevisti = chilometriPrevisti;
    }
}
