package scripts.base.observer;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public interface IObserver {

    public void handleEvent(LocalDateTime value);
}
