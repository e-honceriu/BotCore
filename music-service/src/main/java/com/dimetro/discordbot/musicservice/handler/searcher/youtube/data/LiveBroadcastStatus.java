package com.dimetro.discordbot.musicservice.handler.searcher.youtube.data;

public enum LiveBroadcastStatus {
    
    NONE("none"),
    LIVE("live"),
    UPCOMING("upcoming");

    private final String value;

    LiveBroadcastStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static LiveBroadcastStatus fromString(String text) {
        for (LiveBroadcastStatus kind : LiveBroadcastStatus.values()) {
            if (kind.value.equalsIgnoreCase(text)) {
                return kind;
            }
        }
        throw new IllegalArgumentException("Unknown live broadcast status: " + text);
    }

}
