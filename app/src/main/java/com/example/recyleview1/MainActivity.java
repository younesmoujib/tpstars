package com.example.recyleview1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.recyleview1.adapter.StarAdapter;
import com.example.recyleview1.beans.Star;
import com.example.recyleview1.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<Star> stars;
    public StarAdapter starAdapter = null;
    public RecyclerView recyclerView;
    private Toolbar myToolbar;
    private StarService service;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        stars = service.findAll();
        starAdapter = new StarAdapter(this, stars);
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void filterList(String s) {
        List<Star> filtredList = new ArrayList<>();
        for (Star star : stars) {
            if (star.getName().toLowerCase().startsWith(s.toLowerCase().trim())) {
                filtredList.add(star);
            }
            if (filtredList.isEmpty()) {
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
            }
            starAdapter.setFiltredList(filtredList);
        }
    }

    public void init() {

        service.create(new Star("kate bosworth", "https://www.stars-photos.com/image.php?id=801", 3.5f));
        service.create(new Star("george clooney", "https://www.stars-photos.com/image.php?id=1191", 3));
        service.create(new Star("michelle rodriguez", "https://www.stars-photos.com/image.php?id=1120", 5));
        service.create(new Star("george clooney", "https://www.stars-photos.com/image.php?id=1193", 1));
        service.create(new Star("louise bouroin", "https://www.stars-photos.com/image.php?id=1185", 5));
        service.create(new Star("louise bouroin", "https://www.stars-photos.com/image.php?id=1184", 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.star) {
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);


    }


}
