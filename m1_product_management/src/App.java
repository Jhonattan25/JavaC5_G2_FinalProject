import java.util.Scanner;

public class App {

  static Scanner scanner = new Scanner(System.in);

  static String productName = "";
  static double productPrice = -1;
  static int productStock = -1;
    public static void main(String[] args) throws Exception {

      int option = 0;
      do {
         showMenu();

        System.out.print("Ingrese su opción: ");
        option = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        switch (option) {
          case 1:
            registerProduct();
            break;
          case 2:
            showProductInformation();
            break;
          case 3:
            showTotalValue();
            break;
          case 4:
            showProductSummary();
            break;
          case 5:
            clearProductData();
            break;
          case 0:
            System.out.println("Saliendo del sistema.");
            break;
          default:
            System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            break;
        }
        
      } while (option != 0);

      scanner.close();
    }

    static void showMenu() {
      System.out.println("""

      --- Sistema de Gestión de Productos ---

      1. Registrar nuevo producto
      2. Mostrar información del producto actual
      3. Calcular valor total del inventario
      4. Mostrar resumen completo del producto
      5. Limpiar datos del producto actual
      0. Salir

      """);
    }

    static void registerProduct() {
      if (emptyProduct()) {
        setDataProducto();
      } else {
          System.out.print("Ya hay un producto registrado. ¿Desea sobrescribir los datos? (S/N): ");
          boolean validResponse = false;
          do {
            String response = scanner.nextLine();
            validResponse = isValidResponse(response);

            if (validResponse) {
              if (response.equalsIgnoreCase("S")) {
                setDataProducto();
              }
            } else {
              System.out.print("Respuesta inválida. Por favor ingrese 'S' para Sí o 'N' para No: ");
            }
          
          } while (!validResponse);
      }
    }

    static boolean emptyProduct() {
      return productName.equals("") || productPrice == -1 || productStock == -1;
    }

    static boolean isValidResponse(String response) {
      return response.equalsIgnoreCase("S") || response.equalsIgnoreCase("N");
    }

    static void setDataProducto() {
      productName = requestProductName();
      productPrice = requestProductPrice();
      productStock = requestProductStock();
      scanner.nextLine();

      System.out.println("Producto registrado correctamente");
    }

    static String requestProductName() {
      String name;
      boolean validName = false;

      do {
        System.out.print("Ingrese el nombre del producto: ");
        name = scanner.nextLine();
        validName = isValidName(name);

        if (!validName) {
          System.out.println("Nombre invalido. No debe estar vacio o contener solo espacios");
        }
      } while (!validName);

      return name;
    }

    static double requestProductPrice() {
      double price;
      boolean validPrice = false;

      do {
        System.out.print("Ingrese el precio del producto: ");
        price = scanner.nextDouble();
        validPrice = isValidPrice(price);

        if (!validPrice) {
          System.out.println("Precio invalido. Debe ser mayor de 0");
        }
      } while (!validPrice);

      return price;
    }

    static int requestProductStock() {
      int stock;
      boolean validStock = false;

      do {
        System.out.print("Ingrese la cantidad en inventario del producto: ");
        stock = scanner.nextInt();
        validStock = isValidStock(stock);

        if (!validStock) {
          System.out.println("Cantidad en inventario invalido. Debe ser un numero no negativo");
        }
      } while (!validStock);

      return stock;
    }

    static boolean isValidName(String name) {
      return !name.isBlank();
    }

    static boolean isValidPrice(double price) {
      return price > 0;
    }

    static boolean isValidStock(int stock) {
      return stock >= 0;
    }

    static void showProductInformation() {
      if (emptyProduct()) {
        System.out.println("No hay datos de producto registrados actualmente.");
      } else {
        System.out.printf("""

        --- Información del producto registrado ---

        Nombre: %s
        Precio: $%.2f
        Cantidad en inventario: %s
            
            """, productName, productPrice, productStock);
      }
    }

    static void showTotalValue() {
      if (emptyProduct()) {
        showErrorMessageProductEmpty();
      } else {
        System.out.printf("Valor total en inventario: $%.2f%n", calculateTotalValue());
      }
    }

    static void showProductSummary() {
      if (emptyProduct()) {
        showErrorMessageProductEmpty();
      } else {
        System.out.printf("""

        --- Resumen Del Producto ---

        Nombre: %s
        Precio unitario: $%.2f
        Cantidad en inventario: %s
        Valor total en inventario: $%.2f
        Estado del stock: %s
          

        """, productName, productPrice, productStock, calculateTotalValue(), getStatusProductStock());
      }
    }

    static double calculateTotalValue() {
      return productPrice * productStock;
    }

    static String getStatusProductStock() {
      String statusStock = "Stock suficiente";

      if (productStock < 5) {
        statusStock = "Stock bajo";
      } else if (productStock > 20) {
        statusStock = "Stock alto";
      }

      return statusStock;
    }

    static void clearProductData() {
      if (emptyProduct()) {
        showErrorMessageProductEmpty();
      } else {
        productName = "";
        productPrice = -1;
        productStock = -1;

        System.out.println("Los datos del producto actual han sido borrados exitosamente.");
      }
    }

    static void showErrorMessageProductEmpty() {
      System.out.println("No hay datos de producto registrados actualmente.");
    }
}