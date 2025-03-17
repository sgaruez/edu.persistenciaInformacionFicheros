package servicios;

import controladores.Inicio;

public class MenuImplementacion implements MenuInterfaz {
    @Override
    public void mostrarMenu() {
        System.out.println("------------ MENÚ ------------" +
                "\n 1. Cerrar aplicación" +
                "\n 2. Número de históricos " +
                "\n 3. Alta nuevo propietario" +
                "\n------------------------------");
    }

    @Override
    public byte seleccionarOpcion() {
        byte opcionUsuario;
        System.out.println("Introduzca una opción por favor:");
        opcionUsuario = Inicio.scanner.nextByte();
        return opcionUsuario;
    }
}
