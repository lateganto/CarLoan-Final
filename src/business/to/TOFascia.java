package business.to;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by salvatore on 13/10/15.
 */
public class TOFascia implements TransferObject {

    private String nome;
    private double prezzoKm;
    private double prezzoGg;
    private String descrizione;


    public TOFascia() {

    }

    public TOFascia(TOFascia to) {
        nome = to.getNome();
        prezzoKm = to.getPrezzoKm();
        prezzoGg = to.getPrezzoGg();
        descrizione = to.getDescrizione();
    }

    @Override
    public List<Object> getAttributes() {
        List<Object> allAttributes = new LinkedList<>();

        allAttributes.add(prezzoKm);
        allAttributes.add(prezzoGg);
        allAttributes.add(descrizione);

        return allAttributes;
    }

    @Override
    public String getId() {
        return nome;
    }

    @Override
    public void setId(String id) {
        nome = id;
    }

    @Override
    public TransferObject getData() {
        return new TOFascia(this);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzoKm() {
        return prezzoKm;
    }

    public void setPrezzoKm(double prezzoKm) {
        this.prezzoKm = prezzoKm;
    }

    public double getPrezzoGg() {
        return prezzoGg;
    }

    public void setPrezzoGg(double prezzoGg) {
        this.prezzoGg = prezzoGg;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
