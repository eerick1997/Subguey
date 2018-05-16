package Objects;

import java.net.URI;

/**Class User contains information that we can get using
 * the Google account, this information can be:
 * name, email, profile photo, id of google, ..., etc**/
public class User {

    //Variables
    private String name, email, nick_name;
    //Constructor
    /**User constructor needs three parameters
     * 1. Name: The name of this user this is provided by his/her google account
     * 2. Email: This parameter is provided by his/her google account
     * 3. Nick name: this need to be unique and created by each user**/
    public User(String name, String email, String nick_name){
        //Initializing our global private variables
        this.name = name;
        this.email = email;
        this.nick_name = nick_name;
    }

    /**--------------- GETTERS ---------------**/
    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getNick_name() {
        return this.nick_name;
    }

    /**-------------- SETTERS ---------------**/
    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setNick_name(String nick_name){
        this.nick_name = nick_name;
    }
}
