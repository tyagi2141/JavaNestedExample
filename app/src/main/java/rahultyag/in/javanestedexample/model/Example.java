
package rahultyag.in.javanestedexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Example {
    
    @PrimaryKey(autoGenerate = true)
    public int id;
    
    @SerializedName("ResponseStatus")
    @Expose
    private Integer responseStatus;
    
    @PrimaryKey
    @NonNull
    @SerializedName("ResponseData")
    @Expose
    private ResponseData responseData;
    @SerializedName("Success")
    @Expose
    private Boolean success;

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
