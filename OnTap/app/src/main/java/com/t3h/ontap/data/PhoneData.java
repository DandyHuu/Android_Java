package com.t3h.ontap.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.t3h.ontap.Conts;
import com.t3h.ontap.model.Phone;

import java.util.ArrayList;

public class PhoneData {
    private ContentResolver resolver;
    public PhoneData(Context context){
        resolver = context.getContentResolver();
    }

    private ArrayList<Phone> arr;

    public ArrayList<Phone> readPhone(){
        arr = new ArrayList<Phone>();
        Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null, null);

        cursor.moveToFirst();

        int index = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
        int name = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int phone = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
        int img = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI);

        while (cursor.isAfterLast() == false){
//            String[] colums = cursor.getColumnNames();
//            for (int i = 0; i < colums.length; i++) {
//                Log.e(colums[i],cursor.getString(i)+"");
//            }
//            Log.e("---------------------","==================================");
            int id = cursor.getInt(index);
            String user = cursor.getString(name);
            String sdt = cursor.getString(phone);
            String image = cursor.getString(img);
            Phone item = new Phone(id, user,sdt,image);
            arr.add(item);
            cursor.moveToNext();
        }
        return arr;
    }

    public int updateContact(){

        ContentValues values = new ContentValues();
        values.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, Conts.EXTRA_NAME);
        values.put(ContactsContract.CommonDataKinds.Phone.DATA, Conts.EXTRA_PHONE);
//        values.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, Conts.EXTRA_NAME);
//        values.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, Conts.EXTRA_NAME);
        String where =  ContactsContract.CommonDataKinds.Phone._ID + "= ?";
        String[] whereArr = {Conts.EXTRA_ID};
        int result = resolver.update(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,values,where,whereArr);
        return 0;
    }
}
