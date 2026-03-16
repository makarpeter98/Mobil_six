package hu.unideb.inf.mobilsz12.shoppinglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ItemsActivity extends AppCompatActivity {

    public static final String ITEM_KEY = "hu.unideb.inf.mobilsz12.shoppinglist.ItemsActivity.ITEM_KEY_fhgfdhvchfhzfjjfujfjhg";
    private boolean isColorFragmentVisible = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
    }

    public void addItem(View view) {
        Intent replyIntent = new Intent();

        //if (lenyomott gomb szövege == "Cukor") {}
        String buttonString = ((Button) view).getText().toString();
        String sugarString = getString(R.string.sugar);

        replyIntent.putExtra(ITEM_KEY, buttonString);

        setResult(RESULT_OK, replyIntent);
        finish();
    }
}