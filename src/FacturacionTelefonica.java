import java.util.Scanner;

public class FacturacionTelefonica {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de líneas telefónicas: ");
        int cantidadLineas = sc.nextInt();

        while (cantidadLineas <= 0) {
            System.out.println("Debe ingresar un número positivo.");
            System.out.print("Ingrese la cantidad de líneas telefónicas: ");
            cantidadLineas = sc.nextInt();
        }

        double[][] datosLineas = getUserData(cantidadLineas);
        processBills(datosLineas);
    }

    public static double[][] getUserData(int cantidadLineas) {
        Scanner sc = new Scanner(System.in);
        double[][] datos = new double[cantidadLineas][2];

        for (int i = 0; i < cantidadLineas; i++) {
            System.out.println("Línea #" + (i + 1));

            System.out.print("Costo base del plan: ");
            double base = sc.nextDouble();
            while (base < 0) {
                System.out.println("El costo base no puede ser negativo.");
                System.out.print("Costo base del plan: ");
                base = sc.nextDouble();
            }

            System.out.print("Minutos excedidos: ");
            double excedente = sc.nextDouble();
            while (excedente < 0) {
                System.out.println("Los minutos excedidos no pueden ser negativos.");
                System.out.print("Minutos excedidos: ");
                excedente = sc.nextDouble();
            }

            datos[i][0] = base;
            datos[i][1] = excedente;
        }

        return datos;
    }

    public static double calculateOverages(double minutos) {
        return minutos * 0.25;
    }

    public static double calculateTax(double subtotal) {
        return subtotal * 0.15;
    }

    public static void calculateAndPrintBill(double base, double overage, double tax) {
        double total = base + overage + tax;

        System.out.println("Factura individual:");
        System.out.println("Costo base: $" + base);
        System.out.println("Cargo por excedente: $" + overage);
        System.out.println("Impuesto: $" + tax);
        System.out.println("Total a pagar: $" + total);
        System.out.println();
    }

    public static void processBills(double[][] lineas) {
        double totalGeneral = 0;

        for (int i = 0; i < lineas.length; i++) {
            double base = lineas[i][0];
            double excedente = lineas[i][1];

            double cargoExcedente = calculateOverages(excedente);
            double impuesto = calculateTax(base + cargoExcedente);

            calculateAndPrintBill(base, cargoExcedente, impuesto);

            totalGeneral += base + cargoExcedente + impuesto;
        }

        System.out.println("Total general a pagar por la empresa: $" + totalGeneral);

        // Bonus: promedio por línea
        double promedio = totalGeneral / lineas.length;
        System.out.println("Promedio por línea: $" + promedio);
    }
}