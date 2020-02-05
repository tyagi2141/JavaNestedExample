package rahultyag.in.javanestedexample;

import rahultyag.in.javanestedexample.model.Example;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
	@GET("assignment")
	Call<Example> doGetListResources();
}
