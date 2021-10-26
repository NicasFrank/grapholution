package test;

import main.chat.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ChatControllerTests {
    @Mock
    private ChatGui chatGui;
    @Mock
    private ChatApiClient chatApiClient;

    private ChatController chatController;
    private ChatUser user;
    private ChatUser externalUser1;
    private ChatUser externalUser2;

    @BeforeEach
    public void setup() {
        user = new ChatUser(1, "user");
        externalUser1 = new ChatUser(2, "externalUser1");
        externalUser2 = new ChatUser(3, "externalUser2");
        chatController = new ChatController(chatGui, chatApiClient, user);
    }

    @Test
    public void getUserNamesFromApi_WhenCalled_ReturnsAllUsernamesExceptOwn() {
        var clientResponse = new ArrayList<ChatUser>();
        clientResponse.add(user);
        clientResponse.add(externalUser1);
        clientResponse.add(externalUser2);

        when(chatApiClient.getUserNames()).thenReturn(clientResponse);

        var result = chatController.getUserNamesFromApi();

        assert result.contains(externalUser1.getName());
        assert result.contains(externalUser2.getName());
        assert !result.contains(user.getName());
    }

    @Test
    public void sendMessageToApi_WhenCalled_CallsClientWithCorrectMessage() {
        chatController.sendMessageToApi(
                "testMessage",
                externalUser1.getId(),
                externalUser1.getName()
        );

        var argumentCaptor = ArgumentCaptor.forClass(ChatMessage.class);
        verify(chatApiClient).sendMessage(argumentCaptor.capture());
        var argumentValue = argumentCaptor.getValue();

        assertEquals("testMessage", argumentValue.getText());
        assertEquals(externalUser1.getId(), argumentValue.getReceiver().getId());
        assertEquals(externalUser1.getName(), argumentValue.getReceiver().getName());

        verify(chatApiClient, never()).getNewMessages();
    }

    @Test
    public void sendMessageToApi_ApiClientThrowsException_ThrowChatControllerException() {
        doThrow(new RuntimeException()).when(chatApiClient).sendMessage(any(ChatMessage.class));

        var exception = assertThrows(ChatControllerException.class, () -> chatController.sendMessageToApi(
                "testMessage",
                externalUser1.getId(),
                externalUser1.getName()
        ));

        assertEquals("Failed to send message", exception.getMessage());
    }

    @Test
    public void getMessagesFromApi_WhenCalled_CallsClientAndDisplaysMessagesForUser() {
        var testMessage1 = new ChatMessage(externalUser1, user, "testMessage");
        var testMessage2 = new ChatMessage(externalUser2, user, "another testMessage");
        var clientResponse = new ArrayList<ChatMessage>();
        clientResponse.add(testMessage1);
        clientResponse.add(testMessage2);

        when(chatApiClient.getNewMessages()).thenReturn(clientResponse);

        chatController.getMessagesFromApi();

        verify(chatGui).displayMessage(
                testMessage1.getText(),
                testMessage1.getSender().getId(),
                testMessage1.getSender().getName()
        );

        verify(chatGui).displayMessage(
                testMessage2.getText(),
                testMessage2.getSender().getId(),
                testMessage2.getSender().getName()
        );
    }
}
