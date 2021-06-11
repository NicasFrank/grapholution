package modelViewViewModel;


public interface ViewModel<NotificationObserver> {


        default void publish(String messageName, Object... payload) {
            MvvmFX.getNotificationCenter().publish(String.valueOf(System.identityHashCode(this)), messageName, payload);
        }


        default void subscribe(int i, String messageName, NotificationObserver observer) {
            MvvmFX.getNotificationCenter().subscribe(System.identityHashCode(this), messageName, observer);
        }


        default void unsubscribe(String messageName, NotificationObserver observer) {
            MvvmFX.getNotificationCenter().unsubscribe(System.identityHashCode(this), messageName, observer);
        }

    void unsubscribe(int identityHashCode, String messageName, NotificationObserver observer);

          default void unsubscribe(NotificationObserver observer) {
            MvvmFX.getNotificationCenter().unsubscribe(System.identityHashCode(this));
        }
    }


