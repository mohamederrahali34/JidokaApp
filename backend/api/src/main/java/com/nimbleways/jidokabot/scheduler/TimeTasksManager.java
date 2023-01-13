package com.nimbleways.jidokabot.scheduler;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@AllArgsConstructor
public class TimeTasksManager {

    private final Map<String, ScheduledFuture<?>> timeTasks = new HashMap<>();
    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;



    public boolean cancelTimeTask(final String taskName){
        final ScheduledFuture<?> taskToCancel = getTask(taskName);
        if(taskToCancel==null){
            return false;
        }
        else {
            taskToCancel.cancel(true);
            return true;
        }
    }
    //thread safe
    public ScheduledFuture<?> addTimeTask(final String name,final ScheduledFuture<?> task){
        return timeTasks.put(name,task);
    }
    public ScheduledFuture<?> getTask(final String name){
        return  timeTasks.get(name);
    }
    public ScheduledFuture<?> executeTimeTask(final String taskName,final Runnable runnable,final int leadTime){
        final ScheduledFuture<?> scheduledFuture =  threadPoolTaskScheduler.schedule(runnable, new Date(System.currentTimeMillis() +leadTime));
        final ScheduledFuture<?> lastTimeTask =  addTimeTask(taskName,scheduledFuture);

        if(lastTimeTask != null && !lastTimeTask.isDone()){
            lastTimeTask.cancel(true);
        }
        return scheduledFuture ;
    }

}
