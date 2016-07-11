package android.and05.lektion1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class DataActivity extends Activity {

	private TextView tvOutput;
	private EditText evInput;
	private File app_File;
	@Override
	public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_data);
			/*tvOutput = (TextView) this.findViewById 
			(R.id.tv_output);
			evInput = (EditText) this.findViewById 
			(R.id.et_input); */
			Button btSave = (Button) this.findViewById 
			(R.id.bt_save);
			tvOutput = (TextView) this.findViewById(R.id.tv_output);
			if(app_File == null) {
				File app_Dir = this.getDir("Properties", MODE_PRIVATE);
				app_File = new File(app_Dir, "helloFile");
			}
			//String helloText = this.read(app_File);
			String helloText = "";
			try {
			helloText = this.read(app_File);
			} catch(Exception e) {
			Log.e("TAG", e.toString());
			}
			if(!helloText.equals(""))
			tvOutput.setText(helloText);
			evInput = (EditText) this.findViewById(R.id.et_input);
			btSave.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					//tvOutput.setText(evInput.getText());
					String evText = evInput.getText().toString();
					tvOutput.setText(evText);
					DataActivity.this.save(app_File, evText);
				}
				});
	} // end on create
	
	   private String read(File fileName) throws Exception {
		String retString = "";
		BufferedReader reader = null;
		try {
		FileInputStream in = new FileInputStream(fileName);
		reader = new BufferedReader(new InputStreamReader(in));
		String zeile;
		while((zeile = reader.readLine()) != null)
		retString += zeile;
		} finally {
		if(reader != null)
		reader.close();
		}
		return retString;
		}
	///////////////////////////////////////////////////////////////
	
		private void save(File file, String text) {
				try {
				FileOutputStream out = new  FileOutputStream(
				file);
				OutputStreamWriter writer =
				new OutputStreamWriter(out);
				writer.write(text);
				writer.close();
				} catch (FileNotFoundException fnfe) {
				Log.e("TAG", fnfe.toString());
				} catch (IOException ioe){
				Log.e("TAG", ioe.toString());
				}
		}

}
