package homework;

/**
 * @Description: disc05 No.2
 * @Author: whj
 * @Date: 2023-09-29 17:23
 */
public class OHRequest {
    public String description;
    public String name;
    public OHRequest next;

    public OHRequest(String description, String name, OHRequest next) {
        this.description = description;
        this.name = name;
        this.next = next;
    }

//    public int size() {
//        if (next == null) {
//            return 1;
//        }
//        return 1 + next.size();
//    }
}
