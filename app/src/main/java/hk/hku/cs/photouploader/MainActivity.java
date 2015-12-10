package hk.hku.cs.photouploader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    Button btn_Cam;
    Button btn_Browse;
    Button btn_Gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        btn_Cam = (Button) findViewById(R.id.button1);
        btn_Cam.setOnClickListener(this);
      
        btn_Browse = (Button) findViewById(R.id.button3);
        btn_Browse.setOnClickListener(this);

        btn_Gallery = (Button) findViewById(R.id.button2);
        btn_Gallery.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.clear_cache) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //@Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            Intent intent = new Intent(this, CamActivity.class);
            startActivity(intent);
        }
       
        if (v.getId() == R.id.button3) {
            Intent intent = new Intent(this, BrowserActivity.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.button2) {
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);
        }
    }


}
