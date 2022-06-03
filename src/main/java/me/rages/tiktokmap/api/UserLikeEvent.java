package me.rages.tiktokmap.api;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author : Michael
 * @since : 5/24/2022, Tuesday
 **/
public class UserLikeEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private String username;
    private int totalLikes;

    public UserLikeEvent(String username, int totalLikes) {
        this.username = username;
        this.totalLikes = totalLikes;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
