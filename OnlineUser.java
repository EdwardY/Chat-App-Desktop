/**
 * [OnlineUser.java]
 * Object containing username(String), password(String), mySocket(Socket) used to identify users who are online
 * @author Edward Yang
 * @version 1.0
 * @since 2020/12/8
 */

import java.net.*;

class OnlineUser{

    /**the username */
    private String username ="";
    /**the password */
    private String password ="";
    private Socket mySocket;


    /**
     * Constructor for an user object
     * @param username the username that the account is associated with
     * @param password the password that the account is associated with 
     * @param mySocket 
     */
    OnlineUser(String username, String password, Socket mySocket){

        this.username = username;
        this.password = password;
        this.mySocket = mySocket;
        
    }


    /**
     * @return Socket related to the client that's online
     */
    public Socket getSocket(){

        return this.mySocket;
    }

    /**
     * 
     * @return method returns the value of the client's username
     */
    public String getUserName(){

        return this.username;
    }

    /**
     * @return method returns the value of the client's associated password
     */
    public String getPassword(){

        return this.password;
    }
    
}