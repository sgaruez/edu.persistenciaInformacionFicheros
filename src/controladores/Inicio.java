package controladores;

import dtos.VehiculoDto;
import dtos.PropietarioDto;
import servicios.MenuImplementacion;
import servicios.MenuInterfaz;
import servicios.OperativaImplementacion;
import servicios.OperativaInterfaz;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inicio {

    public static Scanner scanner = new Scanner(System.in);
    public static List<VehiculoDto> listaVehiculo = new ArrayList<VehiculoDto>();
    public static List<PropietarioDto> listaPropietario = new ArrayList<PropietarioDto>();
    public static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main(String[] args) {

        boolean esCerrado = false;
        byte opcionUsuario;

        try {

            MenuInterfaz menu = new MenuImplementacion();
            OperativaInterfaz operativa = new OperativaImplementacion();

            operativa.ficheroADto();1

            do {
                menu.mostrarMenu();
                opcionUsuario = menu.seleccionarOpcion();

                switch (opcionUsuario) {
                    case 1:
                        esCerrado = true;
                        break;
                    case 2:
                        operativa.mostarNHistoricos();
                        break;
                    case 3:
                        operativa.altaNuevoPropietario();
                        break;
                    default:
                        System.err.println("La opción elegida no es correcta.");
                        break;
                }
            } while (!esCerrado);

            System.out.println("Cerrando menú.");
            scanner.close();
            operativa.DtoAFichero();

        } catch (Exception e){
            System.err.println("Se ha producido una excepción.");
            e.printStackTrace();
        }
    }

}
