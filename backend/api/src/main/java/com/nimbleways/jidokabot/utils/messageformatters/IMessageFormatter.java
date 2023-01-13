package com.nimbleways.jidokabot.utils.messageformatters;

import java.util.Map;

public interface IMessageFormatter {
    String formatMessage(final String message, Map<String,String> params);
}
