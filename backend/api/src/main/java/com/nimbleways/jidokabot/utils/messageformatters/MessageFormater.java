package com.nimbleways.jidokabot.utils.messageformatters;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Setter
@Getter
@Component
public class MessageFormater implements IMessageFormatter{

    @Override
    public String formatMessage(final String message,final Map<String,String> params) {
        String result = message;
        for(final String key : params.keySet() ){
            result =  result.replace("{"+key+"}",params.get(key));
        }
        return result;
    }
}
