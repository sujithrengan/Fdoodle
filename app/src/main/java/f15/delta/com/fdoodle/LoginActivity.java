package f15.delta.com.fdoodle;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends Activity {

    private EditText username_text,password_text;
    private CheckBox checkBox;
    public  String user_mail,password;
    public SharedPreferences prefs;
    public Button SignIn;
    public Button register;

    public Intent i;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        username_text = (EditText)findViewById(R.id.username_text);
        password_text = (EditText)findViewById(R.id.password);
        checkBox = (CheckBox)findViewById(R.id.showPasswordCheckBox);
        SignIn = (Button)findViewById(R.id.signInButton);
        register=(Button)findViewById(R.id.RegisterButton);
        prefs = getSharedPreferences("LogInPrefs",0);
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/gnu.ttf");
        username_text.setTypeface(typeface);
        password_text.setTypeface(typeface);
        SignIn.setTypeface(typeface);
        register.setTypeface(typeface);
        i= new Intent(LoginActivity.this,Hscreen.class);
        checkBox.setTypeface(typeface);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password_text.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                if (!isChecked) {
                    password_text.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }
    private static Boolean isValidEmail(String email)
    {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    public void callRegistration(View view){
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }
    public void callSignIn(View view){
        user_mail = username_text.getText().toString();
        password = password_text.getText().toString();
        if(user_mail.length()==0)username_text.setError("Invalid username");
            // else if(!isValidEmail(username))username_text.setError("Invalid Username");
        else if(password.length()==0)password_text.setError("Invalid password");
        else  check_login();



    }
    public void check_login(){
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, Utilities.url_auth,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int status = jsonResponse.getInt("status");
                            String error = jsonResponse.getString("data");
                            pDialog.dismiss();
                            switch (status) {
                                case 0:
                                    Toast.makeText(LoginActivity.this, "There was a problem connecting to the server. Please check your username and password and try again.", Toast.LENGTH_LONG).show();
                                    username_text.setText("");
                                    password_text.setText("");
                                    SignIn.setClickable(true);

                                    break;
                                case 1:case 2:

                                    SharedPreferences.Editor editor = prefs.edit();
                                    Utilities.status=1;
                                    editor.putInt("Logged_in", Utilities.status);
                                    editor.putString("f_email", user_mail);
                                    Utilities.f_email = user_mail;
                                    editor.putString("f_pass", password);
                                    Utilities.f_pass = password;
                                    editor.apply();
                                    getDetails();
                                    //Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                                    //startActivity(i);
                                    //finish();
                                    break;

                                case 3:
                                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                                    username_text.setText("");
                                    password_text.setText("");
                                    SignIn.setClickable(true);
                                    break;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        error.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("user_email", user_mail);
                params.put("user_pass", password);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);

    }
    public void getDetails(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, Utilities.url_details,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int status = jsonResponse.getInt("status");
                            String data = jsonResponse.getString("data");
                            JSONObject jsonObject= new JSONObject(data);

                            String id=jsonObject.getString("user_id");
                            String name=jsonObject.getString("user_name");
                            String email=jsonObject.getString("user_email");
                            String fullname=jsonObject.getString("user_fullname");

                            pDialog.dismiss();

                            switch (status) {
                                case 0:
                                    Toast.makeText(LoginActivity.this, "There was a problem connecting to the server. Please check your username and password and try again.", Toast.LENGTH_LONG).show();
                                    username_text.setText("");
                                    password_text.setText("");
                                    SignIn.setClickable(true);

                                    break;
                                case 1:case 2:

                                    SharedPreferences.Editor editor = prefs.edit();
                                    //editor.putInt("Logged_in", Utilities.status);
                                    editor.putString("f_email", email);
                                    editor.putString("f_id", id);
                                    editor.putString("f_name", name);
                                    editor.putString("f_fullname",fullname);
                                    editor.putString("f_pass", password);
                                    Utilities.f_email = email;
                                    Utilities.f_id=id;
                                    Profile.setProfile(Integer.valueOf(id),name,email,fullname);
                                    editor.apply();
                                    startActivity(i);
                                    finish();
                                    break;

                                case 3:
                                    Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
                                    username_text.setText("");
                                    password_text.setText("");
                                    SignIn.setClickable(true);
                                    break;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        error.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("user_email", Utilities.f_email);
                params.put("user_pass", Utilities.f_pass);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
