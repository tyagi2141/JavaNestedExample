package rahultyag.in.javanestedexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.IStatusListener;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;
import rahultyag.in.javanestedexample.adapter.AreaListAdapter;
import rahultyag.in.javanestedexample.adapter.CountryListAdapter;
import rahultyag.in.javanestedexample.adapter.EmployeeListAdapter;
import rahultyag.in.javanestedexample.adapter.RegionListAdapter;
import rahultyag.in.javanestedexample.adapter.ZoneListAdapter;
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

public class MainActivity extends AppCompatActivity {
	APIInterface apiInterface;
	
	private SearchableSpinner CountrySpinner;
	private SearchableSpinner AreaSpinner;
	private SearchableSpinner ZoneSpinner;
	private SearchableSpinner EmployeeSpinner;
	private SearchableSpinner RegionSpinner;
	
	private EmployeeListAdapter employeeAdapter;
	private CountryListAdapter countryAdapter;
	private AreaListAdapter AreaListAdapter;
	private ZoneListAdapter ZoneListAdapter;
	private RegionListAdapter RegionListAdapters;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		apiInterface = APIClient.getClient().create(APIInterface.class);
		EmployeeSpinner = findViewById(R.id.EmployeeSpinnerId);
		RegionSpinner = findViewById(R.id.RegionAapterId);
		ZoneSpinner = findViewById(R.id.ZoneSpinnerId);
		AreaSpinner = findViewById(R.id.AreaSpinnerId);
		CountrySpinner = findViewById(R.id.CountrySpinnerId);
		
		CountrySpinner.setOnItemSelectedListener(mOnItemSelectedListenerCountry);
		EmployeeSpinner.setOnItemSelectedListener(mOnItemSelectedListenerEmployee);
		AreaSpinner.setOnItemSelectedListener(mOnItemSelectedListenerArea);
		ZoneSpinner.setOnItemSelectedListener(mOnItemSelectedListenerZone);
		RegionSpinner.setOnItemSelectedListener(mOnItemSelectedListenerRegion);
		
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		
		
		
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
		
	
		getCountry();
		
		
		
	}
	
	class SaveTask extends AsyncTask<ResponseData, Void, Void> {
		
		@Override
		protected Void doInBackground(ResponseData... voids) {
			
			ResponseData responseData=voids[0];
			
			for (Area area:responseData.getArea()){
				DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
						.taskDao()
						.insertAllArea(area);
			}
			for (Country country:responseData.getCountry()){
				DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
						.taskDao()
						.insertAllCountry(country);
			}
			for (Employee employee:responseData.getEmployee()){
				DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
						.taskDao()
						.insertAllEmployee(employee);
			}
			for (Region region:responseData.getRegion()){
				DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
						.taskDao()
						.insertAllRegion(region);
			}
			for (Zone zone:responseData.getZone()){
				DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
						.taskDao()
						.insertAllZone(zone);
			}
		
			return null;
		}
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
				
				return taskList;
			}
			@Override
			protected void onPostExecute(List<Country> tasks) {
				super.onPostExecute(tasks);
				countryAdapter = new CountryListAdapter(getApplicationContext(), tasks);
				CountrySpinner.setAdapter(countryAdapter);
				
				CountrySpinner.setStatusListener(new IStatusListener() {
					@Override
					public void spinnerIsOpening() {
						hideSpinnerView();
					}
					
					@Override
					public void spinnerIsClosing() {
					
					}
				});
				
			}
		}
		
		GetCountry gt = new GetCountry();
		gt.execute();
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
				
				return taskList;
			}
			@Override
			protected void onPostExecute(List<Area> areas) {
				super.onPostExecute(areas);
				AreaListAdapter = new AreaListAdapter(getApplicationContext(), areas);
			
				AreaSpinner.setAdapter(AreaListAdapter);
				AreaSpinner.setStatusListener(new IStatusListener() {
					@Override
					public void spinnerIsOpening() {
						hideSpinnerView();
					}

					@Override
					public void spinnerIsClosing() {

					}
				});
				
			}
		}
		
		GetTasks gt = new GetTasks();
		gt.execute();
	}

	private void getEmployee(final String area) {
		class GetEmployee extends AsyncTask<Void, Void, List<Employee>> {
			
			@Override
			protected List<Employee> doInBackground(Void... voids) {
				List<Employee> taskList = DatabaseClient
						.getInstance(getApplicationContext())
						.getAppDatabase()
						.taskDao()
						.findEmployeeById(area);
				
				return taskList;
			}
			@Override
			protected void onPostExecute(List<Employee> tasks) {
				super.onPostExecute(tasks);
				employeeAdapter = new EmployeeListAdapter(getApplicationContext(), tasks);
			
				EmployeeSpinner.setAdapter(employeeAdapter);
				EmployeeSpinner.setStatusListener(new IStatusListener() {
					@Override
					public void spinnerIsOpening() {
						hideSpinnerView();
					}
					
					@Override
					public void spinnerIsClosing() {
						
					}
				});
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
				
				return taskList;
			}
			@Override
			protected void onPostExecute(List<Region> tasks) {
				super.onPostExecute(tasks);
				RegionListAdapters = new RegionListAdapter(getApplicationContext(), tasks);
			
				RegionSpinner.setAdapter(RegionListAdapters);
				RegionSpinner.setStatusListener(new IStatusListener() {
					@Override
					public void spinnerIsOpening() {
						hideSpinnerView();
					}
					
					@Override
					public void spinnerIsClosing() {
					
					}
				});
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
				return taskList;
			}
			@Override
			protected void onPostExecute(List<Zone> tasks) {
				super.onPostExecute(tasks);
				ZoneListAdapter = new ZoneListAdapter(getApplicationContext(), tasks);
			
				ZoneSpinner.setAdapter(ZoneListAdapter);
				ZoneSpinner.setStatusListener(new IStatusListener() {
					@Override
					public void spinnerIsOpening() {
						hideSpinnerView();
					}
					
					@Override
					public void spinnerIsClosing() {
					
					}
				});
				
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
		if (!CountrySpinner.isInsideSearchEditText(event)) {
			CountrySpinner.hideEdit();
		}
		if (!ZoneSpinner.isInsideSearchEditText(event)) {
			ZoneSpinner.hideEdit();
		}
		if (!EmployeeSpinner.isInsideSearchEditText(event)) {
			EmployeeSpinner.hideEdit();
		}if (!AreaSpinner.isInsideSearchEditText(event)) {
			AreaSpinner.hideEdit();
		}if (!RegionSpinner.isInsideSearchEditText(event)) {
			RegionSpinner.hideEdit();
		}
		return super.onTouchEvent(event);
	}
	
	private OnItemSelectedListener mOnItemSelectedListenerCountry = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(View view, int position, long id) {
					if (position==0){
						ZoneSpinner.setVisibility(View.GONE);
						AreaSpinner.setVisibility(View.GONE);
						RegionSpinner.setVisibility(View.GONE);
						EmployeeSpinner.setVisibility(View.GONE);
						//ZoneSpinner.setSelectedItem(0);
					}else {
						ZoneSpinner.setVisibility(View.VISIBLE);
						getZone();
					}
			
		}
		
		@Override
		public void onNothingSelected() {
			Toast.makeText(MainActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
		}
	};
	
	
	private OnItemSelectedListener mOnItemSelectedListenerArea = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(View view, int position, long id) {
			Area area=AreaListAdapter.getItem(position);
			
			if (position==0){
				EmployeeSpinner.setVisibility(View.GONE);
				AreaSpinner.setVisibility(View.GONE);
				RegionSpinner.setVisibility(View.GONE);
				
			}else {
				EmployeeSpinner.setVisibility(View.VISIBLE);
				getEmployee(area.getArea());
			}
		}
		
		@Override
		public void onNothingSelected() {
			Toast.makeText(MainActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
		}
	};
	private OnItemSelectedListener mOnItemSelectedListenerRegion = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(View view, int position, long id) {
			Region region=RegionListAdapters.getItem(position);
			//Toast.makeText(MainActivity.this, "Item on position " + position + " : " + region.getRegion() + " Selected", Toast.LENGTH_SHORT).show();
			if (position==0){
				AreaSpinner.setVisibility(View.GONE);
				EmployeeSpinner.setVisibility(View.GONE);
			}else {
				AreaSpinner.setVisibility(View.VISIBLE);
				getArea();
			}
			
		}
		
		@Override
		public void onNothingSelected() {
			Toast.makeText(MainActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
		}
	};
	private OnItemSelectedListener mOnItemSelectedListenerZone = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(View view, int position, long id) {
			if (position==0){
				AreaSpinner.setVisibility(View.GONE);
				RegionSpinner.setVisibility(View.GONE);
				EmployeeSpinner.setVisibility(View.GONE);
				
			}else {
				RegionSpinner.setVisibility(View.VISIBLE);
				getRegion();
			}
			
		}
		
		@Override
		public void onNothingSelected() {
			Toast.makeText(MainActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
		}
	};
	private OnItemSelectedListener mOnItemSelectedListenerEmployee = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(View view, int position, long id) {
			Employee area=employeeAdapter.getItem(position);
			
		}
		
		@Override
		public void onNothingSelected() {
			Toast.makeText(MainActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
		}
	};
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		
		
		if (id == R.id.action_reset) {
			CountrySpinner.setSelectedItem(0);
			ZoneSpinner.setSelectedItem(0);
			EmployeeSpinner.setSelectedItem(0);
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	public void hideSpinnerView(){
		CountrySpinner.hideEdit();
		EmployeeSpinner.hideEdit();
		AreaSpinner.hideEdit();
		RegionSpinner.hideEdit();
		ZoneSpinner.hideEdit();
	}
	
}
