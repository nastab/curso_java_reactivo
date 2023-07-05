import java.util.Scanner;

public class Main {



    public static void main(String[] args) {


        SMTPServer server = new SMTPServer();

        execute(server);


    }

    private static void execute(SMTPServer server) {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        System.out.println("\nMenu de opciones:");
        System.out.println("" +
                "1- Subscribir correo\n" +
                "2- Cancelar subscripción correo \n" +
                "3- Enviar correo\n" +
                "8- Salir");
        System.out.print("Ingrese la opción: ");
        int menuOption = sc.nextInt();

        switch (menuOption){
            case 1:
                System.out.println("Registrar correo");
                String mailToSubscribe = sc2.nextLine();

                Receiver receiver = new Receiver(mailToSubscribe);
                server.addObserver(receiver);

                System.out.println("Correo suscrito.");
                execute(server);
                break;

            case 2:
                server.getObservers().forEach(System.out::println);

                System.out.println("Cancelar subscripcion correo");
                String nameToBeRemoved = sc2.nextLine();

                server.removeByName(nameToBeRemoved);
                execute(server);
                break;

            case 3:
                System.out.println("Enviar correo:\n");
                System.out.println("ingrese el destinatario: ");
                String to = sc2.nextLine();
                System.out.println("ingrese el asunto: ");
                String subject = sc2.nextLine();
                System.out.println("ingrese el mensaje: ");
                String message = sc2.nextLine();
                server.sentMail(new Message(to, subject, message));
                execute(server);
                break;
            case 8:
                System.out.println("Gracias por utilizar nuestro sistema!");
                break;
            default:
                System.out.println("Opción incorrecta");
        }
        sc.close();
        sc2.close();
    }
}