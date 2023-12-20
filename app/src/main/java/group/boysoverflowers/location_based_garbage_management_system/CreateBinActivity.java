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

public class CreateBinActivity extends AppCompatActivity {
    private EditText city;
    private EditText barangay;
    private EditText street;
    private EditText schedule;
    private Button create;
    private DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bin);

        city = findViewById(R.id.updatedCityEditText);
        barangay = findViewById(R.id.updatedBarangayEditText);
        street = findViewById(R.id.updatedStreetEditText);
        schedule = findViewById(R.id.updatedScheduleEditText);
        create = findViewById(R.id.updateButton);
        dbHelper = new DBHelper(this);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bin bin = new Bin();

                bin.setCity(city.getText().toString());
                bin.setBarangay(barangay.getText().toString());
                bin.setStreet(street.getText().toString());
                bin.setSchedule(schedule.getText().toString());


                if(bin.getCity().length() >= 5 && bin.getBarangay().length() >= 5 &&
                        bin.getStreet().length() >= 5 && bin.getSchedule().length() >= 5) {
                    if(dbHelper.checkBinIfExists(bin)) {
                        Toast.makeText(getApplicationContext(), "Data already exists!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(dbHelper.insertDataToBins(bin)) {
                            Toast.makeText(getApplicationContext(), "Data Inserted Successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Data Failed to insert!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please Enter atleast 5 letters per value!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
