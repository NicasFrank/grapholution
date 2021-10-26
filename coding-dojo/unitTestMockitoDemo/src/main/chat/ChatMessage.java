package main.chat;

public class ChatMessage {
    private final ChatUser sender;
    private final ChatUser receiver;
    private final String text;

    public ChatMessage(ChatUser sender, ChatUser receiver, String text) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
    }

    public ChatUser getSender() {
        return sender;
    }

    public ChatUser getReceiver() {
        return receiver;
    }

    public String getText() {
        return text;
    }
}
