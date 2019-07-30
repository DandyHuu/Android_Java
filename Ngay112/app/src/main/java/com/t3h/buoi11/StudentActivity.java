package com.t3h.buoi11;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.t3h.buoi11.dao.AppDatabase;
import com.t3h.buoi11.model.Student;

public class StudentActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtScore;
    private EditText edtSubject;
    private EditText edNoidung, edTacgia;

    private static final String EXTRA_STUDENT = "extra_student";
    private Student student;

    public static Intent newInstance(Context context, Student student) {
        Intent intent = new Intent(context, StudentActivity.class);
        intent.putExtra(EXTRA_STUDENT, student);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        initViews();

        getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);
    }

    private void initViews() {
        edtName = findViewById(R.id.edt_name);
        edtScore = findViewById(R.id.edt_score);
        edtSubject = findViewById(R.id.edt_subject);
        edNoidung = findViewById(R.id.edt_noidung);
        edTacgia = findViewById(R.id.edt_tacgia);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.student_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_done:
                saveStudent();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveStudent() {
        String name = edtName.getText().toString();
        String subject = edtSubject.getText().toString();
        String score = edtScore.getText().toString();
        String noidung = edNoidung.getText().toString();
        String tacgia = edTacgia.getText().toString();
        if (name.isEmpty()
                || subject.isEmpty()
                || score.isEmpty()) {
            Toast.makeText(this, "Data invalid", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean isInsert = student == null;
        if (student == null) {
            student = new Student();
        }
        student.setName(name);
        student.setDate(score);
        student.setSubject(subject);
        student.setNoidung(noidung);
        student.setTacgia(tacgia);
        if (isInsert) {
            AppDatabase.getInstance(this)
                    .getStudentDao()
                    .insert(student);
        }
        setResult(RESULT_OK);
        finish();
    }
}
