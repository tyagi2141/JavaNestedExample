package rahultyag.in.javanestedexample;

import androidx.appcompat.app.AppCompatActivity;
import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.IStatusListener;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;
import rahultyag.in.javanestedexample.adapter.SimpleArrayListAdapter;
import rahultyag.in.javanestedexample.adapter.SimpleListAdapter;
import rahultyag.in.javanestedexample.model.Area;
import rahultyag.in.javanestedexample.model.Country;
import rahultyag.in.javanestedexample.model.Employee;
import rahultyag.in.javanestedexample.model.Example;
import rahultyag.in.javanestedexample.model.Region;
import rahultyag.in.javanestedexample.model.ResponseData;
import rahultyag.in.javanestedexample.model.Zone;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	APIInterface apiInterface;
	
	private SearchableSpinner mSearchableSpinner;
	private SearchableSpinner mSearchableSpinner1;
	private SearchableSpinner mSearchableSpinner2;
	private SearchableSpinner mSearchableSpinner3;
	private SimpleListAdapter mSimpleListAdapter;
	private SimpleArrayListAdapter mSimpleArrayListAdapter;
	private final ArrayList<String> mStrings = new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		apiInterface = APIClient.getClient().create(APIInterface.class);
		
		initListValues();
		mSimpleListAdapter = new SimpleListAdapter(this, mStrings);
		mSimpleArrayListAdapter = new SimpleArrayListAdapter(this, mStrings);
		
		mSearchableSpinner = (SearchableSpinner) findViewById(R.id.SearchableSpinner);
		mSearchableSpinner.setAdapter(mSimpleArrayListAdapter);
		mSearchableSpinner.setOnItemSelectedListener(mOnItemSelectedListener);
		mSearchableSpinner.setStatusListener(new IStatusListener() {
			@Override
			public void spinnerIsOpening() {
				mSearchableSpinner1.hideEdit();
				mSearchableSpinner2.hideEdit();
			}
			
			@Override
			public void spinnerIsClosing() {
			
			}
		});
		
		mSearchableSpinner1 = (SearchableSpinner) findViewById(R.id.SearchableSpinner1);
		mSearchableSpinner1.setAdapter(mSimpleListAdapter);
		mSearchableSpinner1.setOnItemSelectedListener(mOnItemSelectedListener);
		mSearchableSpinner1.setStatusListener(new IStatusListener() {
			@Override
			public void spinnerIsOpening() {
				mSearchableSpinner.hideEdit();
				mSearchableSpinner2.hideEdit();
			}
			
			@Override
			public void spinnerIsClosing() {
			
			}
		});
		
		mSearchableSpinner2 = (SearchableSpinner) findViewById(R.id.SearchableSpinner2);
		mSearchableSpinner2.setAdapter(mSimpleListAdapter);
		mSearchableSpinner2.setOnItemSelectedListener(mOnItemSelectedListener);
		mSearchableSpinner2.setStatusListener(new IStatusListener() {
			@Override
			public void spinnerIsOpening() {
				mSearchableSpinner.hideEdit();
				mSearchableSpinner1.hideEdit();
			}
			
			@Override
			public void spinnerIsClosing() {
			
			}
		});
		
		mSearchableSpinner3 = (SearchableSpinner) findViewById(R.id.SearchableSpinner3);
		mSearchableSpinner3.setAdapter(mSimpleListAdapter);
		mSearchableSpinner3.setOnItemSelectedListener(mOnItemSelectedListener);
		mSearchableSpinner3.setStatusListener(new IStatusListener() {
			@Override
			public void spinnerIsOpening() {
				mSearchableSpinner.hideEdit();
				mSearchableSpinner3.hideEdit();
			}
			
			@Override
			public void spinnerIsClosing() {
			
			}
		});
		
		Call<Example> call = apiInterface.doGetListResources();
		call.enqueue(new Callback<Example>() {
			@Override
			public void onResponse(Call<Example> call, Response<Example> response) {
				clearDatabase();
				
				SaveTask st = new SaveTask();
				st.execute(response.body().getResponseData());
				
			}
			
			@Override
			public void onFailure(Call<Example> call, Throwable t) {
			
			}
		});
		
		getArea();
		getCountry();
		getEmployee();
		getRegion();
		getZone();
	}
	
	class SaveTask extends AsyncTask<ResponseData, Void, Void> {
		
		@Override
		protected Void doInBackground(ResponseData... voids) {
			
			ResponseData responseData=voids[0];
			
			//adding to database
			for (Area area:responseData.getArea()){
				DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
						.taskDao()
						.insertAllArea(area);
				Log.e("AREASSS",area.toString());
			}
			for (Country country:responseData.getCountry()){
				DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
						.taskDao()
						.insertAllCountry(country);
				Log.e("COUNTRYYY",country.toString());
			}
			for (Employee employee:responseData.getEmployee()){
				DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
						.taskDao()
						.insertAllEmployee(employee);
				Log.e("EMPLOYEEEEE",employee.toString());
			}
			for (Region region:responseData.getRegion()){
				DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
						.taskDao()
						.insertAllRegion(region);
				Log.e("REGIONSSS",region.toString());
			}
			for (Zone zone:responseData.getZone()){
				DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
						.taskDao()
						.insertAllZone(zone);
				Log.e("ZONEeee",zone.toString());
			}
		
			return null;
		}
	}
	
	private void getArea() {
		class GetTasks extends AsyncTask<Void, Void, List<Area>> {
			
			@Override
			protected List<Area> doInBackground(Void... voids) {
				List<Area> taskList = DatabaseClient
						.getInstance(getApplicationContext())
						.getAppDatabase()
						.taskDao()
						.getAllArea();
				
				Log.e("AREAAAAAAA",taskList.toString());
				return taskList;
			}
			@Override
			protected void onPostExecute(List<Area> tasks) {
				super.onPostExecute(tasks);
				
			}
		}
		
		GetTasks gt = new GetTasks();
		gt.execute();
	}
	private void getCountry() {
		class GetCountry extends AsyncTask<Void, Void, List<Country>> {
			
			@Override
			protected List<Country> doInBackground(Void... voids) {
				List<Country> taskList = DatabaseClient
						.getInstance(getApplicationContext())
						.getAppDatabase()
						.taskDao()
						.getAllCountry();
				
				Log.e("COUNTRYYYYYY",taskList.toString());
				return taskList;
			}
			@Override
			protected void onPostExecute(List<Country> tasks) {
				super.onPostExecute(tasks);
				
			}
		}
		
		GetCountry gt = new GetCountry();
		gt.execute();
	}
	
	private void getEmployee() {
		class GetEmployee extends AsyncTask<Void, Void, List<Employee>> {
			
			@Override
			protected List<Employee> doInBackground(Void... voids) {
				List<Employee> taskList = DatabaseClient
						.getInstance(getApplicationContext())
						.getAppDatabase()
						.taskDao()
						.getAllEmployee();
				
				Log.e("EMPLOYEEEE",taskList.toString());
				return taskList;
			}
			@Override
			protected void onPostExecute(List<Employee> tasks) {
				super.onPostExecute(tasks);
				
			}
		}
		
		GetEmployee gt = new GetEmployee();
		gt.execute();
	}
	
	private void getRegion() {
		class GetEmployee extends AsyncTask<Void, Void, List<Region>> {
			
			@Override
			protected List<Region> doInBackground(Void... voids) {
				List<Region> taskList = DatabaseClient
						.getInstance(getApplicationContext())
						.getAppDatabase()
						.taskDao()
						.getAllRegion();
				
				Log.e("REGIONNNNNN",taskList.toString());
				return taskList;
			}
			@Override
			protected void onPostExecute(List<Region> tasks) {
				super.onPostExecute(tasks);
				
			}
		}
		
		GetEmployee gt = new GetEmployee();
		gt.execute();
	}
	
	private void getZone() {
		class GetEmployee extends AsyncTask<Void, Void, List<Zone>> {
			
			@Override
			protected List<Zone> doInBackground(Void... voids) {
				List<Zone> taskList = DatabaseClient
						.getInstance(getApplicationContext())
						.getAppDatabase()
						.taskDao()
						.getAllZone();
				
				Log.e("ZONEEEEEEE",taskList.toString());
				return taskList;
			}
			@Override
			protected void onPostExecute(List<Zone> tasks) {
				super.onPostExecute(tasks);
				
			}
		}
		
		GetEmployee gt = new GetEmployee();
		gt.execute();
	}
	public void clearDatabase(){
		DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().deleteArea();
		DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().deleteCountry();
		DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().deleteEmployee();
		DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().deleteRegion();
		DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().deleteZone();
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (!mSearchableSpinner.isInsideSearchEditText(event)) {
			mSearchableSpinner.hideEdit();
		}
		if (!mSearchableSpinner1.isInsideSearchEditText(event)) {
			mSearchableSpinner1.hideEdit();
		}
		if (!mSearchableSpinner2.isInsideSearchEditText(event)) {
			mSearchableSpinner2.hideEdit();
		}
		return super.onTouchEvent(event);
	}
	
	private OnItemSelectedListener mOnItemSelectedListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(View view, int position, long id) {
			Toast.makeText(MainActivity.this, "Item on position " + position + " : " + mSimpleListAdapter.getItem(position) + " Selected", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		public void onNothingSelected() {
			Toast.makeText(MainActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
		}
	};
	
	private void initListValues() {
		mStrings.add("Brigida Kurz");
		mStrings.add("Tracy Mckim");
		mStrings.add("Iesha Davids");
		mStrings.add("Ozella Provenza");
		mStrings.add("Florentina Carriere");
		mStrings.add("Geri Eiler");
		mStrings.add("Tammara Belgrave");
		mStrings.add("Ashton Ridinger");
		mStrings.add("Jodee Dawkins");
		mStrings.add("Florine Cruzan");
		mStrings.add("Latia Stead");
		mStrings.add("Kai Urbain");
		mStrings.add("Liza Chi");
		mStrings.add("Clayton Laprade");
		mStrings.add("Wilfredo Mooney");
		mStrings.add("Roseline Cain");
		mStrings.add("Chadwick Gauna");
		mStrings.add("Carmela Bourn");
		mStrings.add("Valeri Dedios");
		mStrings.add("Calista Mcneese");
		mStrings.add("Willard Cuccia");
		mStrings.add("Ngan Blakey");
		mStrings.add("Reina Medlen");
		mStrings.add("Fabian Steenbergen");
		mStrings.add("Edmond Pine");
		mStrings.add("Teri Quesada");
		mStrings.add("Vernetta Fulgham");
		mStrings.add("Winnifred Kiefer");
		mStrings.add("Chiquita Lichty");
		mStrings.add("Elna Stiltner");
		mStrings.add("Carly Landon");
		mStrings.add("Hans Morford");
		mStrings.add("Shawanna Kapoor");
		mStrings.add("Thomasina Naron");
		mStrings.add("Melba Massi");
		mStrings.add("Sal Mangano");
		mStrings.add("Mika Weitzel");
		mStrings.add("Phylis France");
		mStrings.add("Illa Winzer");
		mStrings.add("Kristofer Boyden");
		mStrings.add("Idalia Cryan");
		mStrings.add("Jenni Sousa");
		mStrings.add("Eda Forkey");
		mStrings.add("Birgit Rispoli");
		mStrings.add("Janiece Mcguffey");
		mStrings.add("Barton Busick");
		mStrings.add("Gerald Westerman");
		mStrings.add("Shalanda Baran");
		mStrings.add("Margherita Pazos");
		mStrings.add("Yuk Fitts");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		//noinspection SimplifiableIfStatement
		if (id == R.id.action_reset) {
			mSearchableSpinner.setSelectedItem(0);
			mSearchableSpinner1.setSelectedItem(0);
			mSearchableSpinner2.setSelectedItem(0);
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
