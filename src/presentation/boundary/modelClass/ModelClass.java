package presentation.boundary.modelClass;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by salvatore on 25/10/15.
 */
//TODO da fare con interfaccia o estensione di classe
public abstract class ModelClass {

    private SimpleStringProperty id;
    private SimpleStringProperty column1;
    private SimpleStringProperty column2;
    private SimpleStringProperty column3;
    private SimpleStringProperty column4;
    private SimpleStringProperty column5;

    public ModelClass(String id, String cl1, String cl2, String cl3, String cl4, String cl5) {
        this.id = new SimpleStringProperty(id);
        this.column1 = new SimpleStringProperty(cl1);
        this.column2 = new SimpleStringProperty(cl2);
        this.column3 = new SimpleStringProperty(cl3);
        this.column4 = new SimpleStringProperty(cl4);
        this.column5 = new SimpleStringProperty(cl5);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getColumn1() {
        return column1.get();
    }

    public SimpleStringProperty column1Property() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1.set(column1);
    }

    public String getColumn2() {
        return column2.get();
    }

    public SimpleStringProperty column2Property() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2.set(column2);
    }

    public String getColumn3() {
        return column3.get();
    }

    public SimpleStringProperty column3Property() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3.set(column3);
    }

    public String getColumn4() {
        return column4.get();
    }

    public SimpleStringProperty column4Property() {
        return column4;
    }

    public void setColumn4(String column4) {
        this.column4.set(column4);
    }

    public String getColumn5() {
        return column5.get();
    }

    public SimpleStringProperty column5Property() {
        return column5;
    }

    public void setColumn5(String column5) {
        this.column5.set(column5);
    }
}
