import org.w3c.dom.ls.LSOutput;

// sujeto concreto
public class SMTPServer extends MailMessage {

    private Message message;


    public void sentMail(Message message){

        this.message = message;

        System.out.println("New mail sent to " + message.getTo());

        this.notifiy();
    }

    public Message getReceiver() {
        return message;
    }

    @Override
    public void notifiy() {
        observers.forEach(observer -> observer.update(this.message.toString()));
    }

    public void removeByName(String name) {
        observers.stream()
            .filter(observer -> observer instanceof  Receiver && ((Receiver) observer).getReceiver().equals(name))
            .findFirst()
                .ifPresent(this::removeObserver);
    }

}
