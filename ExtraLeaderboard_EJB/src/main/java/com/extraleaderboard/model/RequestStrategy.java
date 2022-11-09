package com.extraleaderboard.model;

public enum RequestStrategy {
    TIMETOPOS, //provide a time value, get the corresponding position
    POSTOMAXTIME, //provide a position, get the maximum time necessary to achieve it

    MEDTOMAXTIME, //provide a medal, get the maximum time necessary to achieve it
    TIMETOMED, //provide a time value, get the corresponding medal

    MEDTOMAXPOS, //provide a medal, get the maximum position necessary to achieve it
    POSTOMED, //provide a position, get the corresponding medal

    POSTOPERCENT, //provide a position, get the corresponding percentage
    PERCENTTOPOS, //provide a percentage, get the position necessary to achieve it

    TIMETOPERCENT, //provide a time value, get the corresponding percentage
    PERCENTTOMAXTIME, //provide a percentage, get the maximum time value necessary to achieve it

    IDTOPOS, //provide a playerId, get the corresponding position
    IDTOTIME, //provide a playerId, get the corresponding time
    IDTOMED, //provide a playerId, get the corresponding medal

    PLAYERAMOUNT, //get the total amount of players that have competed on the map
    PLAYERAMOUNTMED, //provide a medal, get the total amount of players that have that medal on the map

    POS, //get the position of the current account on the map

}
