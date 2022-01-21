package at.htlkaindorf.studentmanagementfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button btDelete, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        // Action bar title
        getSupportActionBar().setTitle("Schüler:in löschen");

        spinner = findViewById(R.id.spinner);
        btDelete = findViewById(R.id.btDelete);
        btCancel = findViewById(R.id.btCancel);

        List<Student> studentList = (List<Student>) getIntent().getSerializableExtra("list");
        String[] names = studentList.stream().map(Student::getName).toArray(String[]::new);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, names);
        spinner.setAdapter(adapter);

        btDelete.setOnClickListener(view -> {
            int sel = spinner.getSelectedItemPosition();
            Intent data = new Intent();
            data.putExtra("index", sel);
            data.putExtra("activity", "DELETE");
            setResult(RESULT_OK, data);
            finish();
        });

        btCancel.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}