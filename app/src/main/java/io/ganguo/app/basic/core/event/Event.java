package io.ganguo.app.basic.core.event;


import java.util.HashMap;
import java.util.Map;

/**
 * 事件
 * <p/>
 * Created by tony on 8/28/14.
 */
public class Event {
    /**
     * 事件ID，定义在IMEventID
     */
    private EventType eventType;

    /**
     * 事件的对象，通常可以作为事件附加数据
     */
    private Object target;
    /**
     * 事件数据MAP，也可以直接把事件数据以KEY的形式放入MAP中，处理事件时按KEY读取出来，编程时要确保KEY一致
     */
    private Map<String, Object> data = new HashMap<String, Object>();

    public Event(EventType eventType) {
        this(eventType, null);
    }

    public Event(EventType eventType, Object target) {
        this.eventType = eventType;
        this.target = target;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public void putData(String key, Object value) {
        this.data.put(key, value);
    }

    public boolean hasData(String key) {
        return this.data.containsKey(key);
    }

}
