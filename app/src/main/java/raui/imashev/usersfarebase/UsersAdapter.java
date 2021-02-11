package raui.imashev.usersfarebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    private List<User> users;

    public UsersAdapter() {
        this.users = new ArrayList<>();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.textViewName.setText(user.getName());
        holder.textViewLastname.setText(user.getLastname());
        holder.textViewAge.setText(String.valueOf(user.getAge()));
        holder.textViewSex.setText(user.getSex());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    static class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewName;
        private final TextView textViewLastname;
        private final TextView textViewAge;
        private final TextView textViewSex;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLastname = itemView.findViewById(R.id.textViewLastName);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            textViewSex = itemView.findViewById(R.id.textViewSex);
        }
    }
}
