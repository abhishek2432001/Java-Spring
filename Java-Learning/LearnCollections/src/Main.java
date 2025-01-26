import java.util.*;

class StringLenghtComparator implements Comparator<String>{


    @Override
    public int compare(String o1, String o2) {
        return o1.length()-o2.length();
    }
}



public class Main {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>(10);
//        list.add(1);
//        System.out.println(list.get(0));
//
//        List<String>words = Arrays.asList("abhi", "banana", "ram", "shyam");
////        Comparator comp = new StringLenghtComparator();
////        words.sort(comp);
//
//        words.sort((a,b)->a.length()-b.length());
////        words.sort(comp);
//        System.out.println(words);
//        LinkedList<Integer>linkedlist = new LinkedList<>();
//        linkedlist.add(1);
//        linkedlist.add(2);
//        linkedlist.add(3);
//        System.out.println(linkedlist);
//        System.out.println(linkedlist.get(1));
//        linkedlist.addFirst(1);
//        linkedlist.addLast(4);
//        linkedlist.getFirst();


        //HASHMAP
        Map<String, Integer> map = new HashMap<>();
        map.put("abhi", 1);
        map.put("adi", 1);
        map.put("sumit", 1);
        map.put("ranjan", 1);
        for(String key: map.keySet()){
            System.out.println(map.get(key));
        }
        map.remove("abhi");
        // Displaying the Map
        System.out.println("Map elements: " + map);

        //HASHMAP
        LinkedHashMap<String, Integer> mp = new LinkedHashMap<>();
        mp.put("abhi", 1);
        mp.put("adi", 1);
        mp.put("sumit", 1);
        mp.put("ranjan", 1);
        for(String key: mp.keySet()){
            System.out.println(mp.get(key));
        }
        mp.remove("abhi");
        // Displaying the Map
        System.out.println(mp);
    }
}