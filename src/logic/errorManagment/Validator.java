package logic.errorManagment;

public class Validator {
    //  Methods
    public static boolean validateName(String name){
        int check=-1;
        for(char i='0'; i<='9';i++){
            check=name.indexOf(i);
        }
        if (name.isEmpty()){
            check=-1;
        }
        return check==-1;
    }
    public static boolean validateId(String id){
        //add more validation
        return !id.isEmpty();
    }
    public static boolean validatePassword(String password){
        return password.length()>=4;
    }
    public static boolean validateChoice(char choiceId){
        return (choiceId<='D'&&choiceId>='A')||choiceId=='\0';
    }
    public static boolean validateQuestionNumber(int questionNumber){
        return questionNumber>0;
    }
    public static boolean validatePoint(double point){
        return point>0&&point<10;
    }
    public static boolean validateTime(double time){
        return time>0&&time<10;
    }
    public static boolean validatePoint(String point){
        char[] temp=point.toCharArray();
        for (char x:temp) {
            switch (x){
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '.':
                    break;
                default:
                    return false;
            }
        }
        return validatePoint(Double.parseDouble(point));
    }
    public static boolean validateTime(String time){
        char[] temp=time.toCharArray();
        for (char x:temp) {
            switch (x){
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '.':
                    break;
                default:
                    return false;
            }
        }
        return validateTime(Double.parseDouble(time));
    }
}
