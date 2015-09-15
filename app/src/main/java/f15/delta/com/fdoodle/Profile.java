package f15.delta.com.fdoodle;

/**
 * Created by HP on 14-09-2015.
 */
public class Profile {

    private static int id;
    private static String name;
    private static String email;
    private static String full_name;



    public static void setProfile(int id,String name,String email,String full_name)
    {
        Profile.id=id;
        Profile.name=name;
        Profile.email=email;
        Profile.full_name=full_name;
    }

}
