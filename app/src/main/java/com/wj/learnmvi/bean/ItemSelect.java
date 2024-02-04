package com.wj.learnmvi.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author:WJ
 * Date:2024/1/17 17:03
 * Describe：
 */
public class ItemSelect implements Parcelable {
    public String title;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
    }

    public void readFromParcel(Parcel source) {
        this.title = source.readString();
    }

    public ItemSelect() {
    }

    protected ItemSelect(Parcel in) {
        this.title = in.readString();
    }

}
