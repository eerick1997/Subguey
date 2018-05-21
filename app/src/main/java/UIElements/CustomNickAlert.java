package UIElements;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.erick.adooproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Objects.User;

import static DataBases.Firebase.FirebaseReferences.DB_REFERENCE;
import static DataBases.Firebase.FirebaseReferences.USER_REFERENCE;

public class CustomNickAlert {

    //Constants
    private static final String TAG = "CustomNickAlert.java";
    //Variables
    private Context context;

    //Constructor
    public CustomNickAlert(Context context){
        Log.d(TAG, "CustomNickAlert() called with: context = [" + context + "]");
        //Initializing variables
        this.context = context;
    }

    /**This method show an customized alert dialog. We don't need
     * extra information to send data to an user                **/
    public void showDialog(final User user) {
        //As we said in the description we gonna use an Alert Dialog
        final AlertDialog alertDialog;
        //Probably we catch an exception
        try{
            //We need to inflate a layout to customize an Alert Dialog
            LayoutInflater inflater = LayoutInflater.from(context);
            /**Method inflate(here_we_have_the_name_of_the_layout_to_inflate, view_group)**/
            View view = inflater.inflate(R.layout.nick_alert, null);
            //We make an instance of an alertDialog
            alertDialog = new AlertDialog.Builder(context).create();
            final EditText TXTNickName = (EditText)view.findViewById(R.id.TXT_nick_alert);
            Button button = (Button)view.findViewById(R.id.BTN_dialog_nick_alert);
            button.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    String nick_user = TXTNickName.getText().toString();
                    user.setNick_name(nick_user.trim());
                    Log.i("VALOR: ", "" + user.getNick_name());
                    register(user);
                    /**LALITO ELLA NO TE AMA :v De aquí se pueden sacar los datos para
                     * poder**/
                    /*Ste man :v */

                }
            });
            //We add all the elements in our Alert Dialog
            alertDialog.setView(view);
            alertDialog.show();
        } catch (Exception e){
            Log.e(TAG, "showDialog: ", e);
        }
    }

    /**
     * Description: This method send data to our database and verify if a user
     * already exists in the FireBase database
     * Parameters: An User object, as we know FireBase database is a oriented
     * object database, so we can send a complete object and It'll be registered
     * Returns: nothing
     **/
    private void register(@NonNull final User user /*, final GoogleSignInAccount account */) {
        Log.d(TAG, "register() called with: user = [" + user + "]");

        //We make an instance of a FirebaseDatabase object to create our database
        /*FirebaseDatabase database = FirebaseDatabase.getInstance();
        //We need to pass the name of our database and child to we need to use or create
        final DatabaseReference usuarios = database.getReference(FirebaseReferences.DB_REFERENCE).child(FirebaseReferences.USER_REFERENCE);
        //put Object on database's child User
        usuarios.push().setValue(user);*/

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference usuarios = database.getReference(DB_REFERENCE).child(USER_REFERENCE);
        usuarios.addListenerForSingleValueEvent(new ValueEventListener() {
            boolean bool1 = false;
            boolean bool2 = false;

            @Override


            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot objSnapshot : snapshot.getChildren()) {
                    final User actual = objSnapshot.getValue(User.class);

                    if (user.getEmail().equals(actual.getEmail())) {
                        bool1 = true;
                        Toast.makeText(context, "La cuenta de correo gmail: " + user.getEmail() + " ya se encuentra asociada", Toast.LENGTH_LONG).show();
                    }

                    if (user.getNick_name().equals(actual.getNick_name())) {
                        bool2 = true;
                        Toast.makeText(context, "El Nick "+user.getNick_name()+" ya se encuentra en uso, intente con otro ", Toast.LENGTH_LONG).show();
                    }


                }
                if ((bool1 == false) && (bool2==false)) {
                    usuarios.push().setValue(user);
                    /*try {
                        getAccountData(account);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.e("Read failed", firebaseError.getMessage());
            }
        });


        //getAccountData(account); //line 168
    }

}
