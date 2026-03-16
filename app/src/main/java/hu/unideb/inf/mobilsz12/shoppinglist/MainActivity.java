package hu.unideb.inf.mobilsz12.shoppinglist;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private final String sharedPrefFile = "shrprf";
    private static final String SAVESTATE_ITEMS_TEXT = "hu.unideb.inf.mobilsz12.shoppinglist.MainActivity.SAVESTATE_ITEMS_TEXT.asdfjhbfhwefjbkjdsbdfs";
    private TextView itemsTextView;

    private ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            if (itemsTextView.getText().toString().equals(getString(R.string.empty_list)))
                                itemsTextView.setText("");
                            itemsTextView.append(result.getData().getStringExtra(ItemsActivity.ITEM_KEY) + "\n");
                        }
                    }
            );
    private ActivityResultLauncher<Intent> activityResultLauncherSearch =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent intent = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://www.google.com/search?q=" + result.getData().getStringExtra(ItemsActivity.ITEM_KEY)));
                                startActivity(intent);
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        itemsTextView = findViewById(R.id.itemsTextView);

        if (savedInstanceState!=null)
            itemsTextView.setText(savedInstanceState.getString(SAVESTATE_ITEMS_TEXT));

        /*itemsTextView.setText(
                sharedPreferences.getString(
                        SAVESTATE_ITEMS_TEXT, itemsTextView.getText().toString())
        );*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVESTATE_ITEMS_TEXT, itemsTextView.getText().toString());
        editor.apply();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVESTATE_ITEMS_TEXT, itemsTextView.getText().toString());
    }

    public void addButtonClicked(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        //startActivity(intent);
        activityResultLauncher.launch(intent);
    }
}