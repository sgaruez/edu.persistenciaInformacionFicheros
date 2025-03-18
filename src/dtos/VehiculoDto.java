package dtos;

import java.time.LocalDate;

public class VehiculoDto {

    //Atributos
    private long id = 0;
    private String matricula = "aaaa";
    private LocalDate fchMatriculacion = LocalDate.MAX;

    //Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getFchMatriculacion() {
        return fchMatriculacion;
    }

    public void setFchMatriculacion(LocalDate fchMatriculacion) {
        this.fchMatriculacion = fchMatriculacion;
    }

    //to-String
    @Override
    public String toString() {
        return " Id: " + id +
                "\n Matrícula: " + matricula +
                "\n Fecha de matriculación: " + fchMatriculacion +
                "\n %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
    }

}
