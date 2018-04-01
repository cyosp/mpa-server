package com.cyosp.mpa.api.rest.common.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by CYOSP on 2017-07-19.
 */
@Getter
@AllArgsConstructor
public class Message {

    MessageType type;

    String message;

}