package at.htlkaindorf.studentmanagementfinal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentModel extends BaseAdapter {
    private List<Student> studentList;
    private LayoutInflater inflater;
    private TextView tvName, tvSchoolClass, tvBirthday;
    private ImageView icon;

    public StudentModel(Context context) {
        studentList = new ArrayList<>();
        inflater = LayoutInflater.from(context);

        // If LocalDate.parse makes you require api levels, set "minSdk 29" in build.grade (Module app)

        Student erika = createStudent("Erika Mustermann", "3CHIF", LocalDate.parse("2005-02-01"), R.drawable.icon, context);
        studentList.add(erika);

        Student max = createStudent("Max Mustermann", "3CHIF", LocalDate.parse("2005-01-01"), R.drawable.icon, context);
        studentList.add(max);
    }

    // Create new student

    public Student createStudent(String name, String schoolClass, LocalDate birthday, int imageId, Context context) {
        Student s = new Student();
        s.setName(name);
        s.setSchoolClass(schoolClass);
        s.setBirthday(birthday);

        InputStream is = context.getResources().openRawResource(imageId);
        Bitmap bm = BitmapFactory.decodeStream(is);
        s.setIcon(bm);

        return s;
    }

    // Override methods

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return studentList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            // Inflate layout file
            convertView = inflater.inflate(R.layout.student_row,parent,false);

            icon = convertView.findViewById(R.id.icon);
            tvName = convertView.findViewById(R.id.tvName);
            tvSchoolClass = convertView.findViewById(R.id.tvSchoolClass);
            tvBirthday = convertView.findViewById(R.id.tvBirthday);

            convertView.setTag(R.id.icon, icon);
            convertView.setTag(R.id.tvName, tvName);
            convertView.setTag(R.id.tvSchoolClass, tvSchoolClass);
            convertView.setTag(R.id.tvBirthday, tvBirthday);
        } else {
            icon = (ImageView) convertView.getTag(R.id.icon);
            tvName = (TextView) convertView.getTag(R.id.tvName);
            tvSchoolClass = (TextView) convertView.getTag(R.id.tvSchoolClass);
            tvBirthday = (TextView) convertView.getTag(R.id.tvBirthday);
        }

        Student s = studentList.get(position);

        icon.setImageBitmap(s.getIcon());
        tvName.setText(s.getName());
        tvSchoolClass.setText(s.getSchoolClass());
        tvBirthday.setText(s.getBirthday().toString());

        return convertView;
    }

    // Custom implementation

    public void remove(int index) {
        studentList.remove(index);
        this.notifyDataSetChanged();
    }

    public void add(Student student) {
        studentList.add(student);
        this.notifyDataSetChanged();
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void sortStudentList(Comparator<? super Student> c) {
        studentList.sort(c);
        this.notifyDataSetChanged();
    }
}
