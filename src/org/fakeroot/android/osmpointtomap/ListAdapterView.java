package org.fakeroot.android.osmpointtomap;

import org.fakeroot.android.osmpointtomap.marker.OverlayMarker;
import org.fakeroot.android.osmpointtomap.pojos.KeyStyle;
import org.fakeroot.android.osmpointtomap.pojos.PoiDTO;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListAdapterView extends LinearLayout {

	public ListAdapterView(Context context, PoiDTO poiDto) {
		super(context);
		
		//Add picture
        
        LinearLayout.LayoutParams skyParams = 
            new LinearLayout.LayoutParams(60, LayoutParams.WRAP_CONTENT);

		ImageView skyControl = new ImageView( context );
		
		//setImage
		Worker worker = Worker.getInstance();
		if(poiDto.getKeyName()!=null && poiDto.getKeyName() !=null){
			for(KeyStyle kas:Worker.getInstance().getKeys()){
				if(kas.getKey().equals(poiDto.getKeyName())){
					skyControl.setImageDrawable(kas.getMarkerPic());
				}
			}
			
			for (KeyStyle key : worker.getKeys()) {
				if (key.getKey().equals(poiDto.getKeyName())) {
					
					if(key.getValue()!=null){
						for(KeyStyle value: key.getValue()){
							if(value.getKey().equals(poiDto.getKeyValue())){
								skyControl.setImageDrawable(value.getMarkerPic());
							}
						}
					}else{
						skyControl.setImageDrawable(key.getMarkerPic());
					}
					
					
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

