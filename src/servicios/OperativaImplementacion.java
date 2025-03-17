package servicios;

import controladores.Inicio;
import dtos.PropietarioDto;
import dtos.VehiculoDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OperativaImplementacion implements OperativaInterfaz{

    private long idPropietario = 0;
    private long idVehiculo = 0;

    @Override
    public void ficheroADto() {
        Path rutaArchivo = Paths.get("datosIniciales.txt");
        List<String> registrosArchivo = new ArrayList<>();
        try {
            registrosArchivo = Files.readAllLines(rutaArchivo);
        }catch (IOException e) {
            System.err.println("Error al leer el archivo.");
        }
        for (String archivo : registrosArchivo){
            String matricula;
            String dni;
            LocalDate fchMatriculacion;
            LocalDate fchCompra;
            boolean esHistorico = false;
            String[] lineaArchivo = archivo.split(":");

            matricula = lineaArchivo [0];
            fchMatriculacion = LocalDate.parse(lineaArchivo[1], Inicio.formatoFecha);
            dni = lineaArchivo[2];
            fchCompra = LocalDate.parse(lineaArchivo[3], Inicio.formatoFecha);

            VehiculoDto nuevoVehiculoDto = new VehiculoDto();
            idVehiculo = 1 + idVehiculo;
            nuevoVehiculoDto.setId(idVehiculo);
            nuevoVehiculoDto.setMatricula(matricula);
            nuevoVehiculoDto.setFchMatriculacion(fchMatriculacion);
            Inicio.listaVehiculo.add(nuevoVehiculoDto);

            idPropietario = 1 + idPropietario;
            if (!LocalDate.now().isBefore(fchMatriculacion.plusYears(25))) {
                esHistorico = true;
            }
            PropietarioDto nuevoPropietarioDto = new PropietarioDto(idPropietario, dni, fchCompra, matricula, esHistorico);
            Inicio.listaPropietario.add(nuevoPropietarioDto);
        }
    }

    @Override
    public void mostrarDatos() {
        for (VehiculoDto vehiculo : Inicio.listaVehiculo){
            System.out.println("---- VEHICULO ----");
            System.out.println(vehiculo);
        }
        for (PropietarioDto propietario :Inicio.listaPropietario){
            System.out.println("---- PROPIETARIO ----");
            System.out.println(propietario);
        }
    }
}
