package f15.delta.com.fdoodle;

/**
 * Created by HP on 14-09-2015.
 */
public class Profile {

    public static int id;
    public static String name;
    public static String email;
    public static String full_name;
    public static String coupon="";



    public static void setProfile(int id,String name,String email,String full_name)
    {
        Profile.id=id;
        Profile.name=name;
        Profile.email=email;
        Profile.full_name=full_name;
    }
    public static void assigncoup(String coup)
    {
        coupon=coup;
    }

}
