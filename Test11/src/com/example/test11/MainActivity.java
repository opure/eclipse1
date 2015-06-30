package com.example.test11;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        EditText e1=(EditText) findViewById(R.id.login);
        EditText e2=(EditText) findViewById(R.id.passwd);
        


}
}
