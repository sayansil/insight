package iedc_beast.yolo_reciever;

public class Item {

    private int mImageDrawable;
    private String mName;
    private String mQty;
    private String mDate;
    private String mTime;

    public Item(int mImageDrawable, String mName, String mQty, String mDate, String mTime) {
        this.mImageDrawable = mImageDrawable;
        this.mName = mName;
        this.mQty = mQty;
        this.mDate = mDate;
        this.mTime = mTime;
    }

    public int getmImageDrawable() {
        return mImageDrawable;
    }

    public void setmImageDrawable(int mImageDrawable) {
        this.mImageDrawable = mImageDrawable;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmQty() {
        return mQty;
    }

    public void setmQty(String mQty) {
        this.mQty = mQty;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }


}