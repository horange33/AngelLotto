package activity;

import android.Manifest;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import helper.PermissionRequester;
import tbc.angellotto.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;



public class GetLottoActivity extends AppCompatActivity {
    private TextView tvLatestLottoCount;
    private ImageView ivNumber1;
    private ImageView ivNumber2;
    private ImageView ivNumber3;
    private ImageView ivNumber4;
    private ImageView ivNumber5;
    private ImageView ivNumber6;
    private ImageView ivNumber7;
    private TextView tvNumber1;
    private TextView tvNumber2;
    private TextView tvNumber3;
    private TextView tvNumber4;
    private TextView tvNumber5;
    private TextView tvNumber6;
    private TextView tvNumber7;
    private TextView tvWinGameCount;
    private TextView tvWinGameMoney;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getlotto);
        tvLatestLottoCount = (TextView) findViewById(R.id.tvLatestLottoCount);
        //ivNumber1 = (ImageView) findViewById(R.id.ivNumber1);
        //ivNumber2 = (ImageView) findViewById(R.id.ivNumber2);
        // ivNumber3 = (ImageView) findViewById(R.id.ivNumber3);
        //ivNumber4 = (ImageView) findViewById(R.id.ivNumber4);
        // ivNumber5 = (ImageView) findViewById(R.id.ivNumber5);
        //ivNumber6 = (ImageView) findViewById(R.id.ivNumber6);
        //ivNumber7 = (ImageView) findViewById(R.id.ivNumber7);
        tvNumber1 = (TextView) findViewById(R.id.tvNumber1);
        tvNumber2 = (TextView) findViewById(R.id.tvNumber2);
        tvNumber3 = (TextView) findViewById(R.id.tvNumber3);
        tvNumber4 = (TextView) findViewById(R.id.tvNumber4);
        tvNumber5 = (TextView) findViewById(R.id.tvNumber5);
        tvNumber6 = (TextView) findViewById(R.id.tvNumber6);
        tvNumber7 = (TextView) findViewById(R.id.tvNumber7);
        tvWinGameCount = (TextView) findViewById(R.id.tvWinGameCount);
        tvWinGameMoney = (TextView) findViewById(R.id.tvWinGameMoney);

        // 권한 요청
        PermissionRequester.Builder request = new PermissionRequester.Builder(this);
        request.create().request(
                Manifest.permission.INTERNET
                , 10000
                , new PermissionRequester.OnClickDenyButtonListener() {
                    @Override
                    public void onClick (Activity activity)
                    {
                        Toast.makeText(GetLottoActivity.this, "인터넷 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
                        activity.finish();
                    }
                }
        );
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        GetLottoNumberTask task = new GetLottoNumberTask();
        task.execute();

    }

    public class GetLottoNumberTask extends AsyncTask<Void, Void, Map<String, String>> {
        String[] ballNum = new String[7];
        String latest;
        @Override
        protected Map doInBackground(Void... params) {
            Map result = new HashMap();


            try {
                Document document = Jsoup.connect("http://www.nlotto.co.kr/common.do?method=main").get();
                // 회차 정보 가져오기
                Elements elements = document.select(".lotto_area #lottoDrwNo");
                result.put("latestLotto", elements.text());
                Log.i("doInBackground: ",result.toString());
                String latestStr = result.get("latestLotto").toString();
                String[] lsp = latestStr.split("=");
                latest = lsp[1].split("=")[0];


                // 번호 가져오기
                for (int i = 1; i < 7; i++)
                {
                    elements = document.select(".lotto_area #numView #drwtNo" + i);
                    result.put("number" + i, elements.attr("src"));

                    String ballNumAddrStr = result.get("number"+i).toString();//공의 이미지 파일 경로
                    String[] sp = ballNumAddrStr.split("_");//_단위로 잘라줘서 sp에 넣고
                    ballNum[i-1] = sp[2].split("\\.")[0];//뒤에 확장자명 잘라서 ballNum 스트링에 넣음
                }

                // 보너스 번호 가져오기
                elements = document.select(".lotto_area #numView #bnusNo");
                result.put("number7", elements.attr("src"));
                String ballNumAddrStr = result.get("number7").toString();//공의 이미지 파일 경로
                String[] sp = ballNumAddrStr.split("_");//_단위로 잘라줘서 sp에 넣고
                ballNum[6] = sp[2].split("\\.")[0];//뒤에 확장자명 잘라서 ballNum 스트링에 넣음
                // 1등 당첨자수
                elements = document.select(".lotto_area .winner_num #lottoNo1Su");
                result.put("tvWinGameCount", elements.text());
                // 1등 당첨금액
                elements = document.select(".lotto_area .winner_money #lottoNo1SuAmount");
                result.put("tvWinGameMoney", elements.text());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override protected void onPostExecute(Map map)
        {
            for(int alpha=1;alpha<8;alpha++)
            {
                if(alpha==1)
                    tvNumber1.setText(ballNum[0]);
                else if(alpha==2)
                    tvNumber2.setText(ballNum[1]);
                else if(alpha==3)
                    tvNumber3.setText(ballNum[2]);
                else if(alpha==4)
                    tvNumber4.setText(ballNum[3]);
                else if(alpha==5)
                    tvNumber5.setText(ballNum[4]);
                else if(alpha==6)
                    tvNumber6.setText(ballNum[5]);
                else if(alpha==7)
                    tvNumber7.setText(ballNum[6]);
            }
        }
    }

//    private class GetLottoNumberTask extends AsyncTask<Void, Void, Map<String, String>> {
//        String[] ballNum = new String[7];
//        @Override
//        protected Map doInBackground(Void... params) {
//            Map result = new HashMap();
//
//
//            try {
//                Document document = Jsoup.connect("http://www.nlotto.co.kr/common.do?method=main").get();
//                // 회차 정보 가져오기
//                Elements elements = document.select(".lotto_area #lottoDrwNo");
//                result.put("latestLotto", elements.text());
//                //Log.i("doInBackground: ",result.toString());
//                // 번호 가져오기
//                for (int i = 1; i < 7; i++)
//                {
//                    elements = document.select(".lotto_area #numView #drwtNo" + i);
//                    result.put("number" + i, elements.attr("src"));
//
//                    String ballNumAddrStr = result.get("number"+i).toString();//공의 이미지 파일 경로
//                    String[] sp = ballNumAddrStr.split("_");//_단위로 잘라줘서 sp에 넣고
//                    ballNum[i-1] = sp[2].split("\\.")[0];//뒤에 확장자명 잘라서 ballNum 스트링에 넣음
//
//
//
//                }
//
//                // 보너스 번호 가져오기
//                elements = document.select(".lotto_area #numView #bnusNo");
//                result.put("number7", elements.attr("src"));
//                // 1등 당첨자수
//                elements = document.select(".lotto_area .winner_num #lottoNo1Su");
//                result.put("tvWinGameCount", elements.text());
//                // 1등 당첨금액
//                elements = document.select(".lotto_area .winner_money #lottoNo1SuAmount");
//                result.put("tvWinGameMoney", elements.text());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return result;
//        }
//
//        @Override protected void onPostExecute(Map map)
//        {
//            for(int alpha=1;alpha<7;alpha++)
//            {
//                if(alpha==1)
//                    tvNumber1.setText(ballNum[0]);
//                else if(alpha==2)
//                    tvNumber2.setText(ballNum[1]);
//                else if(alpha==3)
//                    tvNumber3.setText(ballNum[2]);
//                else if(alpha==4)
//                    tvNumber4.setText(ballNum[3]);
//                else if(alpha==5)
//                    tvNumber5.setText(ballNum[4]);
//                else if(alpha==6)
//                    tvNumber6.setText(ballNum[5]);
//            }
//        }
//    }

    /** * String : Parameter type
     *  * Bitmap : Result type */
    private class GetImageTask extends AsyncTask<String, Void, Bitmap>
    {
        private String numberType;

        @Override
        protected Bitmap doInBackground(String... params)
        {
            numberType = params[1];
            Bitmap bitmap = null;

            try
            {
                URL url = new URL("http://www.nlotto.co.kr" + params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.connect();
                bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override protected void onPostExecute(Bitmap bitmap)
        {
            if( numberType.equals("number1") )
            {
                ivNumber1.setImageBitmap(bitmap);
            }
            else if ( numberType.equals("number2") )
            {
                ivNumber2.setImageBitmap(bitmap);
            }
            else if ( numberType.equals("number3") )
            {
                ivNumber3.setImageBitmap(bitmap);
            }
            else if ( numberType.equals("number4") )
            {
                ivNumber4.setImageBitmap(bitmap);
            }
            else if ( numberType.equals("number5") )
            {
                ivNumber5.setImageBitmap(bitmap);
            }
            else if ( numberType.equals("number6") )
            {
                ivNumber6.setImageBitmap(bitmap);
            }
            else if ( numberType.equals("number7") )
            {
                ivNumber7.setImageBitmap(bitmap);
            }
        }
    }
}




