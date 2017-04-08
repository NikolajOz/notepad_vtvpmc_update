package com.example.om.notepad_vtvpmc;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by OM on 08.04.17.
 */



public class Utilities {
    public static final String FILE_EXTENSION = ".bin";
    public static boolean saveNote(Context context, Note note ){

        String fileName = String.valueOf(note.getmDateTime()) + FILE_EXTENSION;

        FileOutputStream fos;
        ObjectOutputStream cos;
        try{
            fos = context.openFileOutput(fileName, context.MODE_PRIVATE);
            cos = new ObjectOutputStream(fos);
            cos.writeObject(note);
            cos.close();
            fos.close();

        }catch (IOException e){
            e.printStackTrace();
            return false; // sakyti useriui kad kazkas ne taip
        }


        return true;
    }

    public static ArrayList<Note> getAllSaveNotes(Context context){

        ArrayList<Note> notes = new ArrayList<>();

        File filesDir = context.getFilesDir();
        ArrayList<String> noteFiles = new ArrayList<>();

        for (String file:filesDir.list()){
            if (file.endsWith(FILE_EXTENSION)){
                noteFiles.add(file);
            }
        }

        FileInputStream fis;
        ObjectInputStream ois;

        for (int i = 0; i < noteFiles.size(); i++) {
            try{
                fis = context.openFileInput(noteFiles.get(i));
                ois = new ObjectInputStream(fis);

                notes.add((Note) ois.readObject());
                fis.close();
                ois.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        return notes;
    }
    }






