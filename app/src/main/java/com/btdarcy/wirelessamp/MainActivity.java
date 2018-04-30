package com.btdarcy.wirelessamp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference presets;
    private Button btnSave, btnLoad;
    private TextView vtxt, ttxt,cvol,cton, ct, cv;
    private SeekBar vol, ton;
    private EditText number;
    //private List<MyPreset> listItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        presets = database.getReference("Presets");

        btnSave = findViewById(R.id.save_btn);
        btnLoad = findViewById(R.id.load_btn);
        vtxt = findViewById(R.id.volume);
        vol = findViewById(R.id.volume_bar);
        ttxt = findViewById(R.id.tone);
        ton = findViewById(R.id.tone_bar);
        number = findViewById(R.id.editText);
        cvol = findViewById(R.id.current_volume);
        cton = findViewById(R.id.current_tone);
        ct = findViewById(R.id.ct);
        cv = findViewById(R.id.cv);
        //listItems = new ArrayList<>();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String volume = (String) vtxt.getText();
                String tone = (String) ttxt.getText();
                String num = number.getText().toString();
                if(num.equals("")) {

                }else{
                        presets.child(num).child("volume").setValue(volume);
                        presets.child(num).child("tone").setValue(tone);
                    }
            }

        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = number.getText().toString();
                presets.child(num).child("volume").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String volume = dataSnapshot.getValue(String.class);
                        cvol.setText(volume);
                        cv.setVisibility(View.VISIBLE);
                        presets.child("current").child("volume").setValue(volume);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                presets.child(num).child("tone").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String tone = dataSnapshot.getValue(String.class);
                        cton.setText(tone);
                        ct.setVisibility(View.VISIBLE);
                        presets.child("current").child("tone").setValue(tone);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        /*ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MyPreset data = dataSnapshot.getValue(MyPreset.class);

                //updateLogs(data);

                listItems.add(0,data);
                while(listItems.size()>4)
                {
                    listItems.remove(listItems.size()-1);
                }
                logView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        //test1.addChildEventListener(childEventListener);
        logs.addChildEventListener(childEventListener);
*/
        /*btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = number.getText().toString();
                presets.child(num).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        MyPreset data = dataSnapshot.getValue(MyPreset.class);
                        //Map<String, String> map = dataSnapshot.getValue(Map.class);
                        //String val1 = map.get("Volume");
                        //String val2 = map.get("Tone");

                        String val1 = data.getVolume();
                        String val2 = data.getTone();
                        vtxt.setText("volume: " + val1);
                        ttxt.setText("tone: " + val2);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });*/



        /*btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = number.getText().toString();

                ChildEventListener childEventListener = new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        MyPreset data = dataSnapshot.getValue(MyPreset.class);

                        String val1 = data.getVolume();
                        String val2 = data.getTone();
                        vtxt.setText(val1);
                        ttxt.setText(val2);

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };
                presets.child(num).addChildEventListener(childEventListener);


                }

        });*/

        vol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                vtxt.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ton.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ttxt.setText(""+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
