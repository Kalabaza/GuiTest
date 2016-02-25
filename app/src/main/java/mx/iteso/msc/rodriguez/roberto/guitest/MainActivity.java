package mx.iteso.msc.rodriguez.roberto.guitest;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Add the string array from the resources to the spinner, for that an adapter is needed for the strings
        // and access to the resources of the application
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                                                                      getResources().getStringArray(R.array.schoolingLevels));
        ((Spinner)findViewById(R.id.spinnerSchooling)).setAdapter(spinnerAdapter);

        // The auto complete textview needs also the input data, an adapter is needed and also acces to the
        // resources to get the array
        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item,
                                                                            getResources().getStringArray(R.array.books));
        ((AutoCompleteTextView)findViewById(R.id.autoTextBooks)).setAdapter(autoCompleteAdapter);
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
        if (item.getItemId() == R.id.action_save) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
