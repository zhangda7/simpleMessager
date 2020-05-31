package com.comm.util;

public class MessageConstants {

    public static final byte VERSION = 1;

    public static final short PREAMBLE = (short) 0xABCD;

    /**
     * msg client send to server
     */
    public static final byte DIRECTION_CLIENT_TO_SERVER = 0;

    /**
     * msg server send to client
     */
    public static final byte DIRECTION_SERVER_TO_CLINET = 1;
}
