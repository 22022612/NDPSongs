package sg.edu.rp.c346.id22022612.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etSongTitle,etSinger,etYear;
    RadioGroup rdGrp;
    Button btnInsert, btnShow;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        etSongTitle = findViewById(R.id.etSongTitle);

        rdGrp = findViewById(R.id.rdGrp);

        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);

        DBHelper dbHelper = new DBHelper(MainActivity.this);



        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean isEmpty;

                // Get inputs from EditTexts and convert to string/int
                String songTitle = etSongTitle.getText().toString();
                String songSingers = etSinger.getText().toString();
                String songYearString = etYear.getText().toString();

                // Checks if EditTexts are filled in or not. This is to prevent crashing if user presses insert when not all fields are inputted
                if (songTitle.isEmpty() || songSingers.isEmpty() || songYearString.isEmpty())
                    isEmpty = true;
                else {
                    isEmpty = false;
                }

                // Get value of stars in the radio button group
                int starBtn = rdGrp.getCheckedRadioButtonId();
                int starValue;

                if (starBtn ==  R.id.Rb1)
                    starValue = 1;
                else if (starBtn == R.id.Rb2)
                    starValue = 2;
                else if (starBtn == R.id.Rb3)
                    starValue = 3;
                else if (starBtn == R.id.Rb4)
                    starValue = 4;
                else if (starBtn == R.id.Rb5)
                    starValue = 5;
                else
                    starValue = 0;

                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                if (!isEmpty) {
                    int songYear = Integer.parseInt(songYearString);
                    db.insertSong(songTitle, songSingers, songYear, starValue);
                    // Add toast notification
                    Toast.makeText(MainActivity.this, "Song added!", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "Error. Please enter input on all fields.", Toast.LENGTH_SHORT).show();
                // Reset inputs on EditText
                etSongTitle.setText("");
                etSinger.setText("");
                etYear.setText("");
                rdGrp.clearCheck();

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowSongActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuItemInsert) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menuItemShow) {
            Intent intent = new Intent(MainActivity.this, ShowSongActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
