package org.eu.fondatore.freeotp;

import org.eu.fondatore.freeotp.otp.TOTP;
import org.eu.fondatore.freeotp.utils.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_SERVICE_REQUEST = 1;
    private static final long REFRESH_INTERVAL = 5000;

    private DBHelper dbHelper;
    private ListView otpListView;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        otpListView = findViewById(R.id.otpListView);
        handler = new Handler();

        Button addServiceButton = findViewById(R.id.addOtpButton);
        addServiceButton.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, AddServiceActivity.class);
                startActivityForResult(intent, ADD_SERVICE_REQUEST);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        handler.postDelayed(() -> {
            refreshCodes();
            handler.postDelayed(() -> runOnUiThread(this::refreshCodes), REFRESH_INTERVAL);
        }, REFRESH_INTERVAL);

        refreshCodes();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_SERVICE_REQUEST && resultCode == Activity.RESULT_OK) {
            refreshCodes();
        }
    }

    private void refreshCodes() {
        List<Pair<String, String>> services = dbHelper.getAllServices();
        List<Pair<String, String>> displayList = new ArrayList<>();

        for (Pair<String, String> service : services) {
            String serviceName = service.first;
            String secretKey = service.second;
            String totpCode = new TOTP(secretKey).getCode();
            displayList.add(new Pair<>(serviceName, totpCode));
        }

        TOTPViewAdapter adapter = new TOTPViewAdapter(this, displayList);
        otpListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
