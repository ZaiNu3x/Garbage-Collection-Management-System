package group.boysoverflowers.location_based_garbage_management_system;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteBinActivity extends AppCompatActivity {

    private EditText binId;
    private Button deleteButton;
    private DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_bin);

        binId = findViewById(R.id.binIdEditText);
        deleteButton = findViewById(R.id.deleteBtn);
        dbHelper = new DBHelper(this);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.deleteBinById(binId.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Bin deleted successfully!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Bin does not Exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
