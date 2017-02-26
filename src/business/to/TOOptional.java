package business.to;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by antonio on 13/10/15.
 */
public class TOOptional implements TransferObject {
    private String id;
    private String nome;
    private double prezzo;
    private String descrizione;
    private String stato;

    public TOOptional() {}

    public TOOptional(TOOptional to) {
        id = to.getId();
        nome = to.getNome();
        prezzo = to.getPrezzo();
        descrizione = to.getDescrizione();
        stato = to.getStato();
    }

    @Override
    public List<Object> getAttributes() {
        List<Object> allAttributes = new LinkedList<>();

        allAttributes.add(nome);
        allAttributes.add(prezzo);
        allAttributes.add(descrizione);
        allAttributes.add(stato);

        return allAttributes;
    }

    @Override
    public TransferObject getData() {
        return new TOOptional(this);
    }

    @Override
    public String getId() {
        return id;
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

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
}
