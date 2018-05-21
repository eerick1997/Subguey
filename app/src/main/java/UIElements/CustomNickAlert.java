package UIElements;

import android.app.AlertDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.erick.adooproject.R;

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
    public void showDialog(){
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
                   Log.i("VALOR: ", ""+nick_user);
                }
            });
            //We add all the elements in our Alert Dialog
            alertDialog.setView(view);
            alertDialog.show();
        } catch (Exception e){
            Log.e(TAG, "showDialog: ", e);
        }
    }

}
