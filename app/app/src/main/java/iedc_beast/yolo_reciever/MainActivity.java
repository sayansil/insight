package iedc_beast.yolo_reciever;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ItemAdapter mAdapter;
    private String[] xItems = {"Gun", "Knife", "Girl"};
    private String url = "insight-iedc.eu-gb.cf.appdomain.cloud";

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

                //while((tmp=in.readLine()) != null) {
                //Log.d("Found ", tmp);
                //allitems.add(tmp);
                //}

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

                    mAdapter = new ItemAdapter(getApplicationContext(), mlist);
                    listView.setAdapter(mAdapter);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.iedcmembers);


        final Handler handler = new Handler();
        final int delay = 2000; //milliseconds

        handler.postDelayed(new Runnable(){
            public void run(){
                GetRequest requestor = new GetRequest();

                requestor.execute("index.php?var1=ban2");
                handler.postDelayed(this, delay);
            }
        }, delay);

    }
}
