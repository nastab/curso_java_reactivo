package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Primera practica curso reactividad.");
        loadMenu();
    }

    public static void menu(){
        System.out.println("Menu de opciones:");
        System.out.println("1. Calcular el min y max de una lista de enteros");
        System.out.println("2. Calcular el descuento de una venta");
        System.out.println("3. Calcular el total de iva de una venta");

    }

    public static void loadMenu(){
        Scanner sc = new Scanner(System.in);
        menu();
        System.out.print("Ingrese la opción: ");
        String op=sc.nextLine();
        Main main = new Main();
        switch(op){
            case "1":
                System.out.println("Calculando min y max de una lista de enteros");
                List<Integer> lista = new ArrayList<>();
                lista.add(1);
                lista.add(2);
                lista.add(3);
                lista.add(4);
                lista.add(5);
                lista.add(6);
                lista.add(7);
                lista.add(8);
                lista.add(24);
                main.getMinAndMax(lista);
                break;
            case "2":
                System.out.println("Calculando el descuento de una venta");
                System.out.println("Ingrese el total de la venta: ");
                double total = sc.nextDouble();
                System.out.println("Ingrese el porcentaje de descuento: ");
                double disc = sc.nextDouble();

                main.calcDiscount(total, disc);
                break;
            case "3":
                System.out.println("Calculando el total de iva de una venta");
                System.out.println("Ingrese el total de la venta: ");
                double total2 = sc.nextDouble();
                System.out.println("Ingrese el porcentaje de iva: ");
                double iva = sc.nextDouble();
                main.calcTax(total2, iva);
                break;
            default:
                System.out.println("ERROR en el input, este metodo no ha sido creado. Intente de nuevo");
        }

    }

    /**
     * Haciendo uso de lo aprendido(Expresiones lambda, referencias a métodos y funciones puras) resuelva:
     * */

    /**
     * 1. Con la intención de encontrar el número mayor en una lista y a su vez el número menor, cree un método que le permita
     * estas dos funciones, siguiendo los principios de las funciones puras.
     * */
    public void getMinAndMax(List<Integer> lista) {

        FunctionMinMax ifunction = (list) -> {

            List<Integer> result = new ArrayList<>();
            result.add(Collections.min(lista));
            result.add(Collections.max(lista));
            return result;
        };

        System.out.println("Minimo y maximo: ");
        ifunction.getMinAndMax(lista).forEach(System.out::println);
    }

    /**
     * 2. En una tienda de ropa necesitamos aplicar un descuento a las ventas que se hacen, este descuento
     * es variable por lo que necesitamos que usted cree un método que reciba el total de la venta y el porcentaje a descontar
     * y devuelva el nuevo precio total.
     * */

    public void calcDiscount (double initTotal, double disc){

        FunctionDiscount iDiscount = (t, d) -> (t - (t * d / 100));

        System.out.println("El total de la venta con descuento es: " + iDiscount.getTotal(initTotal, disc));
    }

    /**
     * 3. Necesitamos calcular el iva de una venta, para esto es necesario que usted cree un método
     * Tendremos dos escenarios posibles, en el primero se le pasará únicamente el valor al cual se le debe
     * aplicar el iva, sin especificar el porcentaje, en este caso se debe aplicar un 21% siempre.
     * En el segundo escenario se le pasará tanto el precio de la venta como el porcentaje de iva.
     * Cree una sola función que pueda lograr estos dos propósitos
     * */

    public void calcTax (double total, double iva){
        FunctionTax iTax = (t, i) -> {
            if (i == 0.0) {
                return t * 0.21;
            } else {
                return t * i;
            }
        };

        System.out.println("Total Iva: " + iTax.getTax(total, iva));
    }
}