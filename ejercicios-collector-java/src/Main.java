import java.util.*;
import java.util.stream.Collectors;

public class Main {
    /**
     * Ejercicio utilizando toList:
     * Escribe un programa que tome una lista de productos
     * y devuelva una lista con los nombres de los productos
     * cuyos precios son mayores a $5.
     *
     * */

    public static List<String> getProductNames(List<Product> products) {
        return products.stream().filter(product -> product.getPrice() > 5)
                .map(Product::getName)
                .collect(Collectors.toList());
    }

    /**
     * Ejercicio utilizando counting:
     * Dada una lista de productos, implementa un método que cuente la cantidad
     * total de productos cuyos precios sean mayores a $5.
     * */

    public static long countExpensiveProducts(List<Product> products) {
        return products.stream().filter(product -> product.getPrice() > 5)
                .count();
     }


    /**
     * Ejercicio utilizando toUnmodifiableList:
     * Dada una lista de desarrolladores, implementa un método que devuelva una lista inmutable
     * con los nombres de los desarrolladores.
     * */

    public static List<String> getImmutableDeveloperNames(List<Developer> developers) {
        return developers.stream().map(Developer::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Ejercicio utilizando .joining(", "):
     * Dada una lista de productos, implementa un método que obtenga
     * los nombres de los productos cuyos precios sean mayores a $5
     * y los concatene en una cadena separada por comas.
     * */

    public static String getNamesOfExpensiveProducts(List<Product> products) {
        return products.stream().filter(product -> product.getPrice() > 5)
                .map(Product::getName)
                .collect(Collectors.joining(", "));
    }
    /**
     * Dada una lista de productos, implementa un método que agrupe todos los
     * *elementos por precio en un mapa
     */

    public static Map<Double, List<Product>> groupProductBy(List<Product> products) {
        return products.stream().collect(Collectors.groupingBy(Product::getPrice));
    }

    /**
     * Ejercicio utilizando .summarizingDouble:
     * Dada una lista de productos, implementa un método que calcule las estadísticas
     * del precio de los productos, incluyendo la cantidad total, el mínimo, el máximo,
     * el promedio y la suma.
     * **/

    public static DoubleSummaryStatistics calculatePriceStatistics(List<Product> products) {
        return products.stream().collect(Collectors.summarizingDouble(Product::getPrice));
    }

    /**
     * Ejercicio utilizando toSet():
     * Dada una lista de empleados, implementa un metodo que devuelva un conjunto
     * unico de todas las habilidades presentes en la lista de empleados.
     * */

    public static Set<String> getAllUniqueSkills(List<Employee> employees) {
        return employees.stream().flatMap(employee -> employee.getSkills().stream())
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {


        List<Product> products = new ArrayList<>(List.of(
                new Product( "Coca cola", 9.99, false),
                new Product("Pan dulce", 5.00, false),
                new Product("Harina", 5.00, false),
                new Product( "aceite", 39.99, false),
                new Product( "manteca", 19.99, false)
        ));

        List<Developer> developers = new ArrayList<>(List.of(
                new Developer("dev1", 20),
                new Developer("dev2", 30),
                new Developer("dev3", 40)
        ));

        Employee employee1 = new Employee("1", "John Doe", Set.of("Java", "Python"));
        Employee employee2 = new Employee("2", "Jane Smith", Set.of("C++", "JavaScript"));
        Employee employee3 = new Employee("3", "Mike Johnson", Set.of("Java", "SQL"));

        List<Employee> employees = new ArrayList<>(List.of(employee1, employee2, employee3));

        System.out.println(getProductNames(products));
        System.out.println(countExpensiveProducts(products));
        System.out.println(getImmutableDeveloperNames(developers));
        System.out.println(getNamesOfExpensiveProducts(products));
        System.out.println(groupProductBy(products));
        System.out.println(getAllUniqueSkills(employees));


        // Ejemplo calculatePriceStatistics
        DoubleSummaryStatistics priceStatistics = calculatePriceStatistics(products);
        System.out.println("Price Statistics: ");
        System.out.println("Count: " + priceStatistics.getCount());
        System.out.println("Min: " + priceStatistics.getMin());
        System.out.println("Max: " + priceStatistics.getMax());
        System.out.println("Average: " + priceStatistics.getAverage());
        System.out.println("Sum: " + priceStatistics.getSum());

    }
}