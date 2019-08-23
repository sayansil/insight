package iedc_beast.yolo_reciever;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryActivity extends AppCompatActivity {

    private static final String TAG = "History";

    private static String[] xItems = {"gun", "knife", "girl"};

    private ListView listView;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = findViewById(R.id.items_history);
        ArrayList<Item> mlist = new ArrayList<>();


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            goBackToLogin();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

        String historyURL = "users/" + currentUser.getUid() + "/history";
        db = FirebaseFirestore.getInstance();
        db.collection(historyURL)
                .get()
                .addOnCompleteListener((@NonNull Task<QuerySnapshot> task) -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            ArrayList<Long> quantity = (ArrayList<Long>) document.get("quantity");
                            ArrayList<String> objects = (ArrayList<String>) document.get("objects");
                            Date timestamp = document.getTimestamp("timestamp").toDate();
                            String date = dateFormat.format(timestamp);
                            String time = timeFormat.format(timestamp);

                            for (int i=0; i<objects.size(); i++) {
                                String object = objects.get(i).trim().toLowerCase();
                                String qty = "Quantity: " + String.valueOf(quantity.get(i)).trim();

                                int tempImg = R.drawable.ok;

                                if (object.length() > 0)
                                    for (String xItem: xItems) {
                                        if (xItem.equalsIgnoreCase(object)) {
                                            tempImg = R.drawable.cross;
                                        }
                                    }

                                mlist.add(new Item(
                                        tempImg,
                                        object,
                                        qty,
                                        date,
                                        time
                                ));
                            }
                        }
                        ItemAdapter mAdapter = new ItemAdapter(getApplicationContext(), mlist);
                        listView.setAdapter(mAdapter);
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    private void goBackToLogin() {
        Intent intent = new Intent(HistoryActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
