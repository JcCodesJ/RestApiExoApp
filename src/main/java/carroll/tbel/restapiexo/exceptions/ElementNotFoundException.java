package carroll.tbel.restapiexo.exceptions;

public class ElementNotFoundException extends RuntimeException{

    public ElementNotFoundException() {
        super("The searched-for element was not found");
    }
}
