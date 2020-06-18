package timetravell.toolproject.exceptions;

public class Errors {
//    DUPLICATE_ROOM_NUMBER("E001", "Room with roomNumber added already exists."),
//    FACILITY_NOT_FOUND("E002", "Required facility was not found!"),
//    ROOM_NOT_FOUND("E003", "Required room was not found!"),
//    NO_RESERVATION_FOUND("E004", "The reservation was not found!"),
//    INVALID_DATE_FORMAT("E005", "Date format is not accepted!"),
//    NO_FILTER_GIVEN("E006", "No filter has been found"),
//    MISSING_ROOM_NUMBER_ARGUMENT( "E007", "You must insert a roomNumber"),
//    MISSING_FILTER_PARAMETER("E008", "Both 'checkIn' and 'checkOut' parameter is required for searching a room by an interval!"),
//    ROOM_ALREADY_BOOKED("E009", "The room is already booked for the given interval.");

    String code;
    String message;

    Errors(String code, String message){
        this.code = code;
        this.message = message;
    }
}
