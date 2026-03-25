import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ShoppingCartApp {

    private Scanner input = new Scanner(System.in);
    private Locale locale;
    private ResourceBundle rb;

    public ShoppingCartApp() {
        System.out.println("Please select one language by entering its associated number :");
        System.out.println("[1]. English\n[2]. Swedish\n[3]. Finish\n[4]. Japanese");
        System.out.print("> ");
        int langSelect = -1;
        boolean entered = false;
        do {
            if(entered) System.out.print("Invalid input. Please retry entering the number associated to the language you would like. \n> ");
            entered = true;
            while(!input.hasNextInt()) {
                System.out.print("Invalid input. Please retry entering the number associated to the language you would like. \n> ");
                input.next();
            }
            langSelect = input.nextInt();
        } while (langSelect < 1 || langSelect > 4);

        locale = switch (langSelect) {
            case 1 -> new Locale("en", "US");
            case 2 -> new Locale("sv", "SE");
            case 3 -> new Locale("fi", "FI");
            case 4 -> new Locale("ja", "JP");
            default -> throw new IllegalStateException("Unexpected value: " + langSelect);
        };

        rb = ResourceBundle.getBundle("MessagesBundle", locale);
    }

    public ShoppingCartApp(String language, String country)
    {
        locale = new Locale(language, country);
        rb = ResourceBundle.getBundle("MessagesBundle", locale);
    }

    private int getNBItems() {
        int nbItems = -1;
        boolean entered = false;
        System.out.print(rb.getString("nbItems")+"\n> ");
        do {
            if(entered) System.out.print("/!\\\n> ");
            entered = true;
            while(!input.hasNextInt()) {
                System.out.print("/!\\\n> ");
                input.next();
            }
            nbItems = input.nextInt();
        } while (nbItems < 0);
        return  nbItems ;
    }

    private int getQuantity() {
        int quantity = -1;
        boolean entered = false;
        System.out.print(rb.getString("quantity")+"\n> ");
        do {
            if(entered) System.out.print("/!\\\n> ");
            entered = true;
            while(!input.hasNextInt()) {
                System.out.print("/!\\\n> ");
                input.next();
            }
            quantity = input.nextInt();
        } while (quantity < 0);
        return  quantity;
    }

    private double getPrice() {
        double price = -1;
        boolean entered = false;
        System.out.print(rb.getString("price")+"\n> ");
        do {
            if(entered) System.out.print("/!\\\n> ");
            entered = true;
            while(!input.hasNextDouble()) {
                System.out.print("/!\\\n> ");
                input.next();
            }
            price = input.nextDouble();
        } while (price < 0);
        return price;
    }

    public void calculateCost() {
        int nbItems = getNBItems();
        double total = 0;
        for(int i=0; i<nbItems; i++) {
            int objectQuantity = getQuantity();
            double objectPrice = getPrice();

            total += objectQuantity * objectPrice;

        }
        System.out.println(rb.getString("total") + String.format(" %.2f",total));
    }
}
