package business.to;

import java.util.LinkedList;
import java.util.List;

/**
 * JavaDoc
 */
public class TOUtente implements TransferObject {

    private String id;
    private String username;
    private String password;
    private String tipo;


    public TOUtente() {

    }

    public TOUtente(TOUtente to) {
        id = to.getId();
        username = to.getUsername();
        tipo = to.getTipo();
    }

    @Override
    public TransferObject getData() {
        return new TOUtente(this);
    }

    @Override
    public List<Object> getAttributes() {
        List<Object> allAttributes = new LinkedList<>();

        allAttributes.add(username);
        allAttributes.add(password);
        allAttributes.add(tipo);

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
