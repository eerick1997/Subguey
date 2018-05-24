package DataBases.SQLite;

public class Utilities {

    /**
     * -------------- Here we store information about a data base that
     * contains information about each line (STCM or STPP)
     **/
    public static final String DB_LINES_nameDB = "DataBaseLines.db",
            DB_LINES_nameLine = "line", DB_LINES_ID = "identifier",
            DB_LINES_latitude = "latitude", DB_LINES_longitude = "longitude",
            DB_LINES_tbl = "lines";
    /** -------------. END DATABASE LINES INFORMATION -------------- **/

    /**
     * -------------- Here we store information about a data base that
     * contains information about each station (STCM or STPP)
     **/
    public static final String DB_STATIONS_nameDB = "DataBaseLines.db",
            DB_STATIONS_ID = "identifier", DB_STATIONS_Name = "name",
            DB_STATIONS_Line = "line", DB_STATIONS_position = "pisition",
            DB_STATIONS_next = "next", DB_STATIONS_previous = "previous",
            DB_STATIONS_services = "services", DB_STATIONS_exits = "exits",
            DB_STATIONS_tbl = "stations";

    /** -------------. END DATABASE STATION INFORMATION -------------- **/

    /**
     * -------------- HERE we store information about all our events
     * registered in Subguey
     **/

    public static final String DB_EVENTS_nameDB = "DataBaseEvents.db",
            DB_EVENT_tbl = "identifier", DB_EVENT_TYPE = "type",
            DB_EVENT_PUBLISHER = "publisher", DB_EVENT_HOUR = "HOUR",
            DB_EVENT_POSITION = "position";

    /*** --------------- END DATABASE EVENT INFORMATION -------------- **/

}
