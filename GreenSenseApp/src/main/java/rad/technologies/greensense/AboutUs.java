package rad.technologies.greensense;
//R.A.D. Technologies

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class AboutUs extends AppCompatActivity {

    private static final int pic_id = 123;

    //sign out method
    public void signOut() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();
        // this listener will be called when there is change in firebase user session
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("About Us");
    }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            switch (item.getItemId()) {
                // action with ID action_refresh was selected
                case R.id.action_picture:
                    try {
                        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(camera_intent, pic_id);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(this, R.string.featErr, Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    break;
                case R.id.action_help:
                    try {
                        Intent browserHelp = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wikihow.com/Maintain-a-Greenhouse"));
                        startActivity(browserHelp);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(this, R.string.wrongErr, Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    break;
                // action with ID action_settings was selected
                case R.id.action_settings:
                    try {
                        startActivity(new Intent(this, SettingsActivity.class));
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(this, R.string.featErr, Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    break;
                case R.id.action_signout:
                    try {
                        signOut();
                        Intent intent = new Intent(AboutUs.this, Login.class);
                        startActivity(intent);
                        finish();
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(this, R.string.wrongErr, Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    break;
                case R.id.action_about:
                    try {
                        startActivity(new Intent(this,AboutUs.class));
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(this, R.string.wrongErr, Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    break;
                default:
                    return super.onOptionsItemSelected(item);
            }
            return true;
        }
    }