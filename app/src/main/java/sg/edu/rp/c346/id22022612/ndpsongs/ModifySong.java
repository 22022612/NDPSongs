package sg.edu.rp.c346.id22022612.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ModifySongActivity extends AppCompatActivity {

    EditText etSongTitle, etSinger, etYear;
    RadioGroup rdGrp;
    Button btnUpdate, btnDelete, btnCancel;
    Songs song;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_song);

        etSongTitle = findViewById(R.id.etSongTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rdGrp = findViewById(R.id.rdGrp);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        dbHelper = new DBHelper(ModifySongActivity.this);

        song = (Songs) getIntent().getSerializableExtra("song");
        etSongTitle.setText(song.getTitle());
        etSinger.setText(song.getSinger());
        etYear.setText(String.valueOf(song.getYear()));

        int rating = song.getRating();

        if (rating == 1) {
            rdGrp.check(R.id.radioButton);
        } else if (rating == 2) {
            rdGrp.check(R.id.radioButton2);
        } else if (rating == 3) {
            rdGrp.check(R.id.radioButton3);
        } else if (rating == 4) {
            rdGrp.check(R.id.radioButton4);
        } else if (rating == 5) {
            rdGrp.check(R.id.radioButton5);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String songTitle = etSongTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());

                int rating = 0;

                int selectedRadioButtonId = rdGrp.getCheckedRadioButtonId();
                if (selectedRadioButtonId == R.id.radioButton) {
                    rating = 1;
                } else if (selectedRadioButtonId == R.id.radioButton2) {
                    rating = 2;
                } else if (selectedRadioButtonId == R.id.radioButton3) {
                    rating = 3;
                } else if (selectedRadioButtonId == R.id.radioButton4) {
                    rating = 4;
                } else if (selectedRadioButtonId == R.id.radioButton5) {
                    rating = 5;
                }

                song.setTitle(songTitle);
                song.setSinger(singer);
                song.setYear(year);
                song.setRating(rating);

                boolean isUpdated = dbHelper.updateSong(song);

                if (isUpdated) {
                    Toast.makeText(ModifySongActivity.this, "Song updated!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ModifySongActivity.this, "Failed to update song.", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isDeleted = dbHelper.deleteSong(song.getId());

                if (isDeleted) {
                    Toast.makeText(ModifySongActivity.this, "Song deleted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ModifySongActivity.this, "Failed to delete song.", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
