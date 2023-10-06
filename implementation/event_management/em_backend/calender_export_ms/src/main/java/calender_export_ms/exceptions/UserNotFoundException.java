package calender_export_ms.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
    }

    public static UserNotFoundException getUserNotFoundException(){
        return new UserNotFoundException();
    }
}
