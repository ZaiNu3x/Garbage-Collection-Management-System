package group.boysoverflowers.location_based_garbage_management_system;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import group.boysoverflowers.location_based_garbage_management_system.models.Bin;

public class AdminHomeActivity extends AppCompatActivity {

    private Button createBin;
    private Button showBins;
    private Button deleteBin;
    private Button updateBin;
    private DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        createBin = findViewById(R.id.createBinBtn);
        showBins = findViewById(R.id.showBinsBtn);
        deleteBin = findViewById(R.id.deleteBin);
        updateBin = findViewById(R.id.updateBinBtn);
        dbHelper = new DBHelper(this);

        createBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateBinActivity.class);
                startActivity(intent);
            }
        });

        showBins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Bin> bins = dbHelper.getAllBins();

                if(bins.size()==0){
                    Toast.makeText(getApplicationContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                    StringBuffer buffer = new StringBuffer();

                    for(Bin bin : bins) {
                        buffer.append("Id :"+bin.getId()+"\n");
                        buffer.append("City :"+bin.getCity()+"\n");
                        buffer.append("Barangay :"+bin.getBarangay()+"\n");
                        buffer.append("Street :"+bin.getStreet()+"\n");
                        buffer.append("Schedule :"+bin.getSchedule()+"\n\n");
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(AdminHomeActivity.this);

                    builder.setCancelable(true);
                    builder.setTitle("Bin Entries");
                    builder.setMessage(buffer.toString());
                    builder.show();
                    }
        });

        deleteBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DeleteBinActivity.class);
                startActivity(intent);
            }
        });

        updateBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateBinActivity.class);
                startActivity(intent);
            }
        });

    }
}
