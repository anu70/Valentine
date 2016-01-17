package verkstad.org.in.valentineapp;

import android.app.Activity;
import android.app.DownloadManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Home extends ActionBarActivity {
    Functions functions;
    TextView textView,textView2,textView3;
    EditText editText,editText2;
    String user,red_rose,yellow_rose,receiver,message;
    String anonymous="no";
    RadioGroup radioGroup;
    Button button;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> arrayAdapter;
    LinearLayout linearLayout,linearLayout2;
    ListView listView;
    String arr1[] = {"anu","abhishek","chetan","akash"};
    String arr2[] = {"hiiii","hellooo","hahaha","awwww"};
    int img[] = {R.drawable.valentine1,R.drawable.valentine1,R.drawable.valentine1,R.drawable.valentine1};
    received_flowers_list_rows rflr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        functions = new Functions();
        radioGroup = (RadioGroup) findViewById(R.id.radio);
        button = (Button) findViewById(R.id.button);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        editText2= (EditText) findViewById(R.id.editText2);
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        linearLayout2 = (LinearLayout) findViewById(R.id.linear2);
        listView= (ListView) findViewById(R.id.listView);


    }


    public void login(View view){
        editText = (EditText) findViewById(R.id.editText);textView= (TextView) findViewById(R.id.textView);textView2= (TextView) findViewById(R.id.textView2);textView3= (TextView) findViewById(R.id.textView3);
        user = editText.getText().toString();
        textView.setText(user);
          functions.count_roses(Home.this, user, new Functions.VolleyCallback() {
              @Override
              public void onSuccess(int[] result) {
                  textView2.setText("" + result[0]);
                  textView3.setText("" + result[1]);
              }
          });

        functions.get_users(Home.this, new Functions.VolleyCallback1() {
            @Override
            public void onSuccess(String[] result) {
                arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, result);
                autoCompleteTextView.setAdapter(arrayAdapter);
            }
        });
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                receiver = arrayAdapter.getItem(position).toString();
            }
        });
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                receiver = null;

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void send_flowers(View view){
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout2.setVisibility(View.GONE);
    }

    public void received_flowers(View view){
        linearLayout2.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
        rflr = new received_flowers_list_rows(Home.this,img,arr1,arr2);
       // ArrayAdapter arrayAdapter = new ArrayAdapter(Home.this,R.layout.support_simple_spinner_dropdown_item,arr1);
        listView.setAdapter(rflr);



    }



    public  void anonymous(View view){
       boolean check=((CheckBox)view).isChecked();
        if(check){
            anonymous="yes";
        }
        else{anonymous="no";}
    }


    public void select_rose(View view){
        boolean checked = ((RadioButton)view).isChecked();
        switch(view.getId()){
            case R.id.radioButton:
                if(checked){
                    red_rose="1";
                    yellow_rose="0";
                }
                break;
            case R.id.radioButton2:
                if(checked){
                    red_rose="0";
                    yellow_rose="1";
                    break;
                }
        }
    }



    public void send_rose(View view){
       message=editText2.getText().toString();

        if(receiver==null){
            Toast.makeText(getApplicationContext(),"enter valid name",Toast.LENGTH_SHORT).show();
        }
        else {
            if (radioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getApplicationContext(), "Please Select a rose", Toast.LENGTH_SHORT).show();
            } else {
                functions.send(Home.this, user, receiver, red_rose, yellow_rose, anonymous, message);
            }

            radioGroup.clearCheck();
        }
        autoCompleteTextView.setText("");
        editText2.setText("");


    }



















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
