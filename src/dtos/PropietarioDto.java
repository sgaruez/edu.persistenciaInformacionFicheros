package dtos;

import java.time.LocalDate;

public class PropietarioDto {

    //Atributos
    private long id = 0;
    private String dni;
    private LocalDate fchCompra;
    private String matricula;
    private boolean esHistorico;

    //Constructor
    public PropietarioDto(long id, String dni, LocalDate fchCompra, String matricula, boolean esHistorico) {
        this.id = id;
        this.dni = dni;
        this.fchCompra = fchCompra;
        this.matricula = matricula;
        this.esHistorico = esHistorico;
    }
    public PropietarioDto(){
        super();
    }

    //Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFchCompra() {
        return fchCompra;
    }

    public void setFchCompra(LocalDate fchCompra) {
        this.fchCompra = fchCompra;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public boolean isEsHistorico() {
        return esHistorico;
    }

    public void setEsHistorico(boolean esHistorico) {
        this.esHistorico = esHistorico;
    }

    //toString
    @Override
    public String toString() {
        return " Id: " + id +
                "\n DNI: " + dni +
                "\n Fecha de compra: " + fchCompra +
                "\n Matrícula: " + matricula +
                "\n Es histórico: " + (esHistorico ? "sí" : "no") +
                "\n %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
    }
}
