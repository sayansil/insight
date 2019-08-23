package iedc_beast.yolo_reciever;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private static String[] xItems = {"Gun", "Knife", "Girl"};
    private static final String url = "insight-iedc.eu-gb.cf.appdomain.cloud";
    private static final int delay = 2000; //milliseconds
    private Handler handler;

    private FirebaseAuth mAuth;

    private static final String TAG = "Main Activity";

    class GetRequest extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params){
            String urllink = "https://" + url + "/" + params[0];
            Log.e("Link ", urllink);

            String tmp=";;;";

            try {
                URL _url = new URL(urllink);
                HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                tmp=in.readLine();

                in.close();

            } catch(Exception e) {
                Log.e("Send Request", e.toString());
            }

            return tmp;
        }

        protected void onPostExecute(String result) {
            ArrayList<Item> mlist = new ArrayList<>();
            for(Object str:result.split("~")){
                String[] itemlist = str.toString().split(";");
                int tempImg = R.drawable.ok;

                if(itemlist.length >= 4 && itemlist[0].length() > 0) {
                    for (String xItem : xItems)
                        if (xItem.equalsIgnoreCase(itemlist[0].trim()))
                            tempImg = R.drawable.cross;

                    if (itemlist[0].trim().length() > 10)
                        itemlist[0] = itemlist[0].trim().substring(0, 9) + "...";

                    mlist.add(new Item(
                            tempImg,
                            itemlist[0].trim(),
                            "Quantity: " + itemlist[1].trim(),
                            itemlist[2],
                            itemlist[3]
                    ));

                    ItemAdapter mAdapter = new ItemAdapter(getApplicationContext(), mlist);
                    listView.setAdapter(mAdapter);
                }
            }
        }
    }

    private void startFetching() {
        handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                GetRequest requestor = new GetRequest();

                requestor.execute("index.php?var1=ban2");
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.items_found);
        ImageButton logoutBtn = findViewById(R.id.logout);
        Button historyBtn = findViewById(R.id.history);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            goBackToLogin();
        }

        startFetching();

        logoutBtn.setOnClickListener((View v) -> {
            handler.removeCallbacksAndMessages(null);
            mAuth.signOut();
            goBackToLogin();
        });

        historyBtn.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
    }

    private void goBackToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
