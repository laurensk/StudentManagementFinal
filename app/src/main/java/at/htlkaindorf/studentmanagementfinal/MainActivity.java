package at.htlkaindorf.studentmanagementfinal;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private StudentModel adapter;
    private Button btSortByName, btSortBySchoolClass, btSortByBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btSortByName = findViewById(R.id.btSortByName);
        btSortBySchoolClass = findViewById(R.id.btSortBySchoolClass);
        btSortByBirthday = findViewById(R.id.btSortByBirthday);

        // List view

        adapter = new StudentModel(this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((l, v, position, id) -> {
            Student s = (Student) adapter.getItem(position);
            Toast.makeText(getApplicationContext(), "Du hast " + s.getName() + " ausgewählt.", Toast.LENGTH_SHORT).show();
        });

        // Sort buttons

        btSortByName.setOnClickListener(view -> adapter.sortStudentList(Comparator.comparing(Student::getName)));

        btSortBySchoolClass.setOnClickListener(view -> adapter.sortStudentList(Comparator.comparing(Student::getSchoolClass)));

        btSortByBirthday.setOnClickListener(view -> adapter.sortStudentList(Comparator.comparing(Student::getBirthday)));
    }

    // Start activity for result

    private ActivityResultLauncher<Intent> mStartForResult =
            this.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    String activity = data.getStringExtra("activity");

                    if (activity.equals("DELETE")) {
                        int index = data.getIntExtra("index", -1);
                        adapter.remove(index);
                    } else if (activity.equals("ADD")) {
                        Student s = adapter.createStudent(
                                data.getStringExtra("name"),
                                data.getStringExtra("schoolClass"),
                                LocalDate.parse(data.getStringExtra("birthday")),
                                R.drawable.icon,
                                getApplicationContext());

                        adapter.add(s);
                    }
                }
            });

    // Menu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAdd:
                mStartForResult.launch(new Intent(MainActivity.this, AddActivity.class));
                break;
            case R.id.menuSearch:
                Intent searchIntent = new Intent(this, SearchActivity.class);
                searchIntent.putExtra("list", (Serializable) adapter.getStudentList());
                mStartForResult.launch(searchIntent);
                break;
            case R.id.menuDelete:
                Intent deleteIntent = new Intent(this, DeleteActivity.class);
                deleteIntent.putExtra("list", (Serializable) adapter.getStudentList());
                mStartForResult.launch(deleteIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}