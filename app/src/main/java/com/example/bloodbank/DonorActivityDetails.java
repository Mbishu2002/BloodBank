package com.example.bloodbank;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DonorDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_details);

        // Get donor details from intent
        BloodDonor donor = getIntent().getParcelableExtra("donor");

        // Populate TextViews with donor details
        if (donor != null) {
            TextView nameTextView = findViewById(R.id.nameTextView);
            nameTextView.setText("Name: " + donor.getName());

            TextView bloodGroupTextView = findViewById(R.id.bloodGroupTextView);
            bloodGroupTextView.setText("Blood Group: " + donor.getBloodGroup());

            // Populate other TextViews with remaining donor details
            // ...
        }
    }
}
