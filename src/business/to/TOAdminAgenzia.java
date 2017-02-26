package business.to;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by salvatore on 13/10/15.
 */
public class TOAdminAgenzia implements TransferObject {

    private String id;
    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    private String citta;
    private String indirizzo;
    private String codiceFiscale;
    private String agenzia;

    public TOAdminAgenzia() {

    }

    public TOAdminAgenzia(TOAdminAgenzia to) {
        id = to.getId();

        setNome(to.getNome());
        setCognome(to.getCognome());
        setEmail(to.getEmail());
        setTelefono(to.getTelefono());
        setCitta(to.getCitta());
        setIndirizzo(to.getIndirizzo());
        setCodiceFiscale(to.getCodiceFiscale());
        agenzia = to.getAgenzia();
    }

    @Override
    public List<Object> getAttributes() {
        List<Object> allAttributes = new LinkedList<>();

        allAttributes.add(getNome());
        allAttributes.add(getCognome());
        allAttributes.add(getEmail());
        allAttributes.add(getTelefono());
        allAttributes.add(getCitta());
        allAttributes.add(getIndirizzo());
        allAttributes.add(getCodiceFiscale());
        allAttributes.add(agenzia);

        return allAttributes;
    }

    @Override
    public TransferObject getData() {
        return new TOAdminAgenzia(this);
    }

    @Override
    public String getId() {
        return id;
    }

    public String getAgenzia() {
        return agenzia;
    }

    public void setAgenzia(String agenzia) {
        this.agenzia = agenzia;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

}
