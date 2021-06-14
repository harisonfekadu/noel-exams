package logic.errorManagment;

public class FieldNotFound extends Exception {
    //  Fields
    private String fieldId;

    //  Constructors
    public FieldNotFound(String fieldId) {
        this.fieldId = fieldId;
    }

    //  Methods
    public String getFieldId() {
        return this.fieldId;
    }

    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Field " + this.fieldId + " was not found.";
    }
}
