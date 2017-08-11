package dongting.beijing.com.ipcdemo;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者:${董婷}
 * 日期:2017/8/3
 * 类的用途:
 * 实现思路:
 */

public class User implements Parcelable {
    private String username;
    private int age;

    public User(String username, int age){
        this.username=username;
        this.age=age;
    }

    public  static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            Bundle bundle = source.readBundle();

            return new User( bundle.getString("username"), bundle.getInt("age"));
        }

        @Override
        public User[] newArray(int size) {
            return new User[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putString("username",username);
        bundle.putInt("age",age);

        dest.writeBundle(bundle);
    }
}
