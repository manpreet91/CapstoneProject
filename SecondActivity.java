/*
Generate a text file containing the list of items which user will select from the UI
Save the file locally
Transfer the file to embedded system on the shopping cart
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
                {selection.add("Pop-100mL-Can");}
                else
                {selection.remove("Pop-100mL-Can");}
                break;

            case R.id.checkBox5:
                if (checked)
                {selection.add("Milk-2%-2L");}
                else
                {selection.remove("Milk-2%-2L");}
                break;

            case R.id.checkBox6:
                if (checked)
                {selection.add("Milk-3%-2L");}
                else
                {selection.remove("Milk-3%-2L");}
                break;

            case R.id.checkBox7:
                if (checked)
                {selection.add("Milk-1.25%-2L");}
                else
                {selection.remove("Milk-1.25%-2L");}
                break;

            case R.id.checkBox8:
                if (checked)
                {selection.add("Milk-2%-1L");}
                else
                {selection.remove("Milk-2%-1L");}
                break;

            case R.id.checkBox9:
                if (checked)
                {selection.add("Milk-3%-1L");}
                else
                {selection.remove("Milk-3%-1L");}
                break;

            case R.id.checkBox10:
                if (checked)
                {selection.add("Choc-Milk-2%-1L");}
                else
                {selection.remove("Choc-Milk-2%-1L");}
                break;

            case R.id.checkBox11:
                if (checked)
                {selection.add("Bread-White-McGavins");}
                else
                {selection.remove("Bread-White-McGavins");}
                break;

            case R.id.checkBox12:
                if (checked)
                {selection.add("Bread-Brown-McGavins");}
                else
                {selection.remove("Bread-Brown-McGavins");}
                break;

            case R.id.checkBox13:
                if (checked)
                {selection.add("Bread-White-Plain");}
                else
                {selection.remove("Bread-White-Plain");}
                break;

            case R.id.checkBox14:
                if (checked)
                {selection.add("Bread-Brown-Plain");}
                else
                {selection.remove("Bread-Brown-Plain");}
                break;

            case R.id.checkBox15:
                if (checked)
                {selection.add("Eggs-6");}
                else
                {selection.remove("Eggs-6");}
                break;

            case R.id.checkBox16:
                if (checked)
                {selection.add("Eggs-12");}
                else
                {selection.remove("Eggs-12");}
                break;

            case R.id.checkBox17:
                if (checked)
                {selection.add("Eggs-24");}
                else
                {selection.remove("Eggs-24");}
                break;

            case R.id.checkBox18:
                if (checked)
                {selection.add("Butter-Small");}
                else
                {selection.remove("Butter-Small");}
                break;

            case R.id.checkBox19:
                if (checked)
                {selection.add("Butter-Medium");}
                else
                {selection.remove("Butter-Medium");}
                break;

            case R.id.checkBox20:
                if (checked)
                {selection.add("Butter-Large");}
                else
                {selection.remove("Butter-Large");}
                break;

            case R.id.checkBox21:
                if (checked)
                {selection.add("Salt-0.5lb");}
                else
                {selection.remove("Salt-0.5lb");}
                break;

            case R.id.checkBox22:
                if (checked)
                {selection.add("Salt-1lb");}
                else
                {selection.remove("Salt-1lb");}
                break;

            case R.id.checkBox23:
                if (checked)
                {selection.add("Salt-2lb");}
                else
                {selection.remove("Salt-2lb");}
                break;

            case R.id.checkBox24:
                if (checked)
                {selection.add("Sugar-0.5lb");}
                else
                {selection.remove("Sugar-0.5lb");}
                break;

            case R.id.checkBox25:
                if (checked)
                {selection.add("Sugar-1lb");}
                else
                {selection.remove("Sugar-1lb");}
                break;

            case R.id.checkBox26:
                if (checked)
                {selection.add("Sugar-2lb");}
                else
                {selection.remove("Sugar-2lb");}
                break;

            case R.id.checkBox27:
                if (checked)
                {selection.add("Juice-Orange-2L");}
                else
                {selection.remove("Juice-Orange-2L");}
                break;

            case R.id.checkBox28:
                if (checked)
                {selection.add("Juice-Orange-1L");}
                else
                {selection.remove("Juice-Orange-1L");}
                break;

            case R.id.checkBox29:
                if (checked)
                {selection.add("Juice-Grape-2L");}
                else
                {selection.remove("Juice-Grape-2L");}
                break;

            case R.id.checkBox30:
                if (checked)
                {selection.add("Juice-Apple-2L");}
                else
                {selection.remove("Juice-Apple-2L");}
                break;

            case R.id.checkBox31:
                if (checked)
                {selection.add("Chips-Small");}
                else
                {selection.remove("Chips-Small");}
                break;

            case R.id.checkBox32:
                if (checked)
                {selection.add("Chips-Medium");}
                else
                {selection.remove("Chips-Medium");}
                break;

            case R.id.checkBox33:
                if (checked)
                {selection.add("Chips-Large");}
                else
                {selection.remove("Chips-Large");}
                break;

            case R.id.checkBox34:
                if (checked)
                {selection.add("Cookies-Small");}
                else
                {selection.remove("Cookies-Small");}
                break;

            case R.id.checkBox35:
                if (checked)
                {selection.add("Cookies-Medium");}
                else
                {selection.remove("Cookies-Medium");}
                break;

            case R.id.checkBox36:
                if (checked)
                {selection.add("Cookies-Large");}
                else
                {selection.remove("Cookies-Large");}
                break;

            case R.id.checkBox37:
                if (checked)
                {selection.add("Wheat-2lb");}
                else
                {selection.remove("Wheat-2lb");}
                break;

            case R.id.checkBox38:
                if (checked)
                {selection.add("Wheat-4lb");}
                else
                {selection.remove("Wheat-4lb");}
                break;

            case R.id.checkBox39:
                if (checked)
                {selection.add("Wheat-10lb");}
                else
                {selection.remove("Wheat-10lb");}
                break;

            case R.id.checkBox40:
                if (checked)
                {selection.add("Rice-2lb");}
                else
                {selection.remove("Rice-2lb");}
                break;

            case R.id.checkBox41:
                if (checked)
                {selection.add("Rice-4lb");}
                else
                {selection.remove("Rice-4lb");}
                break;

            case R.id.checkBox42:
                if (checked)
                {selection.add("Rice-10lb");}
                else
                {selection.remove("Rice-10lb");}
                break;

            case R.id.checkBox43:
                if (checked)
                {selection.add("CornFlour-1lb");}
                else
                {selection.remove("CornFlour-1lb");}
                break;

            case R.id.checkBox44:
                if (checked)
                {selection.add("CornFlour-2lb");}
                else
                {selection.remove("CornFlour-2lb");}
                break;

            case R.id.checkBox45:
                if (checked)
                {selection.add("CornFlour-2.5lb");}
                else
                {selection.remove("CornFlour-2.5lb");}
                break;

            case R.id.checkBox46:
                if (checked)
                {selection.add("ChocolateBar-Reg");}
                else
                {selection.remove("ChocolateBar-Reg");}
                break;

            case R.id.checkBox47:
                if (checked)
                {selection.add("KitKat");}
                else
                {selection.remove("KitKat");}
                break;

            case R.id.checkBox48:
                if (checked)
                {selection.add("KitKat-Chunky");}
                else
                {selection.remove("KitKat-Chunky");}
                break;

            case R.id.checkBox49:
                if (checked)
                {selection.add("Twix");}
                else
                {selection.remove("Twix");}
                break;

            case R.id.checkBox50:
                if (checked)
                {selection.add("Cadbury-Small");}
                else
                {selection.remove("Cadbury-Small");}
                break;

            case R.id.checkBox51:
                if (checked)
                {selection.add("Cadbury-Medium");}
                else
                {selection.remove("Cadbury-Medium");}
                break;

            case R.id.checkBox53:
                if (checked)
                {selection.add("Cadbury-Large");}
                else
                {selection.remove("Cadbury-Large");}
                break;

            case R.id.checkBox54:
                if (checked)
                {selection.add("IceCream-Small");}
                else
                {selection.remove("IceCream-Small");}
                break;

            case R.id.checkBox55:
                if (checked)
                {selection.add("IceCream-Med");}
                else
                {selection.remove("IceCream-Med");}
                break;

            case R.id.checkBox56:
                if (checked)
                {selection.add("IceCream-Bar");}
                else
                {selection.remove("IceCream-Bar");}
                break;

            case R.id.checkBox57:
                if (checked)
                {selection.add("Meat-Goat-1lb");}
                else
                {selection.remove("Meat-Goat-1lb");}
                break;

            case R.id.checkBox58:
                if (checked)
                {selection.add("Meat-Goat-2lb");}
                else
                {selection.remove("Meat-Goat-2lb");}
                break;

            case R.id.checkBox59:
                if (checked)
                {selection.add("Meat-Chicken-2lb");}
                else
                {selection.remove("Meat-Chicken-2lb");}
                break;

            case R.id.checkBox60:
                if (checked)
                {selection.add("Meat-Chicken-4lb");}
                else
                {selection.remove("Meat-Chicken-4lb");}
                break;

            case R.id.checkBox61:
                if (checked)
                {selection.add("Meat-Beef-Steak");}
                else
                {selection.remove("Meat-Beef-Steak");}
                break;

            case R.id.checkBox62:
                if (checked)
                {selection.add("Meat-Beef-Steak-Prime");}
                else
                {selection.remove("Meat-Beef-Steak-Prime");}
                break;

            case R.id.checkBox63:
                if (checked)
                {selection.add("Meat-Halibut-Cut");}
                else
                {selection.remove("Meat-Halibut-Cut");}
                break;

            case R.id.checkBox64:
                if (checked)
                {selection.add("Meat-TunaCut-4lb");}
                else
                {selection.remove("Meat-TunaCut-4lb");}
                break;


            case R.id.checkBox65:
                if (checked)
                {selection.add("Meat-Pork-2lb");}
                else
                {selection.remove("Meat-Pork-2lb");}
                break;

            case R.id.checkBox66:
                if (checked)
                {selection.add("Meat-Pork-4lb");}
                else
                {selection.remove("Meat-Pork-4lb");}
                break;

            case R.id.checkBox67:
                if (checked)
                {selection.add("ButterCroissant(12pack)");}
                else
                {selection.remove("ButterCroissant(12pack)");}
                break;

            case R.id.checkBox68:
                if (checked)
                {selection.add("ChocolateCroissant(12pack)");}
                else
                {selection.remove("ChocolateCroissant(12pack)");}
                break;

            case R.id.checkBox69:
                if (checked)
                {selection.add("Mini-CupCakes(6pack)");}
                else
                {selection.remove("Mini-CupCakes(6pack)");}
                break;

            case R.id.checkBox70:
                if (checked)
                {selection.add("Mini-CupCakes(12pack)");}
                else
                {selection.remove("Mini-CupCakes(12pack)");}
                break;

            case R.id.checkBox71:
                if (checked)
                {selection.add("Vegetables");}
                else
                {selection.remove("Vegetables");}
                break;

            case R.id.checkBox72:
                if (checked)
                {selection.add("Fruits");}
                else
                {selection.remove("Fruits");}
                break;

            case R.id.checkBox73:
                if (checked)
                {selection.add("Shampoo");}
                else
                {selection.remove("Shampoo");}
                break;

            case R.id.checkBox74:
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

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
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
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

}


