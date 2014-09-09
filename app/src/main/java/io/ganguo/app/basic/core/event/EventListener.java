package io.ganguo.app.basic.core.event;

/**
 * EventListener
 * <p/>
 * Created by tony on 8/28/14.
 */
public interface EventListener<T> {
    public void onEvent(T event);
}
