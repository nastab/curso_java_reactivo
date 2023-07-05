public class Message {
    private String to;
    private String subject;
    private String messageDetail;

    public Message() {
    }

    public Message(String to, String subject, String messageDetail) {
        this.to = to;
        this.subject = subject;
        this.messageDetail = messageDetail;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(String messageDetail) {
        this.messageDetail = messageDetail;
    }

    @Override
    public String toString() {
        return "Mail{" + "\n" +
                "to='" + to + "\'\n" +
                "subject='" + subject + "\'\n" +
                "message='" + messageDetail + "\'\n" +
                '}';
    }
}
