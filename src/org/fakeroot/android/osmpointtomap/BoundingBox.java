/*
 *  This file is part of OsmPointsToMap.
 *
 *  OsmPointsToMap is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OsmPointsToMap is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OsmPointsToMap.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.fakeroot.android.osmpointtomap;

import com.google.android.maps.GeoPoint;

/**
 * A BoundingBox return the x,y coordinates of a map view.
 * In returns the 2 corner GeoPoints and a Center Point 
 */
public class BoundingBox
{
    public final GeoPoint mCenter;
    public final GeoPoint mUpperLeftCorner;
    public final GeoPoint mLowerRightCorner;
    public final int mLatitudeSpan;
    public final int mLongitudeSpan;
    
    public BoundingBox(final GeoPoint center, final int latitudeSpan, final int longitudeSpan)
    {
        this.mCenter = center;
        this.mLatitudeSpan = latitudeSpan;
        this.mLongitudeSpan = longitudeSpan;
        this.mUpperLeftCorner = new GeoPoint(center.getLatitudeE6() + latitudeSpan / 2, center
                .getLongitudeE6()
                - longitudeSpan / 2);
        this.mLowerRightCorner = new GeoPoint(center.getLatitudeE6() - latitudeSpan / 2, center
                .getLongitudeE6()
                + longitudeSpan / 2);
    }
    
    public GeoPoint getCenter()
    {
        return this.mCenter;
    }
    
    public int getLatitudeSpan()
    {
        return this.mLatitudeSpan;
    }
    
    public int getLongitudeSpan()
    {
        return this.mLongitudeSpan;
    }
    
    @Override
    public String toString()
    {
        return "Center: CLat: " + this.mCenter.getLatitudeE6() + ", CLng: "
                + this.mCenter.getLongitudeE6() + " " + ", UL: Lat: "
                + this.getUpperLeftCorner().getLatitudeE6() + ", UL: Lng: "
                + this.getUpperLeftCorner().getLongitudeE6() + ", LR: Lat: "
                + this.getLowerRightCorner().getLatitudeE6() + ", LR: Lng: "
                + this.getLowerRightCorner().getLongitudeE6();
    }
    
    public GeoPoint getUpperLeftCorner()
    {
        return this.mUpperLeftCorner;
    }
    
    public GeoPoint getLowerRightCorner()
    {
        return this.mLowerRightCorner;
    }
}
