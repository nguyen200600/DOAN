package com.example.doan;

import java.util.ArrayList;
import java.util.List;

public class ListEntry {
    public List<Entry> listEntry = new ArrayList<Entry>();

    public List<Entry> getListEntry() {
        return listEntry;
    }

    public void setListEntry(Entry _entry) {
        this.listEntry.add(_entry);
    }

    public ListEntry listEntryCopy() {
// copy danh sach entry vao list Entry
        ListEntry copy = new ListEntry();
        copy.listEntry = listEntry;
        return copy;
    }
}
