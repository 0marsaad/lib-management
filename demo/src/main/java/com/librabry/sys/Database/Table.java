package com.librabry.sys.Database;

import com.librabry.sys.Model.*;
public interface Table {

    // Define methods that all tables should implement
    void addRecord(model record);
    void deleteRecord(String id);
    model getRecord(String id);
    void updateRecord(model record);
    model[] getAllRecords();
    
}