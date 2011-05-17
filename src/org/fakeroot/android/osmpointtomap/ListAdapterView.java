package org.fakeroot.android.osmpointtomap;

import org.fakeroot.android.osmpointtomap.pojos.KeyAmenityStyle;
import org.fakeroot.android.osmpointtomap.pojos.PoiDTO;
import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListAdapterView extends LinearLayout {

	public ListAdapterView(Context context, PoiDTO poiDto) {
		super(context);
		
		//Add picture
        
        LinearLayout.LayoutParams skyParams = 
            new LinearLayout.LayoutParams(25, LayoutParams.WRAP_CONTENT);

		ImageView skyControl = new ImageView( context );
		
		//setImage
		if(poiDto.getKeyName()!=null && poiDto.getKeyName() !=null){
			for(KeyAmenityStyle kas:Worker.getInstance().getKeys()){
				if(kas.getKey().equals(poiDto.getKeyName())){
					skyControl.setImageDrawable(kas.getMarkerPic());
				}
			}
		}
		
		
		addView( skyControl, skyParams );
		
		
		LinearLayout.LayoutParams cityParams = 
            new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        cityParams.setMargins(1, 1, 1, 1);

        TextView cityControl = new TextView( context );
        if(poiDto.getTitle()!=null)cityControl.setText(poiDto.getTitle());
        else cityControl.setText("null");
		
        cityControl.setTextSize(14f);
        cityControl.setTextColor(Color.WHITE);
        addView( cityControl, cityParams);       
          

        
	}

	
}

