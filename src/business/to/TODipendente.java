package business.to;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by salvatore on 13/10/15.
 */
public class TODipendente implements TransferObject {

    private String id;
    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    private String citta;
    private String indirizzo;
    private String codiceFiscale;
    private String agenzia;

    public TODipendente() {

    }

    public TODipendente(TODipendente to) {
        id = to.getId();

        nome = to.getNome();
        cognome = to.getCognome();
        email = to.getEmail();
        telefono = to.getTelefono();
        citta = to.getCitta();
        indirizzo = to.getIndirizzo();
        codiceFiscale = to.getCodiceFiscale();
        agenzia = to.getAgenzia();
    }

    @Override
    public List<Object> getAttributes() {
        List<Object> allAttributes = new LinkedList<>();

        allAttributes.add(nome);
        allAttributes.add(cognome);
        allAttributes.add(email);
        allAttributes.add(telefono);
        allAttributes.add(citta);
        allAttributes.add(indirizzo);
        allAttributes.add(codiceFiscale);
        allAttributes.add(agenzia);

        return allAttributes;
    }

    @Override
    public TransferObject getData() {
        return new TODipendente(this);
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
