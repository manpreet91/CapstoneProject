/*
1. This file is a part of Capstone project Android app called ShopSmart. 
1. This file demonstrates the creation of the text file containing the shopping list items and how it is transferred to the embedded system (beaglebone black)
2. A function called FinalSelection() is called when user hits the button to save selected items from the UI in a text file.
3. This text file gets store on the SD storage of the smartphone
4. Once the file is stored, it uses FTP class and establishes the communication between smartphone (client) and beaglebone black (server)
5. ftptesting() is called when user is ready to transfer the file.

Note: Some section of the code is intentionally missing to make it more readable
 */
package shopsmartltd.shopsmart;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class SecondActivity extends AppCompatActivity {
    //declaring an array
    ArrayList<String> selection = new ArrayList<String>();

	//Items available as a list 
    public void selectItem(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())
        {
            case R.id.checkBox:
                if (checked)
                {selection.add("Pop-355mL-Bottle");}
                else
                {selection.remove("Pop-355mL-Bottle");}
                break;

            case R.id.checkBox2:
                if (checked)
                {selection.add("Pop-200mL-Bottle");}
                else

                {selection.remove("Pop-200mL-Bottle");}
                break;

            case R.id.checkBox3:
                if (checked)
                {selection.add("Pop-150mL-Bottle");}
                else
                {selection.remove("Pop-150mL-Bottle");}
                break;

            case R.id.checkBox4:
                if (checked)
                {selection.add("ToiletPaper");}
                else
                {selection.remove("ToiletPaper");}
                break;

        }
    }

    //Create text file containig the items selected by the user.
    //Author: Manpreet Singh and Jashan Dhaliwal
    public void FinalSelection (View view) {
        String FinalList = "";

        for (String Selections : selection) {
            FinalList = FinalList + Selections + "\n";
        }
        final_text.setText(FinalList);
        final_text.setEnabled(true);
        try {
            File rootDirectory = new File(Environment.getExternalStorageDirectory(), "ShoppingSmartDirectory");
            if (!rootDirectory.exists()) { rootDirectory.mkdirs();  }

            //To write to the text file

            String filename = "ShoppingList.txt";

            File textFilePath = new File(rootDirectory, filename);
            FileWriter writer = new FileWriter(textFilePath);

            writer.append(FinalList);
            writer.flush();
            writer.close();
            System.out.println("List Generated");

            //UI Display message
            //Toast.makeText(this,"Saved to"+textFilePath.toString(),Toast.LENGTH_LONG).show();
            Toast.makeText(getBaseContext(),"Shopping List saved",Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            System.out.println("Shopping List cannot be created");
            Toast.makeText(getApplicationContext(), "System Error. Please use the Cart Display.", Toast.LENGTH_LONG).show();
        }
    }
	
	//Author: Manpreet Singh
	//Function to transfer the text file from smartphone to embedded system on the shopping cart
	public void ftptesting()
    {
        AsyncTask< String, Integer, Boolean > task = new AsyncTask< String, Integer, Boolean >()
        {
            private Exception m_exception = null;
            @Override
            protected Boolean doInBackground( String... params )
            {
                Boolean result = Boolean.FALSE;
                try {
                    FTPClient mFTPClient = new FTPClient();
					//ipaddress same for server and client
                    mFTPClient.connect("192.168.43.157", 21);

                    if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {
                        if(mFTPClient.login("debian", "1234"))
                        {
                            //mFTPClient.setFileType(FTP.ASCII_FILE_TYPE);
                            mFTPClient.enterLocalPassiveMode();
                            System.out.println("i am here");

                            try {
                                File rootDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"ShoppingSmartDirectory");
                                File [] subFiles = rootDirectory.listFiles();
                                for (File item1:subFiles){
                                    String test = item1.getPath();
                                    System.out.println(test);

                                    if(item1.isFile()){
                                        String localPath = item1.getAbsolutePath();
                                        System.out.println("About to upload this file"+localPath);
                                        File localFile = new File(localPath);
                                        InputStream myInputStr = new FileInputStream(localFile);
                                        mFTPClient.setFileType(FTP.ASCII_FILE_TYPE);
                                        String remoteFilePath = "ShoppingList.txt";
                                        boolean uploaded = mFTPClient.storeFile(remoteFilePath,myInputStr);
                                        System.out.println("REPLYYY: "+mFTPClient.getReplyCode());
                                        if(uploaded){System.out.println("uploaded the file to: "+remoteFilePath);}
                                        else{System.out.println("could not uplaod the file: "+localPath);}
                                        myInputStr.close();
                                    }

                                }
                            } catch (Exception e) {
                                String log = e.getMessage();
                                Log.d("Error: ", log);
                            }

                        }
                        mFTPClient.logout();
                        mFTPClient.disconnect();
                    }

                }catch(Exception e) {
                    String log = e.getMessage(); //**this is LINE 131**
                    Log.d("Errore: ", log);

                }

                return result;
            }

        };

        task.execute("");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void transferToBeagleBone(View view) {
        ftptesting();
        Toast.makeText(getApplicationContext(),"List exported successfully",Toast.LENGTH_LONG).show();
    }

  


}


