import java.util.ArrayList;

public abstract class MailMessage {
    protected ArrayList<Observer> observers;

    public MailMessage() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
        System.out.println("Suscripcion cancelada.");
    }

    public abstract void notifiy();

    public ArrayList<Observer> getObservers() {
        return observers;
    }


}
