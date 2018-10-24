package botelho.afonso.ywait;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurant implements Parcelable {

    private String id;
    private String name;
    private String desc;
    private String rating;

    public Restaurant(String id, String name, String desc, String rating) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.rating = rating;
    }

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getRating() {
        return rating;
    }



    // implement Parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeString(rating);
    }

    public static final Parcelable.Creator<Restaurant> CREATOR = new Parcelable.Creator<Restaurant>() {
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    private Restaurant(Parcel in) {
        id = in.readString();
        name = in.readString();
        desc = in.readString();
        rating = in.readString();
    }
}
