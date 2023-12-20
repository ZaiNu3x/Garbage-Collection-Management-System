package group.boysoverflowers.location_based_garbage_management_system;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import group.boysoverflowers.location_based_garbage_management_system.models.Bin;

public class UpdateBinActivity extends AppCompatActivity {

    private EditText id;
    private EditText updatedCity;
    private EditText updatedBarangay;
    private EditText updatedStreet;
    private EditText updatedSchedule;
    private Button updateButton;
    private DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bin);

        id = findViewById(R.id.idEditText);
        updatedCity = findViewById(R.id.updatedCityEditText);
        updatedBarangay = findViewById(R.id.updatedBarangayEditText);
        updatedStreet = findViewById(R.id.updatedStreetEditText);
        updatedSchedule = findViewById(R.id.updatedScheduleEditText);
        updateButton = findViewById(R.id.updateButton);

        dbHelper = new DBHelper(this);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bin updatedBin = new Bin(Integer.parseInt(id.getText().toString()) ,updatedCity.getText().toString(), updatedBarangay.getText().toString(),
                        updatedStreet.getText().toString(), updatedSchedule.getText().toString());

                if (dbHelper.updateBin(updatedBin)) {
                    Toast.makeText(getApplicationContext(), "Bin updated Successfully!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Bin does not exits!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
