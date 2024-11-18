package util;

import entity.Location;
import entity.Ride;

import java.util.List;
import java.util.UUID;

public class Helper {
    public static int calculateDistance(Location start, Location end){
        int startLat = start.getLatitude();
        int startLon = start.getLongitude();
        int endLat = end.getLatitude();
        int endLon = end.getLongitude();

        // Cartesian distance formula for integers
        return (int) Math.sqrt(Math.pow(endLat - startLat, 2) + Math.pow(endLon - startLon, 2));
    }
    public static String getId(){
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString().replace("-", "").substring(0, 6);
        return uuidString;
    }

    public static Integer calculateEarning(List<Ride> rides) {
        int ans = 0;
        for(int i=0;i<rides.size();i++){
            ans+=(calculateDistance(rides.get(i).getEnd(),rides.get(i).getStart()));
        }
        ans*=10;
        return ans;
    }
}
