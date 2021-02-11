package raui.imashev.usersfarebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class UsersListActivity extends AppCompatActivity {

    FirebaseFirestore db;
    List<User> users = new ArrayList<>();
    RecyclerView recyclerView;
    UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_list_activity);
        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new UsersAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    public void onClickAddNewUser(View view) {
        Intent intent = new Intent(this, AddNewUser.class);
        startActivity(intent);
    }

    //В onResume вызывается метод для загрузки данных из БД
    @Override
    protected void onResume() {
        super.onResume();
        db.collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        users.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User user = new User(String.valueOf(document.getData().get("name")),
                                    String.valueOf(document.getData().get("lastname")),
                                    Integer.parseInt(String.valueOf(document.getData().get("age"))),
                                    String.valueOf(document.getData().get("sex")));
                            users.add(user);
                        }
                        adapter.setUsers(users);
                    } else {
                        Log.d("sgag", "Error getting documents: ", task.getException());
                    }
                });
    }


}