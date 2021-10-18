package carroll.tbel.restapiexo.exceptions;

public class ElementAlreadyExistsException extends RuntimeException{

    public ElementAlreadyExistsException() {
        super("The element already exists");
    }
}
