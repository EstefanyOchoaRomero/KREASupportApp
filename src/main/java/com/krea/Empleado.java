package com.krea;

import java.time.LocalDate;
import java.util.Scanner;

public class Empleado {
    public static void main(String[] args) {
        SolicitudCRUD solicitudCRUD = new SolicitudCRUD();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        LocalDate fecha = null;
        LocalDate fechaActual = LocalDate.now();
        String azul = "\033[1;36m";
        String naranja = "\033[0;33m";
        String rojo = "\033[0;31m";
        String verde = "\033[0;32m";
        String amarillo = "\033[0;33m";
        String reset = "\033[0m";

        while (running) {
            System.out.println("                                           ");
            System.out.println("                                           ");
            System.out.println("=============");
            System.out.println(azul + "MENÚ EMPLEADO" + reset);
            System.out.println("=============\n");
            System.out.println("1. Crear solicitud            ");
            System.out.println("2. Obtener solicitud          ");
            System.out.println("3. Actualizar solicitud       ");
            System.out.println("4. Eliminar solicitud         ");
            System.out.println("5. Listar solicitudes         ");
            System.out.println("6. Salir                      ");
            System.out.println("                                            ");
            System.out.println("> Elige una opción (1-6):          ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    fecha = fechaActual;
                    System.out.println();
                    System.out.println("---------------");
                    System.out.println(azul + "CREAR SOLICITUD" + reset);
                    System.out.println("---------------");
                    System.out.print("> Ingrese nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("> Ingrese tema: ");
                    String tema = scanner.nextLine();
                    System.out.print("> Ingrese descripción: ");
                    String descripcion = scanner.nextLine();
                    String estado = ("Pendiente");
                    solicitudCRUD.crearSolicitud(nombre, fecha, tema, descripcion, estado);
                    System.out.println(naranja + "\nPulsa [Enter] para volver al menú de empleado..." + reset);
                    scanner.nextLine(); 
                    break;

                case 2:
                    System.out.println();
                    System.out.println("------------------");
                    System.out.println(azul + "OBTENER SOLICITUD" + reset);
                    System.out.println("------------------");
                    System.out.print("> Ingrese ID de la solicitud a obtener: ");
                    int idObtener = scanner.nextInt();
                    SolicitudDatabase solicitudObtenida = solicitudCRUD.obtenerSolicitud(idObtener);
                    if (solicitudObtenida != null) {
                        System.out.println("\n");
                        System.out.println(solicitudObtenida);
                        System.out.println(verde + "\n Solicitud obtenida exitosamente." + reset);
                        System.out.println(naranja + "\nPulsa [Enter] para volver al menú de empleado..." + reset);
                        scanner.nextLine(); 
                        scanner.nextLine(); 
                    } else {
                        System.out.println(rojo + "\n ERROR: No se encontró la solicitud con ID: " + idObtener + reset);
                    }
                    break;

                case 3:
                    System.out.println();
                    System.out.println("------------------");
                    System.out.println(azul + "ACTUALIZAR SOLICITUD" + reset);
                    System.out.println("------------------");
                    System.out.print("> Ingrese ID de la solicitud a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine();
                    if (solicitudCRUD.obtenerSolicitud(idActualizar) != null) {
                        System.out.print("> Ingrese nuevo nombre del solicitante: ");
                        String nombreActualizar = scanner.nextLine();
                        LocalDate fechaActualizar = LocalDate.now();
                        System.out.print("> Ingrese nuevo tema de la solicitud: ");
                        String temaActualizar = scanner.nextLine();
                        System.out.print("> Ingrese nueva descripción de la solicitud: ");
                        String descripcionActualizar = scanner.nextLine();
                        String estadoActualizar = "Cambio en Solicitud";
                        SolicitudDatabase solicitudActualizar = new SolicitudDatabase(idActualizar, nombreActualizar,
                                fechaActualizar, temaActualizar, descripcionActualizar, estadoActualizar);
                        boolean actualizado = solicitudCRUD.actualizarSolicitud(solicitudActualizar);
                        System.out.println(verde + "\n Solicitud actualizada exitosamente." + reset);
                        System.out.println(naranja + "\nPulsa [Enter] para volver al menú de empleado..." + reset);
                        scanner.nextLine(); 
                    } else {
                        System.out.println(rojo + "\n ERROR: No existe una solicitud con ID " + idActualizar + reset);
                    }
                    break;

                case 4:
                    System.out.println();
                    System.out.println("------------------");
                    System.out.println(azul + "ELIMINAR SOLICITUD" + reset);
                    System.out.println("------------------");
                    System.out.print("> Ingrese ID de la solicitud a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    
                    if (solicitudCRUD.obtenerSolicitud(idEliminar) != null) {
                        solicitudCRUD.eliminarSolicitud(idEliminar);
                        System.out.println(verde + "\n Solicitud eliminada exitosamente." + reset);
                        System.out.println(naranja + "\nPulsa [Enter] para volver al menú de empleado..." + reset);
                        scanner.nextLine(); 
                        scanner.nextLine(); 
                    }else{
                        System.out.println(rojo + "\n ERROR: No existe una solicitud con ID " + idEliminar + reset);
                    }
                    break;

                case 5:
                    System.out.println();
                    System.out.println("----------------");
                    System.out.println(azul + "LISTA SOLICITUDES" + reset);
                    System.out.println("----------------");
                    solicitudCRUD.mostrarSolicitudes();
                    System.out.println(naranja + "\nPulsa [Enter] para volver al menú de empleado..." + reset);
                    scanner.nextLine(); 
                    break;

                case 6:
                    solicitudCRUD.guardarSolicitudes();
                    running = false;
                    System.out.println(amarillo + "\nSALIENDO DE LA APLICACIÓN..." + reset);
                    break;

                default:
                    System.out.println(rojo + "\n ERROR: Opción no válida. Intente de nuevo." + reset);
            }
        }
        scanner.close();
    }
}
