public class Receiver implements Observer{
    private String email;


    public Receiver(String email) {
        this.email = email;
    }

    @Override
    public void update(String param) {
        System.out.println("New email received");
        System.out.println(param);
    }

    public String getReceiver() {
        return email;
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "email='" + email + '\'' +
                '}';
    }
}
