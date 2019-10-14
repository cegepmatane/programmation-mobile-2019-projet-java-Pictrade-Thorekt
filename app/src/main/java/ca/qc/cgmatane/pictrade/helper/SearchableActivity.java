package ca.qc.cgmatane.pictrade.helper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import ca.qc.cgmatane.pictrade.R;

public class SearchableActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String requete = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(requete);
        }
    }

    public void doMySearch(String requete){

    }
}
