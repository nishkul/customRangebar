package android.test.com.customseekbar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.test.com.customseekbar.wiget.RangeSeekBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     long minValuee;
     long maxValuee;
    private long RANGE_MIN = 10;
    private long RANGE_MAX = 10000;
    //    private long RANGE_MIN = 0;
//    private long RANGE_MAX = 10000000;
    private EditText minEdt;
    private EditText maxEdt;
    private RangeSeekBar<Long> rangeSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maxEdt = (EditText) findViewById(R.id.setmax_edt);
        minEdt = (EditText) findViewById(R.id.setmin_edt);
        maxEdt.setText(RANGE_MAX + "");
        minEdt.setText(RANGE_MIN + "");
        // Setup the new range seek bar
        rangeSeekBar = new RangeSeekBar<Long>(this);
        // Set the range
        rangeSeekBar.setRangeValues(RANGE_MIN, RANGE_MAX);
        rangeSeekBar.setSelectedMinValue(RANGE_MIN);
        rangeSeekBar.setSelectedMaxValue(RANGE_MAX);
        ((TextView) findViewById(R.id.minValue)).setText("10 ");
        ((TextView) findViewById(R.id.maxValue)).setText("10000");
        maxEdt.addTextChangedListener(new MyTextWatcher(maxEdt));
        minEdt.addTextChangedListener(new MyTextWatcher(minEdt));

        // Add to layout
        LinearLayout layout = (LinearLayout) findViewById(R.id.range_seek_bar_place_holer);
        layout.addView(rangeSeekBar);

        rangeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Long>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<Long> bar, Long minValue, Long maxValue) {

                // bar.setSelectedMinValue(minValuee);
                // bar.setSelectedMaxValue(maxValuee);
                Log.i("aaaa maxvalw ll", minValue + "");
                Log.i("aaaa maxvalwww ll", maxValue + "");
                maxEdt.setText(maxValue + "");
                minEdt.setText(minValue + "");

//                if (minValuee > 0) {
//                    bar.setSelectedMinValue(minValuee);
//                } else {
//                    maxEdt.setText(rangeSeekBar.getSelectedMinValue() + "");
//                }
//                if (maxValuee > 0) {
//                    bar.setSelectedMaxValue(minValuee);
//                } else {
//                    maxEdt.setText(rangeSeekBar.getSelectedMaxValue() + "");
//                }
            }
        });


//        rangeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Long>() {
//            @Override
//            public void onRangeSeekBarValuesChanged(RangeSeekBar<Long> bar, Long minValue, Long maxValue) {
//                long minval = (minValue / 1000000);
//                long maxval = (maxValue / 1000000);
//
//                Log.i("aaaa minval ll",minValue+"");
//                Log.i("aaaa maxval ll",maxValue+"");
//
//                Log.i("aaaa  minval",minval +"");
//                Log.i("aaaa  maxval",maxval +"");
//                if (minval > 0) {
//                    ((TextView) findViewById(R.id.minValue)).setText("" + ((minval * 1000000) / 100000) + " " + "Lacks");
//                    rangeSeekBar.setSelectedMinValue((minval * 1000000));
//                } else {
//                    ((TextView) findViewById(R.id.minValue)).setText("0 Lacks");
//                }
//
//
//                if (maxval > 0) {
//                    ((TextView) findViewById(R.id.maxValue)).setText("" + ((maxval * 1000000) / 100000) + " " + "Lacks");
//                    rangeSeekBar.setSelectedMaxValue((maxval * 1000000));
//                } else {
//                    ((TextView) findViewById(R.id.maxValue)).setText("1 Crore");
//                }
//
//            }
//        });

        ((Button) findViewById(R.id.selectedRange)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Min Value: " + rangeSeekBar.getSelectedMinValue() + "\n" + "Max Value: " + rangeSeekBar.getSelectedMaxValue(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private class MyTextWatcher implements TextWatcher {


        private View view;
        private Context context;

        private MyTextWatcher(View view) {
            this.view = view;

        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {

                case R.id.setmin_edt:
                    if (minEdt.getText().toString().length()>0)
                    minValuee = Long.parseLong(minEdt.getText().toString());
                    Log.i("aaaa maxval ll min edit", minValuee + "");
                    if (minValuee > RANGE_MIN && minValuee < RANGE_MAX) {
                        rangeSeekBar.setSelectedMinValue(minValuee);

                    } else if (minValuee <= RANGE_MIN) {
                        rangeSeekBar.setSelectedMinValue(RANGE_MIN);
                    } else if (minValuee >= RANGE_MAX) {
                        rangeSeekBar.setSelectedMinValue(RANGE_MAX);
                    }
                    break;
                case R.id.setmax_edt:
                    if (maxEdt.getText().toString().length()>0)
                    maxValuee = Long.parseLong(maxEdt.getText().toString());
                    Log.i("aaaa maxval ll min edit", maxValuee + "");
                    if (maxValuee >= RANGE_MIN && maxValuee <= RANGE_MAX) {
                        rangeSeekBar.setSelectedMaxValue(maxValuee);
                    } else if (maxValuee >= RANGE_MAX) {
                        rangeSeekBar.setSelectedMaxValue(RANGE_MAX);
                    } else if (maxValuee <= RANGE_MIN) {
                        rangeSeekBar.setSelectedMaxValue(RANGE_MAX);

                    }
                    break;
            }

        }
    }


}