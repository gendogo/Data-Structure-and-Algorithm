package practise;

/**
 * @Description: disc05 No.3
 * @Author: whj
 * @Date: 2023-09-29 19:52
 */
public class TYIterator extends OHIterator {
    public TYIterator(OHRequest queue) {
        super(queue);
    }

    @Override
    public OHRequest next() {
        OHRequest item = super.next();
        if (item != null && item.description.contains("thank u")) {
            //如果description包含"thank u"，那么跳过这个item，返回下一个
            return super.next();
        }
        return item;
    }

}
