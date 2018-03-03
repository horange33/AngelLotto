package activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tbc.angellotto.R;

public class BiorhythmActivity extends Activity {
    private BioView bioView;
    private EditText inputYear, inputMonth, inputDay;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_biorhythm);

        bioView = (BioView)findViewById(R.id.myView);

        inputYear = (EditText)findViewById(R.id.inputYear);
        inputMonth = (EditText)findViewById(R.id.inputMonth);
        inputDay = (EditText)findViewById(R.id.inputDay);

        /** 버튼이 클릭되었을 경우 바이오리듬 출력 */
        Button button = (Button)findViewById(R.id.showButton);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                bioView.setBirthDay(Integer.parseInt(inputYear.getText().toString()),
                        Integer.parseInt(inputMonth.getText().toString()),
                        Integer.parseInt(inputDay.getText().toString()));

                bioView.invalidate();
            }
        });
    }
}