package com.grad.one4everything.activitypack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.grad.one4everything.R;
import com.grad.one4everything.utilities.SharedPrefs;

/**
 * Created by Anki on 10/11/2015.
 */
public class SecurityActivity extends AppCompatActivity {
    Spinner spinrQuestn;
    String securityQuestion;
    SharedPrefs sharedPrefs;
    EditText securAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_activity);
        sharedPrefs = new SharedPrefs(getApplicationContext());
        securAnswer = (EditText) findViewById(R.id.securAnswer);
        spinrQuestn = (Spinner) findViewById(R.id.spinrQuestn);
        ArrayAdapter<CharSequence> spinrAdapter = ArrayAdapter.createFromResource(
                this, R.array.question_arrays,
                android.R.layout.simple_spinner_item);
        spinrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinrQuestn.setAdapter(spinrAdapter);
        spinrQuestn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                securityQuestion = parent.getItemAtPosition(position).toString();
                System.out.println("Security" + securityQuestion);
                sharedPrefs.setSecurityquestion(securityQuestion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        if (id == R.id.action_tick) {
            Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            String str = securAnswer.getText().toString();
            sharedPrefs.setSecurityanswr(str);
            System.out.println("Security Answer" + str);
            Intent iMain = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(iMain);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
