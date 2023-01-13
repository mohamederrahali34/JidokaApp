package com.nimbleways.jidokabot.events;


import com.nimbleways.jidokabot.scheduler.NotificationTask;
import com.nimbleways.jidokabot.scheduler.TimeTasksManager;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class StartCountingEventListener implements ApplicationListener<StartCountingEvent> {
    private final TimeTasksManager timeTasksManager;
    private final NotificationTask notificationTask;

    @Override
    public void onApplicationEvent(final StartCountingEvent event) {
        notificationTask.setMessage(event.getMessage());
        notificationTask.setChannel(event.getChannel());
        notificationTask.setCardId(event.getCardId());
        timeTasksManager.executeTimeTask(event.getCardId(),notificationTask,event.getLeadTime());
    }
}
