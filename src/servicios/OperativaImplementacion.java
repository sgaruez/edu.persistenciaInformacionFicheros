package servicios;

import controladores.Inicio;
import dtos.PropietarioDto;
import dtos.VehiculoDto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

            if (archivo.equals("matricula:fchMatriculacion:dni:fchCompra")){
                continue;
            }
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
    public void mostarNHistoricos() {
        int nHistoricos = 0;
        if (!Inicio.listaPropietario.isEmpty()){
            for (PropietarioDto propietario : Inicio.listaPropietario){
                if (propietario.isEsHistorico()){
                    nHistoricos++;
                }
            }
        }
        System.out.println("N. Historicos:".concat(String.valueOf(nHistoricos)));
    }

    @Override
    public void altaNuevoPropietario() {
        PropietarioDto nuevoPropietarioDto = new PropietarioDto();

        String dni;

        dni = solicitarDni();
        idPropietario = 1 + idPropietario;

        nuevoPropietarioDto.setId(idPropietario);
        nuevoPropietarioDto.setDni(dni);

        Inicio.listaPropietario.add(nuevoPropietarioDto);

        for (PropietarioDto propietario : Inicio.listaPropietario){
            if (propietario.getId() == idPropietario){
                System.out.println(propietario.toString(true));
            }
        }
    }

    private String solicitarDni() {
        String dni;
        boolean esCorrecto = false;

        do {
            System.out.println("Introduzca su DNI: ");
            dni = Inicio.scanner.next();
            if (dni.length() == 9){
                esCorrecto = true;
            }else {
                esCorrecto = false;
                System.err.println("DNI erróneo.");
            }
        }while (!esCorrecto);

        return dni;
    }


    @Override
    public void DtoAFichero() {

        Path rutaArchivo = Paths.get("datosIniciales.txt");

        try (BufferedWriter bw = Files.newBufferedWriter(
                rutaArchivo,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING)) {
            String matricula;
            String fchMatriculacion = "";
            String dni;
            String fchCompra;
            String linea;

            linea = "matricula:fchMatriculacion:dni:fchCompra";
            bw.write(linea);
            bw.newLine();

            for (PropietarioDto propietario : Inicio.listaPropietario){
                dni = propietario.getDni();
                fchCompra = propietario.getFchCompra().format(Inicio.formatoFecha);
                matricula = propietario.getMatricula();
                for (VehiculoDto vehiculoDto : Inicio.listaVehiculo){
                    if (vehiculoDto.getMatricula().equals(matricula)){
                        fchMatriculacion = vehiculoDto.getFchMatriculacion().format(Inicio.formatoFecha);
                        break;
                    }
                }
                linea = matricula.concat(":").concat(fchMatriculacion).concat(":").concat(dni).concat(":").concat(fchCompra);
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo.");
        }
    }

}
