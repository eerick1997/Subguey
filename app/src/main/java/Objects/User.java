package Objects;

import android.util.Log;

import java.net.URI;

/**Class User contains information that we can get using
 * the Google account, this information can be:
 * name, email, profile photo, id of google, ..., etc**/
public class User {

    //Variables
    private String name, email, nick_name;
    private float nqualifiers = 0.0f, sum_qualifications= 0.0f;
    //Constructor
    /**User constructor needs six parameters
     * 1. Name: The name of this user this is provided by his/her google account
     * 2. Email: This parameter is provided by his/her google account
     * 3. Nick name: this need to be unique and created by each user
     * 4. Number of qualifiers: This parameter is used to calculate the rank of each user
     * 5. Sum of qualifications: this parameter has all the qualifications of the users
     * 6. Qualification: this parameter has the **/
    public User(String name, String email, String nick_name,
                float nqualifiers, float sum_qualifications,
                Float qualification){
        //Initializing our global private variables
        this.name = name;
        this.email = email;
        this.nick_name = nick_name;
        this.nqualifiers = nqualifiers;
        this.sum_qualifications = sum_qualifications;
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

    public Float getNqualifiers(){
        return this.nqualifiers;
    }

    public Float getSum_qualifications(){
        return this.sum_qualifications;
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

    public void setNqualifiers(Float nqualifiers){
        this.nqualifiers = nqualifiers;
    }

    public void setSum_qualifications(Float sum_qualifications){
        this.sum_qualifications = sum_qualifications;
    }

}
