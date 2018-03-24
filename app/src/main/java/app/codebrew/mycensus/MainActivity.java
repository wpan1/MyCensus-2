package app.codebrew.mycensus;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;

import app.codebrew.mycensus.Tabs.FamilyDetailsTab;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the  activity_main.xml layout file
        setContentView(R.layout.activity_main);

        btnCheck = (FloatingActionButton) findViewById(R.id.fab);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(4);

        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    EditText e1= findViewById(R.id.vaccination);
                    EditText e2= findViewById(R.id.first_name_et);
                    EditText e3= findViewById(R.id.last_name_et);
                    RadioButton e4= findViewById(((RadioGroup) findViewById(R.id.gender)).getCheckedRadioButtonId());
                    EditText e5= findViewById(R.id.income);
                    EditText e6= findViewById(R.id.mother_language);
                    EditText e7= findViewById(R.id.disability);
                    EditText e8= findViewById(R.id.assistance);
                    EditText e9= findViewById(R.id.dob);
                    EditText e10= findViewById(R.id.phone_no);
                    EditText e11= findViewById(R.id.ed_qualification);
                    EditText e12= findViewById(R.id.citizenship);
                    EditText e13= findViewById(R.id.country);
                    EditText e14= findViewById(R.id.region);
                    EditText e15= findViewById(R.id.marital);
                    EditText e16= findViewById(R.id.occupation);
                    String vaccination = (e1).getText().toString();
                    String firstname = (e2).getText().toString();
                    String lastname = (e3).getText().toString();
                    String gender = "M";
                    if (e4 != null)
                         gender = e4.getText().toString();
                    String income = (e5).getText().toString();
                    String language = (e6).getText().toString();
                    String disability = e7.getText().toString();
                    String assistance = e8.getText().toString();
                    String dob = e9.getText().toString();
                    String phone_no = e10.getText().toString();
                    String ed_qualify =e11.getText().toString();
                    String citizenship = e12.getText().toString();
                    String country_of_birth = e13.getText().toString();
                    String region = e14.getText().toString();
                    String marital = e15.getText().toString();
                    String occupation = e16.getText().toString();

/**
                vaccination = "egsa1";
                firstname = "egsa2";
                lastname = "egsa3";
                gender = "M";
                income = "egsa4";
                language = "egsa5";
                disability = "egsa6";
                assistance = "egsa7";
                dob = "1995-03-12";
                phone_no = "02131921241";
                ed_qualify = "egsa8";
                citizenship = "egsa9";
                country_of_birth = "egsa10";
                region = "egsa11";
                marital = "egsa12";
                occupation = "egsa13";
                **/

                    HashMap<String, String> sqlwork = new HashMap<String, String>();
                    sqlwork.put("FirstName", firstname);
                    sqlwork.put("LastName", lastname);
                    sqlwork.put("Gender", gender);
                    sqlwork.put("Income", income);
                    sqlwork.put("Language", language);
                    sqlwork.put("Disability", disability);
                    sqlwork.put("Assistance", assistance);
                    sqlwork.put("DOB", dob);
                    sqlwork.put("PhoneNumber", phone_no);
                    sqlwork.put("HighestQualification", ed_qualify);
                    sqlwork.put("Citizenship", citizenship);
                    sqlwork.put("CountryOfBirth", country_of_birth);
                    sqlwork.put("Region", region);
                    sqlwork.put("MaritalStatus", marital);
                    sqlwork.put("Occupation", occupation);
                    sqlwork.put("Vaccinations", vaccination);

                    new SQLoperation().execute(sqlwork);

                    e1.getText().clear();
                    e2.getText().clear();
                    e3.getText().clear();
                    e5.getText().clear();
                    e6.getText().clear();
                    e7.getText().clear();
                    e8.getText().clear();
                    e9.getText().clear();
                    e10.getText().clear();
                    e11.getText().clear();
                    e12.getText().clear();
                    e13.getText().clear();
                    e14.getText().clear();
                    e15.getText().clear();
                    e16.getText().clear();
                    ((RadioGroup) findViewById(R.id.gender)).clearCheck();
                    FamilyDetailsTab.familyMembers.clear();
                }

        });
    }
    public class SQLoperation extends AsyncTask<HashMap<String,String>,Void,Void> {
        Connection conn =null;

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(), "Data submitted successfully", Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(HashMap<String, String>[] hashMaps) {
            try {
                //Log.e("TEST", "START");
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection("jdbc:mysql://128.199.224.104:3306/census", "codebrew", "codebrew");

                //Log.e("TEST", "DONE");

                Statement statement = conn.createStatement();
                String[] keys = {"FirstName", "LastName", "Gender", "DOB", "MaritalStatus", "Region", "Citizenship", "CountryOfBirth", "Language", "Income", "Disability", "Assistance", "HighestQualification", "Occupation", "Vaccinations", "PhoneNumber"};
                String sqlcol = "(`FirstName`,`LastName`,`Gender`,`DOB`,`MaritalStatus`,`Region`,`Citizenship`,`CountryOfBirth`,`Language`,`Income`,`Disability`,`Assistance`,`HighestQualification`,`Occupation`,`Vaccinations`,`PhoneNumber`)";
                String sqlval = "(";
                for (String item : keys) {
                    if (item.equals("PhoneNumber"))
                        sqlval += '"' + hashMaps[0].get(item) + '"';
                    else
                        sqlval += '"' + hashMaps[0].get(item) + '"' + ",";

                }
                sqlval += ");";
                //Log.e("TEST", "INSERT INTO `census`.`Person`" + sqlcol + "VALUES" + sqlval);
                statement.execute("INSERT INTO `census`.`Person`" + sqlcol + "VALUES" + sqlval);
                //statement.execute("SELECT * FROM `census`.`Person`");

            }
            catch (Exception e) {
                System.err.print(e);
                //e.printStackTrace();
            }
            return null;
        }
    }
}