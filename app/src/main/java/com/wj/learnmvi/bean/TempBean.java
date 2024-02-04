package com.wj.learnmvi.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author:WJ
 * Date:2024/1/17 17:04
 * Describe：
 */
public class TempBean extends ItemSelect {
    public String name;
    public int id;

    public TempBean( int id,String name,String title) {
        this.name = name;
        this.id = id;
        this.title=title;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.name);
        dest.writeInt(this.id);
    }

    public void readFromParcel(Parcel source) {
        super.readFromParcel(source);
        this.name = source.readString();
        this.id = source.readInt();
    }

    protected TempBean(Parcel in) {
        super(in);
        this.name = in.readString();
        this.id = in.readInt();
    }

    public static final Creator<TempBean> CREATOR = new Creator<TempBean>() {
        @Override
        public TempBean createFromParcel(Parcel source) {
            return new TempBean(source);
        }

        @Override
        public TempBean[] newArray(int size) {
            return new TempBean[size];
        }
    };
}
