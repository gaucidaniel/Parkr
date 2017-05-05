package com.danielgauci.parkr.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by daniel on 5/4/17.
 */

public class ParkingArea implements Parcelable {

    private String id;
    private String name;
    private String region;
    private Location location;
    private int zoom;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public Location getLocation() {
        return location;
    }

    public int getZoom() {
        return zoom;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.region);
        dest.writeParcelable(this.location, flags);
        dest.writeInt(this.zoom);
    }

    public ParkingArea() {
    }

    protected ParkingArea(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.region = in.readString();
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.zoom = in.readInt();
    }

    public static final Parcelable.Creator<ParkingArea> CREATOR = new Parcelable.Creator<ParkingArea>() {
        @Override
        public ParkingArea createFromParcel(Parcel source) {
            return new ParkingArea(source);
        }

        @Override
        public ParkingArea[] newArray(int size) {
            return new ParkingArea[size];
        }
    };
}
