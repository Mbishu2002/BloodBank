package com.example.bloodbank;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<BloodDonor> donorList;
    private BloodDonorAdapter adapter;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load data into the RecyclerView
        loadDataIntoList();

        // Find the plus button and set its click listener
        ImageView plusButton = findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the plus button click (e.g., open a new activity)
                Toast.makeText(MainActivity.this, "Plus button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Find the three-dot menu button and set its click listener
        ImageView menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow == null || !popupWindow.isShowing()) {
                    showPopupWindow(v);
                } else {
                    dismissPopupWindow();
                }
            }
        });

        // Find the search button and set its click listener
        ImageButton searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the search button click
                EditText searchEditText = findViewById(R.id.searchEditText);
                String searchQuery = searchEditText.getText().toString();
                // Perform search operation with the searchQuery
                Toast.makeText(MainActivity.this, "Search for: " + searchQuery, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to generate random BloodDonor objects and add them to a list
    private List<BloodDonor> generateRandomDonors(int count) {
        List<BloodDonor> donorList = new ArrayList<>();
        String[] names = {"John Doe", "Jane Smith", "Michael Johnson", "Emily Brown", "Daniel Davis"};
        String[] bloodGroups = {"A+", "B+", "AB+", "O+", "A-", "B-", "AB-", "O-"};
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String name = names[random.nextInt(names.length)];
            String bloodGroup = bloodGroups[random.nextInt(bloodGroups.length)];
            int age = random.nextInt(50) + 20; // Random age between 20 and 70
            String email = name.replaceAll("\\s", "").toLowerCase() + "@example.com";
            String phoneNumber = "(" + (random.nextInt(800) + 100) + ") " + (random.nextInt(800) + 100) + "-" + (random.nextInt(9000) + 1000);
            donorList.add(new BloodDonor(name, bloodGroup, age, email, phoneNumber));
        }

        return donorList;
    }

    // Load data into the RecyclerView
    private void loadDataIntoList() {
        donorList = generateRandomDonors(10); // Change the count as needed
        adapter = new BloodDonorAdapter(donorList);
        RecyclerView recyclerView = findViewById(R.id.bloodGroupRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void showPopupWindow(View anchorView) {
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_layout, null);

        // Create a PopupWindow object
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        // Set the animation style
        popupWindow.setAnimationStyle(R.style.PopupAnimation);

        // Position the popup window at the top right corner
        popupWindow.showAsDropDown(anchorView, 0, 0);
    }

    private void dismissPopupWindow() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }
}
