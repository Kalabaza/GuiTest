package mx.iteso.msc.rodriguez.roberto.guitest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar that will contain a button on the upper part of the application
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Add the string array from the resources to the spinner, for that an adapter is needed for the strings
        // and access to the resources of the application
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                                                                 getResources().getStringArray(R.array.schoolingLevels));
        ((Spinner)findViewById(R.id.spinnerSchooling)).setAdapter(spinnerAdapter);

        // The auto complete textview needs also the input data, an adapter is needed and also access to the
        // resources to get the array
        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item,
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
        switch (item.getItemId())
        {
            // Currently only the save button is used, show a toast message on screen when this button is pressed
            case R.id.action_save:
                showToast();
                return true;
            // Default action to handle the Home/Up buttons
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void toggleChecker(View view) {
        // Toggle the checked text view, this changes it's current state (selected or not selected)
        ((CheckedTextView)view.findViewById(R.id.checkedTextView)).toggle();
    }

    private void showToast() {
        StringBuffer text = new StringBuffer();
        // Add the information to a string buffer that will be displayed in a toast
        text.append(getResources().getString(R.string.name)).append(": ").append(((EditText) findViewById(R.id.textName)).getText()).append("\n");
        text.append(getResources().getString(R.string.phone)).append(": ").append(((EditText) findViewById(R.id.textPhone)).getText()).append("\n");
        text.append(getResources().getString(R.string.schooling)).append(": ").append(((Spinner) findViewById(R.id.spinnerSchooling)).getSelectedItem()).append("\n");
        text.append(getResources().getString(R.string.gender)).append(": ").append(getResources().getString(((RadioButton) findViewById(R.id.radioMale)).isChecked() ? R.string.male : R.string.female)).append("\n");
        text.append(getResources().getString(R.string.book)).append(": ").append(((AutoCompleteTextView) findViewById(R.id.autoTextBooks)).getEditableText()).append("\n");
        text.append(getResources().getString(R.string.sport)).append(": ").append(((CheckedTextView) findViewById(R.id.checkedTextView)).isChecked() ? R.string.yes : R.string.no).append("\n");
        // Put the string buffer int the toast message and display it
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();
        // Call the cleanGUI method to clear all the fields on the screen
        clean();
    }

    public void cleanGUI(View view) {
        // The dialog requires a context, send the activity context
        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                                       // Add a title to the dialog
                                       .setTitle(getResources().getString(R.string.clean))
                                       // Add the message to the dialog
                                       .setMessage(getResources().getString(R.string.cleanDialog))
                                       // Set the listener for the positive button
                                       .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                                           @Override
                                           public void onClick(DialogInterface dialog, int which) {
                                               // If a user presses the yes button clear the GUI elements
                                               clean();
                                           }
                                       })
                                       // Set the listener for the negative button
                                       .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                                           @Override
                                           public void onClick(DialogInterface dialog, int which) {
                                               // In case no is pressed don't do anything
                                           }
                                       })
                                       // Create a dialog with the previous characteristics
                                       .create();
        // Add a listener to change the color of the buttons in the dialog
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                // Change the positive button to green color with white text
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setBackgroundColor(0xFF609000);
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.WHITE);
                // Change the negative button to red color with white text
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setBackgroundColor(0xFFC00000);
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.WHITE);
            }
        });
        // Show the dialog on the screen
        dialog.show();
    }

    private void clean() {
        // Clear all the elements of the GUI with default or empty values
        ((EditText)findViewById(R.id.textName)).getText().clear();
        ((EditText)findViewById(R.id.textPhone)).getText().clear();
        ((Spinner)findViewById(R.id.spinnerSchooling)).setSelection(0);
        ((RadioButton)findViewById(R.id.radioMale)).setChecked(true);
        ((AutoCompleteTextView)findViewById(R.id.autoTextBooks)).getEditableText().clear();
        ((CheckedTextView)findViewById(R.id.checkedTextView)).setChecked(false);
    }
}
