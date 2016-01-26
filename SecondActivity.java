/*
1. This file demonstrates the creation of the text file containing the shopping list items and how it is transferred to the embedded system
2. A function called FinalSelection() is called when user hits the button to save selected items from the UI in a text file.
3. This text file gets store on the SD storage of the smartphone
4. Once the file is stored, it uses FTP class and treats the smartphone as a client and embedded system on the shopping cart as a server.
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

    //declaring object for Text view (to cross check if items are added correctly)
    TextView final_text;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_second);
        final_text = (TextView) findViewById(R.id.final_result);  //dummy to be removed
        final_text.setEnabled(false);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



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

    //Create text file for shopping list
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

    //FTP communication
    //Author: Manpreet Singh

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
                                Log.d("Errorrrr: ", log);
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
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Second Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://shopsmartltd.shopsmart/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }


}


