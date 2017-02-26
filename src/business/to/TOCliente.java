package business.to;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by salvatore on 13/10/15.
 */
public class TOCliente implements TransferObject{

    private String id;
    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    private String citta;
    private String indirizzo;
    private String codiceFiscale;
    private String numeroDocumento;
    private String tipoDocumento;
    private String numeroPatente;
    private LocalDate dataRilascioPatente;
    private String cartaCredito;
    private String intestatarioCarta;

    public TOCliente() {

    }

    public TOCliente(TOCliente to) {
        id = to.getId();

        setNome(to.getNome());
        setCognome(to.getCognome());
        setEmail(to.getEmail());
        setTelefono(to.getTelefono());
        setCitta(to.getCitta());
        setIndirizzo(to.getIndirizzo());
        setCodiceFiscale(to.getCodiceFiscale());
        numeroDocumento = to.getNumeroDocumento();
        tipoDocumento = to.getTipoDocumento();
        numeroPatente = to.getNumeroPatente();
        dataRilascioPatente = to.getDataRilascioPatente();
        cartaCredito = to.getCartaCredito();
        intestatarioCarta = to.getIntestatarioCarta();
    }

    @Override
    public List<Object> getAttributes() {
        List<Object> allAttributes = new LinkedList<>();

        allAttributes.add(nome);
        allAttributes.add(cognome);
        allAttributes.add(numeroDocumento);
        allAttributes.add(tipoDocumento);
        allAttributes.add(citta);
        allAttributes.add(indirizzo);
        allAttributes.add(telefono);
        allAttributes.add(email);
        allAttributes.add(numeroPatente);
        allAttributes.add(dataRilascioPatente);
        allAttributes.add(codiceFiscale);
        allAttributes.add(cartaCredito);
        allAttributes.add(intestatarioCarta);

        return allAttributes;
    }

    @Override
    public TransferObject getData() {
        return new TOCliente(this);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroPatente() {
        return numeroPatente;
    }

    public void setNumeroPatente(String numeroPatente) {
        this.numeroPatente = numeroPatente;
    }

    public LocalDate getDataRilascioPatente() {
        return dataRilascioPatente;
    }

    public void setDataRilascioPatente(LocalDate dataRilascioPatente) {
        this.dataRilascioPatente = dataRilascioPatente;
    }

    public String getCartaCredito() {
        return cartaCredito;
    }

    public void setCartaCredito(String cartaCredito) {
        this.cartaCredito = cartaCredito;
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

    public String getIntestatarioCarta() {
        return intestatarioCarta;
    }

    public void setIntestatarioCarta(String intestatarioCarta) {
        this.intestatarioCarta = intestatarioCarta;
    }
}
