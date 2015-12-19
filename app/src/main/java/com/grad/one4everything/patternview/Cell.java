package com.grad.one4everything.patternview;

import android.os.Parcel;
import android.os.Parcelable;


public class Cell implements Parcelable {

    private int row;
    private int column;
    public Cell(int row, int column) {
        CellUtils.checkRange(row, column);
        this.row = row;
        this.column = column;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public String getId() {
        final String formatRow = String.format("%03d", row);
        final String formatColumn = String.format("%03d", column);
        return formatRow + "-" + formatColumn;
    }


    @Override
    public String toString() {
        return "(r=" + getRow() + ",c=" + getColumn() + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Cell) {
            return getColumn() == ((Cell) object).getColumn() && getRow() == ((Cell) object).getRow();
        }
        return super.equals(object);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getColumn());
        parcel.writeInt(getRow());
    }


    public void readFromParcel(Parcel in) {
        column = in.readInt();
        row = in.readInt();
    }

    public static final Creator<Cell> CREATOR = new Creator<Cell>() {

        public Cell createFromParcel(Parcel in) {
            return new Cell(in);
        }

        public Cell[] newArray(int size) {
            return new Cell[size];
        }
    };

    private Cell(Parcel in) {
        readFromParcel(in);
    }
}
