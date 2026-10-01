import controlador.PedidoControlador;
import modelo.Pedido;
import modelo.PedidoModelo;
import modelo.Producto;
import vista.PedidoVista;
import vista.PedidoVistaResumida;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal encargada de inicializar los componentes del patrón MVC
 * y orquestar la ejecución del sistema o las pruebas de evidencia.
 */
public class Principal {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        PedidoModelo modeloCompartido = new PedidoModelo();
        PedidoVista vistaNormal = new PedidoVista();
        PedidoVistaResumida vistaResumida = new PedidoVistaResumida();
        PedidoControlador controlador = new PedidoControlador(modeloCompartido, vistaNormal);

        System.out.println("====================================================");
        System.out.println("    SISTEMA DE GESTIÓN DE PEDIDOS - ARQUITECTURA MVC");
        System.out.println("====================================================");
        System.out.println("1. Demostración automática de casos de evidencia (ADA)");
        System.out.println("2. Modo interactivo por consola");
        System.out.print("Seleccione una opción (1 o 2, default 1): ");

        String opcion = entrada.nextLine().trim();
        if ("2".equals(opcion)) {
            ejecutarModoInteractivo(controlador, entrada);
        } else {
            ejecutarCasosDeEvidencia(modeloCompartido, vistaNormal, vistaResumida, controlador);
        }
    }

    /**
     * Ejecuta automáticamente los 6 casos de prueba requeridos en las evidencias del ADA.
     */
    public static void ejecutarCasosDeEvidencia(
            PedidoModelo modelo,
            PedidoVista vistaNormal,
            PedidoVistaResumida vistaResumida,
            PedidoControlador controlador) {

        System.out.println("\n====================================================");
        System.out.println(">>> EJECUTANDO LOS 6 CASOS DE EVIDENCIA SOLICITADOS <<<");
        System.out.println("====================================================");

        // 1. Registrar pedido válido con descuento (Subtotal >= 1000)
        System.out.println("\n----------------------------------------------------");
        System.out.println("[CASO 1]: Registrar pedido válido CON descuento (Subtotal >= $1,000)");
        System.out.println("----------------------------------------------------");
        List<Producto> prodsConDescuento = new ArrayList<>();
        prodsConDescuento.add(new Producto("Laptop Pro", 2500.0, 1, 5));
        prodsConDescuento.add(new Producto("Mouse Inalámbrico", 500.0, 2, 10)); // Total subtotal = 2500 + 1000 = 3500 >= 1000
        Pedido pedido1 = new Pedido("Carlos Slim", prodsConDescuento);
        controlador.setVista(vistaNormal);
        controlador.registrarPedido(pedido1);

        // 2. Registrar pedido válido sin descuento (Subtotal < 1000)
        System.out.println("\n----------------------------------------------------");
        System.out.println("[CASO 2]: Registrar pedido válido SIN descuento (Subtotal < $1,000)");
        System.out.println("----------------------------------------------------");
        List<Producto> prodsSinDescuento = new ArrayList<>();
        prodsSinDescuento.add(new Producto("Teclado USB", 350.0, 1, 15));
        prodsSinDescuento.add(new Producto("Funda de Laptop", 250.0, 1, 8)); // Total subtotal = 600 < 1000
        Pedido pedido2 = new Pedido("Ana López", prodsSinDescuento);
        controlador.setVista(vistaNormal);
        controlador.registrarPedido(pedido2);

        // 3. Intentar registrar un pedido inválido (Cantidad supera la existencia)
        System.out.println("\n----------------------------------------------------");
        System.out.println("[CASO 3]: Intentar registrar un pedido INVÁLIDO (Cantidad > Existencia)");
        System.out.println("----------------------------------------------------");
        List<Producto> prodsInvalidos = new ArrayList<>();
        prodsInvalidos.add(new Producto("Monitor 4K", 800.0, 10, 2)); // 10 solicitados vs 2 existentes
        Pedido pedido3 = new Pedido("Mario Rossi", prodsInvalidos);
        controlador.setVista(vistaNormal);
        controlador.registrarPedido(pedido3);

        // 4. Consultar un pedido existente
        System.out.println("\n----------------------------------------------------");
        System.out.println("[CASO 4]: Consultar un pedido EXISTENTE (ID = 1)");
        System.out.println("----------------------------------------------------");
        controlador.setVista(vistaNormal);
        controlador.consultarPedido(1);

        // 5. Consultar un pedido inexistente
        System.out.println("\n----------------------------------------------------");
        System.out.println("[CASO 5]: Consultar un pedido INEXISTENTE (ID = 999)");
        System.out.println("----------------------------------------------------");
        controlador.setVista(vistaNormal);
        controlador.consultarPedido(999);

        // 6. Ejecutar el mismo Modelo con la Vista normal y la Vista resumida
        System.out.println("\n----------------------------------------------------");
        System.out.println("[CASO 6]: Demostración de reutilización del Modelo con Vista Normal y Vista Resumida");
        System.out.println("----------------------------------------------------");

        System.out.println("\n--> 6.A Visualización con PedidoVista (Vista Completa/Normal) para el Pedido 1:");
        controlador.setVista(vistaNormal);
        controlador.consultarPedido(1);

        System.out.println("\n--> 6.B Visualización con PedidoVistaResumida (Mismo Modelo, Nueva Representación) para el Pedido 1:");
        controlador.setVista(vistaResumida);
        controlador.consultarPedido(1);

        System.out.println("====================================================");
        System.out.println("         FIN DE LOS CASOS DE EVIDENCIA");
        System.out.println("====================================================");
    }

    private static void ejecutarModoInteractivo(PedidoControlador controlador, Scanner scanner) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar nuevo pedido");
            System.out.println("2. Consultar pedido por ID");
            System.out.println("3. Cambiar a Vista Normal");
            System.out.println("4. Cambiar a Vista Resumida");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1":
                    controlador.registrarPedido();
                    break;
                case "2":
                    controlador.consultarPedido();
                    break;
                case "3":
                    controlador.setVista(new PedidoVista());
                    System.out.println("Vista cambiada a: PedidoVista (Completa)");
                    break;
                case "4":
                    controlador.setVista(new PedidoVistaResumida());
                    System.out.println("Vista cambiada a: PedidoVistaResumida (Resumen)");
                    break;
                case "5":
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
