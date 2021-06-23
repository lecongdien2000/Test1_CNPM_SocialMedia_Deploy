import Database.Data.UsersData;
import Model.User;


public class Test {

    public static void main(String[] args){
        User user = UsersData.getUsers("abc");
        System.out.print(user.getPassword());

    }
}
