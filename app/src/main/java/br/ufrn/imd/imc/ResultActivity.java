package br.ufrn.imd.imc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class ResultActivity extends ActionBarActivity {

    private static final boolean DEBUG = true;
    private static final String TAG = "ResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView tv = (TextView) findViewById(R.id.result_result);

        float imc = getIntent().getFloatExtra("result", -1f);
        float height = getIntent().getFloatExtra("height", -1f);
        float weight = getIntent().getFloatExtra("weight", -1f);

        NumberFormat format = DecimalFormat.getNumberInstance();
        format.setMaximumFractionDigits(2);
        tv.setText(format.format(imc));

        int imgResource = -1;

        if (imc < 18.5f) { // under weight
            if (height < 1.70f) { // small
                imgResource = R.mipmap.small_skinny;
            } else {
                imgResource = R.mipmap.tall_skinny;
            }
        } else if (imc < 25) { // normal
            if (height < 1.70f) { // small
                imgResource = R.mipmap.normal;
            } else {
                imgResource = R.mipmap.normal;
            }
        } else if (imc < 30) { // over weight
            if (height < 1.70f) { // small
                imgResource = R.mipmap.small_fat;
            } else {
                imgResource = R.mipmap.fat;
            }
        } else { // obese
            if (height < 1.70f) { // small
                imgResource = R.mipmap.jo;
            } else {
                imgResource = R.mipmap.fat;
            }
        }

        ImageView img = (ImageView) findViewById(R.id.result_img_result);
        img.setImageResource(imgResource);

        if (DEBUG) {
            Log.d(TAG, "weight = " + weight);
            Log.d(TAG, "height = " + height);
            Log.d(TAG, "imc = " + imc);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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
