/**
 * [ClientMsg.java]
 * An object storing msg, client to and from of msg. This is so that msg can be stored in a queue as an object to buffer incoming and sending out messages
 * @author Edward Yang
 * @version 1.0
 * @since 2020/12/7
 */
 

class ClientMsg {
    
    /**User that is sending the msg */
    private OnlineUser userFrom;
    /**user that is receiving the msg */
    private OnlineUser userTo;
    /**msg that is being sent */
    private String msgSent; 

    /**
     * Constructor of the object
     * @param uf The userFrom, which user sent the msg
     * @param ut The userTo, which user is receiving the msg
     * @param msgSent What msg is being sent
     */
    ClientMsg(OnlineUser uf, OnlineUser ut, String msgSent){
        this.userFrom = uf;
        this.userTo = ut;
        this.msgSent = msgSent;
    }

    /**
     * @return  Method returns the msg being sent as a String
     */
    public String getMsg(){

        return this.msgSent;
    }

    /**
     * @return the user that the message is being from as a String
     */
    public OnlineUser getUserFrom(){

        return this.userFrom;
    }

    /**
     * @return the user that the msg is being sent to as a String
     */
    public OnlineUser getUserTo(){

        return this.userTo;
    }

}