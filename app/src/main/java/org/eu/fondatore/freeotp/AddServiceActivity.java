package org.eu.fondatore.freeotp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import org.eu.fondatore.freeotp.utils.DBHelper;

public class AddServiceActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private EditText editTextServiceName;
    private EditText editTextSecretKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        dbHelper = new DBHelper(this);
        editTextServiceName = findViewById(R.id.editTextServiceName);
        editTextSecretKey = findViewById(R.id.editTextSecretKey);

        Button confirmButton = findViewById(R.id.buttonAddService);
        confirmButton.setOnClickListener(v -> {
            String serviceName = editTextServiceName.getText().toString();
            String secretKey = editTextSecretKey.getText().toString();

            dbHelper.addService(serviceName, secretKey);

            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });
    }
}
