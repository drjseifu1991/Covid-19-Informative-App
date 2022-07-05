package com.ethioptech.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private static final String [] notificationMessageArray={
            "ሰላም! ለሚወዷቸው ሰዎች ሲሉ ራስዎን ይጠብቁ።" +
                    "ማህበራዊ መሰተጋብርዎን ይቀንሱ፤ ርቀትዎን ቢያንስ አንድ ሜትር ያህል።" +
                    "\n\n#StayHome" +
                    "\n#StaySafe"

            ,"ሁላችንም የግል ሃላፊነታችንን ከተወጣን ነጋችን ብሩህ ይሆናል። "+
            "እጅዎን ይታጠቡ፣ ርቀትዎን ይጠብቁ። " +
            "ብቻዎን አይደሉም ሁላችንም አብረንዎት ነን! አይዞን!" +
            "" +
            "\n\nwe are in this together!"

            , "አለመጭባበጥ መጠላላት ሳይሆን ለአንተም ለኔም ለቤተሰቦቻችን ዋስትና ነው።" +
            "የማልጨብጥህ ለኔም ለአንተም ደህንነት ስል ነው።" +
            "" +
            ""

            ,"ስለ አዛውንት ወላጆቻችን እና አያቶቻችን ስንል ማህበራዊ መስተጋብራችን እንቀንስ ።" +
            " ትክክለኛ መረጃ እናስተምር!",
               ""   +
                       "you are making a difference when you decide to stay home!"  ,
            "  \n\n     stay home!"+
            "      \n stay safe!",
            "የእርስዎ መጠንቀቅ ለወገነዎ መዳን ነው!"+
                    "\nርቀትዎን ይጠብቁ"+
                    "\nእዎን በየጊዜው ይታጠቡ"+
                    "\nፊትዎን በእጅዎ አይንኩ",


    };
    private static final String MESSAGE_INDEX="messageIndex";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private TextView message_tExtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        message_tExtView=findViewById(R.id.message_textView);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(Main2Activity.this);
        editor=sharedPreferences.edit();
        setNotificationMessage();
    }
    public void setNotificationMessage(){
        if (sharedPreferences.getInt(Main2Activity.MESSAGE_INDEX,10)==10) {
            editor.putInt("messageIndex", 0);
            editor.commit();
        }

        int currentArrayIndex=sharedPreferences.getInt(MESSAGE_INDEX,3);
         message_tExtView.setText(notificationMessageArray[currentArrayIndex]);


        if (currentArrayIndex>=notificationMessageArray.length-1){
            editor.putInt(MESSAGE_INDEX,0);
            editor.commit();
        }
        else {
            editor.putInt(MESSAGE_INDEX,(currentArrayIndex+1));
            editor.commit();
        }
    }
    //methods onClick callback
    public void ok(View view) {
        finish();
    }
}