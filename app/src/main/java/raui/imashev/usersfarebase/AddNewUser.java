package raui.imashev.usersfarebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.firebase.firestore.FirebaseFirestore;

public class AddNewUser extends AppCompatActivity {
    FirebaseFirestore db;
    EditText editTextName;
    EditText editTextLastName;
    EditText editTextAge;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);
        db = FirebaseFirestore.getInstance();
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAge = findViewById(R.id.editTextAge);
        radioGroup = findViewById(R.id.radioGroup);
    }

    //Реализовано добавление в БД
    public void onClickSaveNewUser(View view) {
        String name = editTextName.getText().toString().trim();
        String lastname = editTextLastName.getText().toString().trim();
        int age = Integer.parseInt(editTextAge.getText().toString().trim());
        int intSex = radioGroup.getCheckedRadioButtonId();
        String sex;
        if (intSex == R.id.radioButtonMale) {
            sex = "Male";
        } else {
            sex = "Female";
        }
        Log.i("прогр",sex);
        User user = new User(name,lastname,age,sex);
        db.collection("users").add(user);
        Intent intent = new Intent(this,UsersListActivity.class);
        startActivity(intent);
    }
}