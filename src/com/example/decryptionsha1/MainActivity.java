package com.example.decryptionsha1;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import android.support.v7.app.ActionBarActivity;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	private StringBuffer sb=new StringBuffer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<1000;i++)
		{
			sb.append("王：大聯盟就是所有事情別人都幫你做得" +
    		"好好的，在大聯盟的時候，你會覺得理所當然，但下來以後，靜下心來想，那" +
    		"些事也不是不能自己做啊，比賽完自己把鞋清一清，衣服順手丟進洗衣機洗一洗，" +
    		"或是，有時候我也會在飯店自己煮點簡單的東西，沒有什麼難的，以前也是這樣過來，沒有理由現在做不" +
    		"到，其實自己來也有樂趣啦，唯一比較辛苦的就是以前搭飛" +
    		"機，現在搭巴士，一趟車程動不動十幾小時，下車時的感覺，比投完一場球還痠痛，但一" +
    		"樣，習慣就好了。王：大聯盟就是所有事情別人都幫你做得" +
    		"好好的，在大聯盟的時候，你會覺得理所當然，但下來以後，靜下心來想，那" +
    		"些事也不是不能自己做啊，比賽完自己把鞋清一清，衣服順手丟進洗衣機洗一洗，" +
    		"或是，有時候我也會在飯店自己煮點簡單的東西，沒有什麼難的，以前也是這樣過來，沒有理由現在做不" +
    		"到，其實自己來也有樂趣啦，唯一比較辛苦的就是以前搭飛" +
    		"機，現在搭巴士，一趟車程動不動十幾小時，下車時的感覺，比投完一場球還痠痛，但一" +
    		"樣，習慣就好了。");
		}
        Button checkbutton=(Button)findViewById(R.id.button1);
        final TextView showtext= (TextView) findViewById(R.id.textView1);
        checkbutton.setOnClickListener(new OnClickListener() {
            	long testlog = 0  ;
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for(int i=0;i<100;i++)
	        	  {
	        	  	long begin = SystemClock.elapsedRealtime();
			    Encrypt(sb.toString());
				long end = SystemClock.elapsedRealtime();
				testlog+=(end-begin);
	        	  }
		showtext.setText("測試的byte："+sb.toString().getBytes().length+"\n"+
	        	             "重複加密100次後的平均時間：" +((testlog/100)/1000.0)+"秒" );
			}
		});
     
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
   /*
    * SHA1加密做法
    */
    
    public String Encrypt(String sb2) {
		MessageDigest md = null;//java中要算加密所呼叫的一個類別
		String strDes = null;
		try {
			md = MessageDigest.getInstance("SHA-1");//看要哪種加密法
			byte[] bt = sb2.getBytes("UTF-8");//將要加密的物件轉成byte陣列,
			md.update(bt);//呼叫update方法加入byte陣列
			strDes = bytes2Hex(md.digest()); 
			// to HexString 加密後的結果轉成１６位元字串，digest是加密後的結果
			Log.i("adair", "password:"+strDes+"\n");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("無效的算法");
			return null;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("編碼轉換錯誤");
			e.printStackTrace();
		} 
		return strDes;
	}
    /*
     * SHA1加密後轉成16進位的字串
     */
	public String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
}
