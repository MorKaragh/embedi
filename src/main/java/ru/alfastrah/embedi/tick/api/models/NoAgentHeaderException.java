package ru.alfastrah.embedi.tick.api.models;

import ru.alfastrah.embedi.util.BadRequestException;

public class NoAgentHeaderException extends BadRequestException {

    public NoAgentHeaderException() {
        super("отсутствует заголовок x-agent-id");
    }

}
