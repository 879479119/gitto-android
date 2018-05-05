package com.stormphoenix.ogit.bridge;


import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 用于对没有发出去的日志进行持久化存储
 */
public class LogStorage {
    private Context activity;

    public LogStorage(Context context) {
        this.activity = context;
    }

    public boolean save(String text) {
        try {
            FileOutputStream outStream=activity.openFileOutput("a.txt", Context.MODE_APPEND);
            outStream.write(text.getBytes());
            outStream.write("\r\n".getBytes());
            outStream.close();
        } catch (IOException e){
            return false;
        }
        return true;

    }

    public void clear() {
        try {
            FileOutputStream outStream=activity.openFileOutput("a.txt", Context.MODE_PRIVATE);
            outStream.write("".getBytes());
            outStream.close();
        } catch (IOException ignored){
        }
    }

    public String load()
    {
        try {
            FileInputStream inStream=activity.openFileInput("a.txt");
            ByteArrayOutputStream stream=new ByteArrayOutputStream();
            byte[] buffer=new byte[1024];
            int length=-1;
            while((length=inStream.read(buffer))!=-1)   {
                stream.write(buffer,0,length);
            }


            stream.close();
            inStream.close();
            return stream.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            return "";
        }
        return "";
    }
}
