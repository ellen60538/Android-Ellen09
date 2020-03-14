package tw.org.iii.ellen.ellen09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp ;
    private SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("config", MODE_PRIVATE) ;
        editor = sp.edit() ;
    }

    public void test1(View view){
        editor.putInt("stage",(int)(Math.random()*49+1)) ;
        editor.putBoolean("sound",false) ;
        editor.putString("username","Ellen") ;
        editor.commit() ;
    }

    public void test2(View view){
        boolean sound = sp.getBoolean("sound",true) ;
        int stage = sp.getInt("stage",1) ;
        String username = sp.getString("username","???") ;
        Toast.makeText(this, username +":"+ stage +":"+ sound,Toast.LENGTH_SHORT).show();
    }

    public void test3(View view){
        try {
            FileOutputStream fout = openFileOutput("ellen.txt",MODE_PRIVATE) ;
            fout.write("Hello,World\n123456789\nabcdefg".getBytes());
            fout.flush() ;
            fout.close() ;
            Toast.makeText(this,"Save OK",Toast.LENGTH_SHORT).show() ;

        }catch (Exception e){
            Log.v("ellen",e.toString()) ;
            Toast.makeText(this,"Save failure",Toast.LENGTH_SHORT).show() ;

        }
    }

    public void test4(View view){
        try {
            FileInputStream fin = openFileInput("ellen.txt") ;
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin)) ;
            StringBuffer sb = new StringBuffer() ;
            String line ;
            while ( (line = reader.readLine()) != null ){
                sb.append(line + "\n") ;
            }
            fin.close() ;
            Toast.makeText(this,sb, Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show() ;
        }
    }
}
