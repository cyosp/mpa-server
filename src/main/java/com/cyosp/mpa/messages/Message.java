package com.cyosp.mpa.messages;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by CYOSP on 2017-07-19.
 */
@Getter
@Setter
public class Message {

    private MessageType type;

    private String message;

}