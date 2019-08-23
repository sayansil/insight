package iedc_beast.yolo_reciever;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    private Context mContext;
    private List<Item> itemList;


    public ItemAdapter(@NonNull Context context, ArrayList<Item> list) {
        super(context, 0, list);
        mContext = context;
        itemList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);

        Item currentItem = itemList.get(position);

        int dpAsPixels = (int) (8*(getContext().getResources().getDisplayMetrics().density));

        ImageView image = listItem.findViewById(R.id.iconitem);
        image.setImageResource(currentItem.getmImageDrawable());
        if(currentItem.getmImageDrawable() == R.drawable.ok) {
            image.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.tick_bg));
            image.setPadding(dpAsPixels,dpAsPixels,dpAsPixels,dpAsPixels);
        }
        else {
            image.setBackgroundColor(0x00000000);
            image.setPadding(0,0,0,0);
        }

        TextView name = listItem.findViewById(R.id.nameitem);
        name.setText(currentItem.getmName());

        TextView qty = listItem.findViewById(R.id.qtyitem);
        qty.setText(currentItem.getmQty());

        TextView date = listItem.findViewById(R.id.dateitem);
        date.setText(currentItem.getmDate());

        TextView time = listItem.findViewById(R.id.timeitem);
        time.setText(currentItem.getmTime());

        return listItem;
    }
}


