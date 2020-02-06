
package rahultyag.in.javanestedexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Country {
   /* @PrimaryKey(autoGenerate = true)
    public int id;*/
    
    @PrimaryKey
    @NonNull
    @SerializedName("country")
    @Expose
    private String country="";
    @SerializedName("territory")
    @Expose
    private String territory="";

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }
    
    @Override
    public String toString() {
        return "Country{" +
                "country='" + country + '\'' +
                ", territory='" + territory + '\'' +
                '}';
    }
}
