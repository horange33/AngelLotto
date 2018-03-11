package activity;

/**
 * Created by sung-min on 2018-03-04.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import app.AppConfig;
import app.AppController;
import helper.SQLiteHandler;
import helper.SessionManager;
import tbc.angellotto.R;

public class CreateLottoNumberActivity extends Activity {
    private static final String TAG = CreateLottoNumberActivity.class.getSimpleName();
    private Button btnCreateNumber;
    private Button btnCreateNumber2;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    private TextView tvNumber1;
    private TextView tvNumber2;
    private TextView tvNumber3;
    private TextView tvNumber4;
    private TextView tvNumber5;
    private TextView tvNumber6;
    private TextView tvNumber7;

    private String ball[] = new String[45];
    private Map<String, Double> ball_weight = new HashMap<String, Double>();
    private Double total;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lotto_number);

        btnCreateNumber = (Button) findViewById(R.id.btnCreateNumber);
        btnCreateNumber2 = (Button) findViewById(R.id.btnCreateNumber2);

        tvNumber1 = (TextView) findViewById(R.id.tvNumber1);
        tvNumber2 = (TextView) findViewById(R.id.tvNumber2);
        tvNumber3 = (TextView) findViewById(R.id.tvNumber3);
        tvNumber4 = (TextView) findViewById(R.id.tvNumber4);
        tvNumber5 = (TextView) findViewById(R.id.tvNumber5);
        tvNumber6 = (TextView) findViewById(R.id.tvNumber6);
        tvNumber7 = (TextView) findViewById(R.id.tvNumber7);


        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(CreateLottoNumberActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Register Button Click event
        btnCreateNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                    createRandomNumber();
            }
        });

        btnCreateNumber2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                createRandomNumber2();
            }
        });

    }

    public static <E> E getWeightedRandom(Map<E, Double> weights, Random random) {
        E result = null;
        double bestValue = Double.MAX_VALUE;

        for (E element : weights.keySet()) {
            double value = -Math.log(random.nextDouble()) / weights.get(element);

            if (value < bestValue) {
                bestValue = value;
                result = element;
            }
        }
        return result;
    }



    private void createRandomNumber()
    {


        // Tag used to cancel the request
        String tag_string_req = "req_register";
        total = 0.0;
        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_GETWEIGHT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "GetLotto Response: " + response.toString());
                hideDialog();
                Vector<ArrayList<String>> vec = new Vector<>();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        String num = jObj.getString("num");
                        JSONObject lottoWeightObj = jObj.getJSONObject("weight");
                        ball[0] = lottoWeightObj.getString("ball1");
                        ball[1] = lottoWeightObj.getString("ball2");
                        ball[2] = lottoWeightObj.getString("ball3");
                        ball[3] = lottoWeightObj.getString("ball4");
                        ball[4] = lottoWeightObj.getString("ball5");
                        ball[5] = lottoWeightObj.getString("ball6");
                        ball[6] = lottoWeightObj.getString("ball7");
                        ball[7] = lottoWeightObj.getString("ball8");
                        ball[8] = lottoWeightObj.getString("ball9");
                        ball[9] = lottoWeightObj.getString("ball10");
                        ball[10] = lottoWeightObj.getString("ball11");
                        ball[11] = lottoWeightObj.getString("ball12");
                        ball[12] = lottoWeightObj.getString("ball13");
                        ball[13] = lottoWeightObj.getString("ball14");
                        ball[14] = lottoWeightObj.getString("ball15");
                        ball[15] = lottoWeightObj.getString("ball16");
                        ball[16] = lottoWeightObj.getString("ball17");
                        ball[17] = lottoWeightObj.getString("ball18");
                        ball[18] = lottoWeightObj.getString("ball19");
                        ball[19] = lottoWeightObj.getString("ball20");
                        ball[20] = lottoWeightObj.getString("ball21");
                        ball[21] = lottoWeightObj.getString("ball22");
                        ball[22] = lottoWeightObj.getString("ball23");
                        ball[23] = lottoWeightObj.getString("ball24");
                        ball[24] = lottoWeightObj.getString("ball25");
                        ball[25] = lottoWeightObj.getString("ball26");
                        ball[26] = lottoWeightObj.getString("ball27");
                        ball[27] = lottoWeightObj.getString("ball28");
                        ball[28] = lottoWeightObj.getString("ball29");
                        ball[29] = lottoWeightObj.getString("ball30");
                        ball[30] = lottoWeightObj.getString("ball31");
                        ball[31] = lottoWeightObj.getString("ball32");
                        ball[32] = lottoWeightObj.getString("ball33");
                        ball[33] = lottoWeightObj.getString("ball34");
                        ball[34] = lottoWeightObj.getString("ball35");
                        ball[35] = lottoWeightObj.getString("ball36");
                        ball[36] = lottoWeightObj.getString("ball37");
                        ball[37] = lottoWeightObj.getString("ball38");
                        ball[38] = lottoWeightObj.getString("ball39");
                        ball[39] = lottoWeightObj.getString("ball40");
                        ball[40] = lottoWeightObj.getString("ball41");
                        ball[41] = lottoWeightObj.getString("ball42");
                        ball[42] = lottoWeightObj.getString("ball43");
                        ball[43] = lottoWeightObj.getString("ball44");
                        ball[44] = lottoWeightObj.getString("ball45");
                        for(int alpha=0;alpha<45;alpha++)
                        {
                            total += Double.valueOf(ball[alpha]);
                        }

                        for(int alpha=0;alpha<45;alpha++)
                        {
                            ball_weight.put(String.valueOf(alpha+1),Double.valueOf(ball[alpha])/total);
                        }

                        /*Random rand = new Random();
                        int[] rArr  =new int[7];
                        for(int alpha=0;alpha<7;alpha++) {
                            rArr[alpha] = Integer.parseInt(getWeightedRandom(ball_weight, rand));
                            Log.d(String.valueOf(alpha) + "  ", getWeightedRandom(ball_weight, rand));
                            for(int beta = 0;beta<alpha;beta++)
                            {
                                if(rArr[alpha]==rArr[beta])
                                {
                                    alpha--;
                                }
                            }
                        }*/

                        Random rand = new Random();
                        int arr[] = new int[7];
                        int i, j, k;
                        int x;

                            for (i = 0; i < 7; i++) { // 랜덤수 6개 생성
                                x = Integer.parseInt(getWeightedRandom(ball_weight, rand)); // 랜덤함수 호출(범위 1-45)
                                arr[i] = x;
                                for (j = 0; j < i; j++) {
                                    if (arr[i] == arr[j]) { // 생성된 수와 이전에 저장된 수를 비교
                                        x = Integer.parseInt(getWeightedRandom(ball_weight, rand));
                                        arr[i] = x; // 다시 수를 생성
                                        i = i - 1; // 다시 첨부터 같은 숫자가 있는가 비교
                                        break;
                                    }
                                }
                        }


                        tvNumber1.setText(String.valueOf(arr[0]));
                        tvNumber2.setText(String.valueOf(arr[1]));
                        tvNumber3.setText(String.valueOf(arr[2]));
                        tvNumber4.setText(String.valueOf(arr[3]));
                        tvNumber5.setText(String.valueOf(arr[4]));
                        tvNumber6.setText(String.valueOf(arr[5]));
                        tvNumber7.setText(String.valueOf(arr[6]));


                        } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    private void createRandomNumber2()
    {
        Random random = new Random();
        int[] rArr  =new int[7];

        for(int alpha=0;alpha<7;alpha++) {
            rArr[alpha] = random.nextInt(45) + 1;
            for(int beta = 0;beta<alpha;beta++)
            {
                if(rArr[alpha]==rArr[beta])
                {
                    alpha--;
                }
            }
        }

        tvNumber1.setText(String.valueOf(rArr[0]));
        tvNumber2.setText(String.valueOf(rArr[1]));
        tvNumber3.setText(String.valueOf(rArr[2]));
        tvNumber4.setText(String.valueOf(rArr[3]));
        tvNumber5.setText(String.valueOf(rArr[4]));
        tvNumber6.setText(String.valueOf(rArr[5]));
        tvNumber7.setText(String.valueOf(rArr[6]));

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
