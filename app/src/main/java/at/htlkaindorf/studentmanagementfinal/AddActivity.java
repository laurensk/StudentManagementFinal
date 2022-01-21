package at.htlkaindorf.studentmanagementfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.time.LocalDate;

public class AddActivity extends AppCompatActivity {

    private Button btAdd, btCancel;
    private EditText etName, etSchoolClass;
    private DatePicker dpBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Action bar title
        getSupportActionBar().setTitle("Schüler:in hinzufügen");

        btAdd = findViewById(R.id.btAdd);
        btCancel = findViewById(R.id.btCancel);
        etName = findViewById(R.id.etName);
        etSchoolClass = findViewById(R.id.etSchoolClass);
        dpBirthday = findViewById(R.id.dpBirthday);

        btAdd.setOnClickListener(view -> {
            Intent data = new Intent();

            data.putExtra("activity","ADD");
            data.putExtra("name", etName.getText().toString());
            data.putExtra("schoolClass", etSchoolClass.getText().toString());

            LocalDate date;

            try {
                date = LocalDate.of(dpBirthday.getYear(), dpBirthday.getMonth() + 1, dpBirthday.getDayOfMonth());
            } catch (Exception e) {
                date = LocalDate.now();
            }

            data.putExtra("birthday", date.toString());

            setResult(RESULT_OK, data);
            finish();
        });

        btCancel.setOnClickListener(view -> {
            setResult(RESULT_CANCELED, null);
            finish();
        });
    }
}