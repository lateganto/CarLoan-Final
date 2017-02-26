package business.to;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by salvatore on 13/10/15.
 */
public class TODitta implements TransferObject {

    private String partitaIva;
    private String ragioneSociale;
    private String citta;
    private String indirizzo;
    private String sitoWeb;
    private String email;

    public TODitta() {

    }

    public TODitta(TODitta to) {
        partitaIva = to.getPartitaIva();
        ragioneSociale = to.getRagioneSociale();
        citta = to.getCitta();
        indirizzo = to.getIndirizzo();
        sitoWeb = to.getSitoWeb();
        email = to.getEmail();
    }

    @Override
    public List<Object> getAttributes() {
        List<Object> allAttributes = new LinkedList<>();

        allAttributes.add(ragioneSociale);
        allAttributes.add(citta);
        allAttributes.add(indirizzo);
        allAttributes.add(sitoWeb);
        allAttributes.add(email);

        return allAttributes;
    }

    @Override
    public String getId() {
        return partitaIva;
    }

    @Override
    public void setId(String id) {
        partitaIva = id;
    }

    @Override
    public TransferObject getData() {
        return new TODitta(this);
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getSitoWeb() {
        return sitoWeb;
    }

    public void setSitoWeb(String sitoWeb) {
        this.sitoWeb = sitoWeb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
