package dad.preparacionexamendad;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Persona {
    StringProperty nombre;
    IntegerProperty telefono;

    public Persona(StringProperty nombre, IntegerProperty telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public int getTelefono() {
        return telefono.get();
    }

    public IntegerProperty telefonoProperty() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono.set(telefono);
    }
}
