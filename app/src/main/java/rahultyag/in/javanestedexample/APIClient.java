package rahultyag.in.javanestedexample;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class APIClient {
	
	private static Retrofit retrofit = null;
	
	static Retrofit getClient() {
		
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().build();
		
		
		retrofit = new Retrofit.Builder()
				.baseUrl("http://demo1929804.mockable.io/")
				.addConverterFactory(GsonConverterFactory.create())
				.client(client)
				.build();
		
		
		
		return retrofit;
	}

}
