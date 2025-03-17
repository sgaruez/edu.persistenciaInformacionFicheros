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

        try (FileWriter fw = new FileWriter("datosIniciales.txt", true);
             PrintWriter datosIniciales = new PrintWriter(fw)) {

            MenuInterfaz menu = new MenuImplementacion();
            OperativaInterfaz operativa = new OperativaImplementacion();

            operativa.ficheroADto();

            do {

                operativa.mostrarDatos();

                menu.mostrarMenu();
                opcionUsuario = menu.seleccionarOpcion();

                switch (opcionUsuario) {
                    case 1:
                        esCerrado = true;
                        break;
                    case 2:
                        System.out.println("Opcion 2");
                        break;
                    case 3:
                        System.out.println("Opcion 3");
                        break;
                    default:
                        System.err.println("La opción elegida no es correcta.");
                        break;
                }
            } while (!esCerrado);

            System.out.println("Cerrando menú.");
            scanner.close();

        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log.");
        }
    }

}
