package DataBases;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Converter {
    //Constants
    private static final String TAG = "Converter.java";

    /**This method convert an object into a byte array
     * this method receive just an argument type Object
     * and returns an array of bytes**/
    public byte[] toBytes(Object object){
        byte[] bytes = null;
        //We can use a ByteArrayOutputStream to convert
        //everything in a byte array
        ByteArrayOutputStream byteArrayOutputStream =
                new ByteArrayOutputStream();
        ObjectOutput objectOutput = null;
        try{
            //We associate our ByteArrayOutputStream with our ObjectOutput
            objectOutput = new ObjectOutputStream(byteArrayOutputStream);
            //We convert our object into bytes
            objectOutput.writeObject(object);
            objectOutput.flush();
            //we convert our byteArrayOutputStream into bytes
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e){
            Log.e(TAG, "toBytes: ", e);
        } finally {
            try{
                byteArrayOutputStream.close();
            } catch (IOException e){
                Log.e(TAG, "toBytes: ", e);
            }
        }
        return bytes;
    }

    public Object getObject(byte[] bytes){
        Object object = null;
        //We associate a byte array in a ByteArrayInputStream
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        try{
            //We initialize our ObjectInput
            in = new ObjectInputStream(byteArrayInputStream);
            //We get an object
            object = in.read();
        } catch (IOException e){
            Log.e(TAG, "getObject: ", e);
        } finally{
            try{
                if(in != null){
                    in.close();
                }
            }catch (IOException e){
                Log.e(TAG, "getObject: ", e);
            }
        }
        return object;
    }

}
