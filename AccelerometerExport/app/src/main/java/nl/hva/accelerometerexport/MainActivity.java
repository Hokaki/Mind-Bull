package nl.hva.accelerometerexport;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.widget.*;
import nl.hva.accelerometerexport.model.Recording;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

// see : http://androidexample.com/Upload_File_To_Server_-_Android_Example/index.php?view=article_discription&aid=83&aaid=106
public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager sensorManager;

	private Sensor accelerometer;
	
	private List<Recording> recordings;
	
	private Recording recording;
	
	private EditText txtRecordingName;
	
	private Button btnExport;
	
	private GraphView graphView;
	
	private long counter;

	int serverResponseCode = 0;
	ProgressDialog dialog = null;

	String upLoadServerUri = null;
	
	public void setRecording(Recording recording) {
		this.recording = recording;
	}
	public Recording getRecording() {
		return recording;
	}

	private void stopRecording() {
		txtRecordingName.setEnabled(true);
		sensorManager.unregisterListener(this);
		if (getRecording() != null && getRecording().getData() != null) {
			
					
			getRecording().setName(txtRecordingName.getText().toString());
			recordings.add(getRecording());
			
			GraphViewData[] graphViewData = new GraphViewData[getRecording().getData().size()];
	
			for (int i = 0; i <getRecording().getData().size(); i++) {
				Float[] dataF = getRecording().getData().get(i);
				graphViewData[i] = new GraphViewData(i+1, (double) sqrt(pow(dataF[0],2) + pow(dataF[1],2) + pow(dataF[2],2) ));
			}
			
			
			GraphViewSeries series = new GraphViewSeries(graphViewData);
			
			graphView = new LineGraphView(this, "sqrt(x^2 + y^2 + z^2)");
			graphView.setManualYAxisBounds(30, 0);
			graphView.setScalable(true);  
			LinearLayout layout = (LinearLayout) findViewById(R.id.graphLayoutX);
			layout.removeAllViews();
			layout.addView(graphView);
			
			
			graphView.addSeries(series);
		}
		btnExport.setEnabled(true);
	}

	public void btnExportAction(View btnExport_) {
		final ArrayList<Uri> uris = createFiles();
//		if (uris != null) {
//			Intent sendIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
//			sendIntent.putExtra(Intent.EXTRA_SUBJECT, "CSV export");
//
//			sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
//
//			sendIntent.setType("plain/text");
//			startActivity(sendIntent);
//		}

		new Thread(new Runnable() {
			public void run() {
				for(int i=0;i<uris.size();i++) {
					uploadFile(uris.get(i).getPath());
				}

			}
		}).start();
	}
	
	private ArrayList<Uri> createFiles() {
		ArrayList<Uri> uris = new ArrayList<Uri>();
		
		try {
			int id = 1;
			for (Recording recording_ : recordings) {
				String filename = "recording_" + id;
				File dir = this.getExternalCacheDir();
				File file = File.createTempFile(filename, ".csv", dir);
				FileOutputStream fos = new FileOutputStream(file);
				
				int i = 0;
				fos.write(recording_.getName().getBytes());
				fos.write(";x;y;z\r\n".getBytes());
				for (Float[] dataF : recording_.getData()) {
					Log.d("eh", "_" + dataF[0]);
					String dataString = "" + i + ";" + dataF[0] + ";" + dataF[1] + ";" + dataF[2] + "\r\n";
					dataString.replace('.', ',');
					fos.write(dataString.getBytes());
					
					i++;
				}			
				
				fos.close();
				Uri uri = Uri.fromFile(file);
				uris.add(uri);
				Log.d("URI", uri.getPath());
				id++;
			}
			
			return uris;
		}	catch (IOException e) {
			Log.e(MainActivity.class.getName(), e.getMessage());
			return null;
		}
	}

	private void startRecording() {
		txtRecordingName.setEnabled(false);
		sensorManager.registerListener
			( this
			, accelerometer
			, SensorManager.SENSOR_DELAY_GAME
			);
		
		setRecording(new Recording());
		btnExport.setEnabled(false);
		counter = 0;
	}
	
	public void btnRecordAction(View btnAction_) {
		ToggleButton btnAction = (ToggleButton) btnAction_;
		if (btnAction.isChecked()) {
			startRecording();
		} else {
			stopRecording();
		}
	}
	
	private void idInit() {
        setContentView(R.layout.activity_main);
        
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
			  
		txtRecordingName = (EditText) findViewById(R.id.txtRecordingName);
		btnExport = (Button) findViewById(R.id.btnExport);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idInit();
		upLoadServerUri = "http://011.006.092.145.hva.nl/upload.php";
		recordings = new ArrayList<Recording>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	private void update() {
		
	}
    
	public void onSensorChanged(SensorEvent event) {
		recording.addData(event.values);
		update();
		TextView lblCounter = (TextView) findViewById(R.id.lblCounter);
		lblCounter.setText("updates: " + Long.toString(counter));
		counter++;
	}

	public void onAccuracyChanged(Sensor event, int accuracy) {
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	  super.onConfigurationChanged(newConfig);
	  //setContentView(R.layout.activity_main);
	}

	public int uploadFile(String sourceFileUri) {


		String fileName = sourceFileUri;

		HttpURLConnection conn = null;
		DataOutputStream dos = null;
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 1 * 1024 * 1024;
		File sourceFile = new File(sourceFileUri);

		if (!sourceFile.isFile()) {

			dialog.dismiss();

			Log.e("uploadFile", "Source File not exist :"
					+sourceFileUri);



			return 0;

		}
		else
		{
			try {

				// open a URL connection to the Servlet
				FileInputStream fileInputStream = new FileInputStream(sourceFile);
				URL url = new URL(upLoadServerUri);

				// Open a HTTP  connection to  the URL
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoInput(true); // Allow Inputs
				conn.setDoOutput(true); // Allow Outputs
				conn.setUseCaches(false); // Don't use a Cached Copy
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Connection", "Keep-Alive");
				conn.setRequestProperty("ENCTYPE", "multipart/form-data");
				conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
				conn.setRequestProperty("uploaded_file", fileName);

				dos = new DataOutputStream(conn.getOutputStream());

				dos.writeBytes(twoHyphens + boundary + lineEnd);
				dos.writeBytes("Content-Disposition: form-data; name="+"test"+";filename="
								+ fileName + "" + lineEnd);

						dos.writeBytes(lineEnd);

				// create a buffer of  maximum size
				bytesAvailable = fileInputStream.available();

				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				buffer = new byte[bufferSize];

				// read file and write it into form...
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);

				while (bytesRead > 0) {

					dos.write(buffer, 0, bufferSize);
					bytesAvailable = fileInputStream.available();
					bufferSize = Math.min(bytesAvailable, maxBufferSize);
					bytesRead = fileInputStream.read(buffer, 0, bufferSize);

				}

				// send multipart form data necesssary after file data...
				dos.writeBytes(lineEnd);
				dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

				// Responses from the server (code and message)
				serverResponseCode = conn.getResponseCode();
				String serverResponseMessage = conn.getResponseMessage();

				Log.i("uploadFile", "HTTP Response is : "
						+ serverResponseMessage + ": " + serverResponseCode);

				if(serverResponseCode == 200){

					Log.d("state", "done");
				}

				//close the streams //
				fileInputStream.close();
				dos.flush();
				dos.close();

			} catch (MalformedURLException ex) {

				ex.printStackTrace();


				Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
			} catch (Exception e) {

				e.printStackTrace();

				Log.e("Upload file to server Exception", "Exception : "
						+ e.getMessage(), e);
			}
			return serverResponseCode;

		} // End else block
	}

}
