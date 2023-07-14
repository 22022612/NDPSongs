package sg.edu.rp.c346.id22022612.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowSongActivity extends AppCompatActivity {

    ListView lvResults;
    ArrayAdapter<Songs> arrayAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_song);

        lvResults = findViewById(R.id.lvResults);

        dbHelper = new DBHelper(ShowSongActivity.this);

        ArrayList<Songs> songsList = dbHelper.getSongs();

        arrayAdapter = new ArrayAdapter<>(ShowSongActivity.this, android.R.layout.simple_list_item_1, songsList);
        lvResults.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Songs> songsList = dbHelper.getSongs();
        arrayAdapter.clear();
        arrayAdapter.addAll(songsList);
        arrayAdapter.notifyDataSetChanged();
    }
}
