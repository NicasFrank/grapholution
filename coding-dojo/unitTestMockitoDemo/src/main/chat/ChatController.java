package main.chat;

import java.util.List;
import java.util.stream.Collectors;

public class ChatController {
    private final ChatGui chatGui;
    private final ChatApiClient chatApiClient;
    private final ChatUser user;

    public ChatController(ChatGui chatGui, ChatApiClient chatApiClient, ChatUser user) {
        this.chatGui = chatGui;
        this.chatApiClient = chatApiClient;
        this.user = user;
    }

    public List<String> getUserNamesFromApi() {
        return chatApiClient
                .getUserNames()
                .stream()
                .filter(chatUser -> chatUser.getId() != user.getId())
                .map(ChatUser::getName)
                .collect(Collectors.toList());
    }

    public void sendMessageToApi(String messageText, int receiverId, String receiverName) throws ChatControllerException {
        var receiver = new ChatUser(receiverId, receiverName);

        var message = new ChatMessage(user, receiver, messageText);

        try {
            chatApiClient.sendMessage(message);
        } catch (Exception e) {
            throw new ChatControllerException("Failed to send message");
        }
    }

    public void getMessagesFromApi() {
        chatApiClient.getNewMessages()
                .stream()
                .filter(message -> message.getReceiver().getId() == user.getId())
                .forEach(message -> chatGui.displayMessage(
                        message.getText(),
                        message.getSender().getId(),
                        message.getSender().getName()
                ));
    }
}
