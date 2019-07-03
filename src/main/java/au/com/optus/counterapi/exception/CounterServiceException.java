package au.com.optus.counterapi.exception;

/**
 * author : Chandan Singh
 * date : 01/07/19
 */

public class CounterServiceException extends Exception {
    private static final long serialVersionUID = 1L;

    public CounterServiceException(String message, Throwable cause){
        super(message, cause);
    }
}
