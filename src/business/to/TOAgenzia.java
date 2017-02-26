package business.to;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by antonio on 13/10/15.
 */
public class TOAgenzia implements TransferObject {

    private String id;
    private String citta;
    private String indirizzo;
    private String email;
    private String telefono;

    public TOAgenzia() {}

    public TOAgenzia(TOAgenzia to) {
        id = to.getId();
        citta = to.getCitta();
        indirizzo = to.getIndirizzo();
        email = to.getEmail();
        telefono = to.getTelefono();
    }

    @Override
    public List<Object> getAttributes() {

        List<Object> allAttributes = new LinkedList<>();

        allAttributes.add(citta);
        allAttributes.add(indirizzo);
        allAttributes.add(email);
        allAttributes.add(telefono);

        return allAttributes;
    }

    @Override
    public TransferObject getData() {
        return new TOAgenzia(this);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
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

}
