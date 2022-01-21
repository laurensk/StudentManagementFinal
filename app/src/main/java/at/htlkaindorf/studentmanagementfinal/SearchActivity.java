package at.htlkaindorf.studentmanagementfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText etSearch;
    private Button btSearch, btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Action bar title
        getSupportActionBar().setTitle("Schüler:innen durchsuchen");

        etSearch = findViewById(R.id.etSearch);
        btSearch = findViewById(R.id.btSearch);
        btBack = findViewById(R.id.btBack);

        btSearch.setOnClickListener(view -> {

            String searchText = etSearch.getText().toString();

            List<Student> studentList = (List<Student>) getIntent().getSerializableExtra("list");
            List<Student> matches = new ArrayList<>();

            studentList.forEach(student -> {
                String target = student.getName() + " " + student.getSchoolClass();
                if (target.toLowerCase().contains(searchText.toLowerCase())) matches.add(student);
            });

            if (matches.size() > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("Gefundene Einträge:");
                sb.append("\n");

                matches.forEach(student -> {
                    sb.append("\n");
                    sb.append(student.getName());
                    sb.append(" - ");
                    sb.append(student.getSchoolClass());
                    sb.append(" - ");
                    sb.append(student.getBirthday().toString());
                });

                Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_LONG).show();
            }
        });

        btBack.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}