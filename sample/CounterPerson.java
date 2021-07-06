package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class CounterPerson {
    private static final ArrayList<Person> list = new ArrayList<>();

     /*  //TESTING VALUES for me ...
    static {
        list.add(new Person(50, "محمد", "عزتی", PersonType.MALE, "0", 0, 0));
        list.add(new Person(51, "مریم", "عزتی", PersonType.FEMALE, "0", 0, 0, 50));
        list.add(new Person(52, "صادق", "عزتی", PersonType.MALE, "0", 50, 51));
        list.add(new Person(53, "مسعود", "عزتی", PersonType.MALE, "0", 50, 51));
        list.add(new Person(54, "کیمیا", "عزتی", PersonType.FEMALE, "0", 50, 51));
        list.add(new Person(55, "فرزانه", "عزتی", PersonType.FEMALE, "0", 50, 51));
        list.add(new Person(56, "داوود", "عزتی", PersonType.MALE, "0", 0, 0, 55));
        list.add(new Person(57, "علی", "عزتی", PersonType.MALE, "0", 0, 0, 54));
        list.add(new Person(58, "مهسا", "عزتی", PersonType.FEMALE, "0", 0, 0, 52));
        list.add(new Person(59, "مهین", "عزتی", PersonType.FEMALE, "0", 0, 0, 53));
        list.add(new Person(60, "تقی", "عزتی", PersonType.MALE, "0", 53, 59));
        list.add(new Person(61, "رضا", "عزتی", PersonType.MALE, "0", 52, 58));
        list.add(new Person(62, "محمود", "عزتی", PersonType.MALE, "0", 57, 54));
        list.add(new Person(63, "وحید", "عزتی", PersonType.MALE, "0", 56, 55));
    } */

    public static ObservableList<Person> getObservableList(){ return FXCollections.observableList(list); }

    public static void add(Person person){
        for (Person p : list) if (p.getID() == person.getID()) throw new IllegalArgumentException();

        list.add(person);
    }
    public static Person findPersonByID(int ID){
        for (Person p : list) if (p.getID() == ID) return p;

        throw new IllegalArgumentException();
    }
    public static ArrayList<Person> findPersonByName(String name){
        ArrayList<Person> result = new ArrayList<>();
        for (Person p : list) if (p.getFirstName().equalsIgnoreCase(name) || p.getLastName().equalsIgnoreCase(name))
            result.add(p);

        return result;
    }


    public static String getRatio(int ID1, int ID2){ return getRatio(ID1, ID2, true); }

    public static String getRatio(int ID1, int ID2, boolean bool) {
        Person person1 = findPersonByID(ID1);
        Person person2 = findPersonByID(ID2);
        Person momP1;
        try { momP1 =  findPersonByID(person1.getMomID()); }
        catch (Exception e) { momP1 = null; }


        if(person1.getID() == person2.getID()) return "خودش";
        if (person1.getDadID() == person2.getID() || person1.getMomID() == person2.getID()){
            if (person2.getType() == PersonType.FEMALE) return PersonRatio.S2;
            else return PersonRatio.S1;
        }
        if (person2.getDadID() == person1.getID() || person2.getMomID() == person1.getID()){
            if (person2.getType() == PersonType.FEMALE) return PersonRatio.S3;
            else return PersonRatio.S4;
        }
        try {
            for (int dadParts : findPersonByID(person1.getDadID()).getPartnerID()){
                if (person2.getID() == dadParts) return PersonRatio.S5 + PersonRatio.S1;
            }
        }catch (Exception ignored) {}
        try {
            if (findPersonByID(person1.getMomID()).getPartnerID().get(0) == person2.getID())
                return PersonRatio.S6 + PersonRatio.S2;
        }catch (Exception ignored) {}

        if (bool){
            for (int x : person1.getPartnerID()){
                if (x == person2.getID()) {
                    if (person2.getType() == PersonType.MALE) return PersonRatio.S5;
                    else return PersonRatio.S6;
                }
            }
        }
        for (int x : person1.getPartnerID()){
            try {
                Person partner = findPersonByID(x);
                if (partner.getDadID() == person2.getID()){
                    if (person1.getType() == PersonType.MALE) return PersonRatio.S1 + PersonRatio.S5;
                    else return PersonRatio.S1 + PersonRatio.S6;
                }
                else if (partner.getMomID() == person2.getID()){
                    if (person1.getType() == PersonType.MALE) return PersonRatio.S2 + PersonRatio.S5;
                    else return PersonRatio.S2 + PersonRatio.S6;
                }
            }catch (Exception ignored) {}
        }
        for (int x = 0; x < person2.getPartnerID().size() ; x++){
            try {
                Person partner = findPersonByID(person2.getPartnerID().get(x));
                if (partner.getDadID() == person1.getID()  ||
                        partner.getMomID() == person1.getID()){
                    if (person2.getType() == PersonType.MALE) return PersonRatio.S6 + PersonRatio.S3;
                    else return PersonRatio.S5 + PersonRatio.S4;
                }
            }catch (Exception ignored) {}
        }
        for (int x : person1.getRatio().sisters){
            if (findPersonByID(x) == person2) //ignored
                if ((person2.getMomID() == person1 .getMomID()) && (person1.getDadID() == person2.getDadID()))
                    return PersonRatio.S7;
                else return PersonRatio.S7 + PersonRatio.SPlus2;
        }
        for (int x : person1.getRatio().brothers){
            if (findPersonByID(x) == person2) //ignored
                if ((person2.getMomID() == person1 .getMomID()) && (person1.getDadID() == person2.getDadID()))
                    return PersonRatio.S8;
                else return PersonRatio.S8 + PersonRatio.SPlus2;
        }

        for (int x : person1.getRatio().daye){
            if (x == person2.getID()) return PersonRatio.S10;
        }
        for (int x : person1.getRatio().khale){
            if (x == person2.getID()) return PersonRatio.S11;
        }
        for (int x : person1.getRatio().amou){
            if (x == person2.getID()) return PersonRatio.S12;
        }
        for (int x : person1.getRatio().amme){
            if (x == person2.getID()) return PersonRatio.S13;
        }
        for (int x : person1.getRatio().bajenagh){
            if (x == person2.getID()) return PersonRatio.S14;
        }
        for (int x : person1.getRatio().jary){
            if (x == person2.getID()) return PersonRatio.S15;
        }
        for (int x : person1.getRatio().hawo){
            if (x == person2.getID()) return PersonRatio.S16;
        }
        for (int x : person1.getRatio().sisters){
            for (int xx : findPersonByID(x).getPartnerID()){
                if (person2.getID() == xx) return PersonRatio.S6 + PersonRatio.S7;
            }
        }
        for (int x : person1.getRatio().brothers){
            for (int xx : findPersonByID(x).getPartnerID()){
                if (person2.getID() == xx) return PersonRatio.S5 + PersonRatio.S8;
            }
        }
        for (int x : person2.getRatio().sisters){
            for (int xx : findPersonByID(x).getPartnerID()){
                if (person1.getID() == xx) return PersonRatio.S8 + PersonRatio.S5;
            }
        }
        for (int x : person2.getRatio().brothers){
            for (int xx : findPersonByID(x).getPartnerID()){
                if (person1.getID() == xx) return PersonRatio.S7 + PersonRatio.S6;
            }
        }
        //
        for (int x : person2.getRatio().daye){
            if (x == person1.getID()){
                if (person2.getType() == PersonType.MALE) return PersonRatio.S4 + PersonRatio.S7;
                else return PersonRatio.S3 + PersonRatio.S7;
            }
        }
        for (int x : person2.getRatio().khale){
            if (x == person1.getID()) {
                if (person2.getType() == PersonType.MALE) return PersonRatio.S4 + PersonRatio.S7;
                else return PersonRatio.S3 + PersonRatio.S7;
            }
        }
        for (int x : person2.getRatio().amou){
            if (x == person1.getID()) {
                if (person2.getType() == PersonType.MALE) return PersonRatio.S4 + PersonRatio.S8;
                else return PersonRatio.S3 + PersonRatio.S8;
            }
        }
        for (int x : person2.getRatio().amme) {
            if (x == person1.getID()) {
                if (person2.getType() == PersonType.MALE) return PersonRatio.S4 + PersonRatio.S8;
                else return PersonRatio.S3 + PersonRatio.S8;
            }
        }
        for (int x : person1.getRatio().amou){
            for (int p1 : findPersonByID(x).getRatio().son){ //ignored...
                if (person2.getID() == p1){
                    if (person2.getType() == PersonType.MALE) return PersonRatio.S4 + PersonRatio.S12;
                    else return PersonRatio.S3 + PersonRatio.S12;
                }
            }
        }
        for (int x : person1.getRatio().amme){
            for (int p1 : findPersonByID(x).getRatio().son){ //ignored...
                if (person2.getID() == p1) {
                    if (person2.getType() == PersonType.MALE) return PersonRatio.S4 + PersonRatio.S13;
                    else return PersonRatio.S3 + PersonRatio.S13;
                }
            }
        }
        for (int x : person1.getRatio().khale){
            for (int p1 : findPersonByID(x).getRatio().son){ //ignored...
                if (person2.getID() == p1) {
                    if (person2.getType() == PersonType.MALE) return PersonRatio.S4 + PersonRatio.S11;
                    else return PersonRatio.S3 + PersonRatio.S11;
                }
            }
        }
        for (int x : person1.getRatio().daye){
            for (int p1 : findPersonByID(x).getRatio().son){ //ignored...
                if (person2.getID() == p1) {
                    if (person2.getType() == PersonType.MALE) return PersonRatio.S4 + PersonRatio.S10;
                    else return PersonRatio.S3 + PersonRatio.S10;
                }
            }
        }
        for (int x : person2.getRatio().son){
            if (person1.getID() == x) return PersonRatio.S9;
        }
        for (int x : person2.getRatio().daughter){
            if (person1.getID() == x) return PersonRatio.S9;
        }
        if (momP1 != null){
            for (int x : momP1.getRatio().son){
                if (person2.getID() == x) return PersonRatio.S9;
            }
            for (int x : momP1.getRatio().daughter){
                if (person2.getID() == x) return PersonRatio.S9;
            }
        }
        if (isLastChild(person2, person1)) return PersonRatio.S9;
        if (isLastChild(person1, person2))
            return (person2.getType() == PersonType.MALE ? PersonRatio.S1 : PersonRatio.S2) + PersonRatio.SPlus1;
        

        return PersonRatio.S0;
    }


    public static StringBuilder getPersonsXRatio(int ID, RatioType type){
        return getPersonsXRatio(findPersonByID(ID), type);
    }

    public static StringBuilder getPersonsXRatio(String index, RatioType type){
        StringBuilder result = new StringBuilder("");
        for (Person person : Objects.requireNonNull(findPersonByName(index)))
            result.append(getPersonsXRatio(person, type));
        return result;
    }

    public static StringBuilder getPersonsXRatio(Person person, RatioType type){
        StringBuilder result = new StringBuilder(
                person.getFirstName() + "\t" + person.getLastName() + "\tنتیجه: \n");

        if (type == RatioType.MADAR){
            try { result.append(findPersonByID(person.getMomID()).toString()); }
            catch (Exception ignored) {}
        }
        else if (type == RatioType.PEDAR) {
            try { result.append(findPersonByID(person.getDadID()).toString()); }
            catch (Exception ignored) {}
        }
        else if (type == RatioType.ZAN){
            if (person.getType() == PersonType.MALE && person.getPartnerID().size() != 0)
                for (int id : person.getPartnerID())
                    try { result.append(findPersonByID(id).toString()); }
                    catch (Exception ignored) {}
        }
        else if (type == RatioType.SHOUHAR){
            if (person.getType() == PersonType.FEMALE && person.getPartnerID().size() != 0){
                for (int id : person.getPartnerID())
                    try { result.append(findPersonByID(id).toString()); }
                    catch (Exception ignored) {}
            }
        }
        else if (type == RatioType.BARADAR){
            for (int id : person.getRatio().brothers)
                result.append(findPersonByID(id).toString());
        }
        else if (type == RatioType.KHAHAR){
            for (int id : person.getRatio().sisters)
                result.append(findPersonByID(id).toString());
        }
        else if (type == RatioType.PESAR){
            for (int id : person.getRatio().son)
                result.append(findPersonByID(id).toString());
        }
        else if (type == RatioType.DOKHTAR){
            for (int id : person.getRatio().daughter)
                result.append(findPersonByID(id).toString());
        }
        else if (type == RatioType.DAYE){
            for (int id : person.getRatio().daye)
                result.append(findPersonByID(id).toString());
        }
        else if (type == RatioType.KHALE){
            for (int id : person.getRatio().khale)
                result.append(findPersonByID(id).toString());
        }
        else if (type == RatioType.AMOU){
            for (int id : person.getRatio().amou)
                result.append(findPersonByID(id).toString());
        }
        else if (type == RatioType.AMME){
            for (int id : person.getRatio().amme)
                result.append(findPersonByID(id).toString());
        }
        else if (type == RatioType.BAJENAGH){
            for (int id : person.getRatio().bajenagh)
                try { result.append(findPersonByID(id).toString()); }
                catch (Exception ignored) {}
        }
        else if (type == RatioType.JARY){
            for (int id : person.getRatio().jary)
                try { result.append(findPersonByID(id).toString()); }
                catch (Exception ignored) {}
        }
        else if (type == RatioType.HAWO){
            for (int id : person.getRatio().hawo)
                try { result.append(findPersonByID(id).toString()); }
                catch (Exception ignored) {}
        }
        else result.append("NON\n");

        result.append(
                "\n   -------------------------\n");
        return result;
    }


    public static StringBuilder nonRatioPartners(){
        StringBuilder result = new StringBuilder("");
        ArrayList<Person> list2 = new ArrayList<>(list);

        for (Person person : list2) {
            for (int x : person.getPartnerID()){
                try {
                    if (getRatio(person.getID(), x, false).equalsIgnoreCase(PersonRatio.S0)){
                        result.append(person.toString());
                        result.append(" - ");
                        result.append(findPersonByID(x).toString());
                        result.append(" -------------------------\n");
                    }
                } catch (Exception ignored) {}
            }
        }

        return result;
    }

    public static Person getBigBabiesOfParent() {
        HashMap<Person, Integer> map = new HashMap<>();
        for (Person person : list) map.put(person, babyCount(person));
        int val = 0;
        Person result = list.get(0);

        for (Map.Entry e : map.entrySet()){
            if ((Integer) e.getValue() > val){
                val = (Integer) e.getValue();
                result = (Person) e.getKey();
            }
        }
        return result;
    }


    private static int babyCount(Person person){
        int result = 0;
        result += person.getRatio().son.size();
        result += person.getRatio().daughter.size();

        for (int id : person.getRatio().son)
            result += babyCount(findPersonByID(id));
        for (int id : person.getRatio().daughter)
            result += babyCount(findPersonByID(id));

        return result;
    }

    private static boolean isLastChild(Person child, Person dad){
        boolean result = false;

        for (int x : dad.getRatio().son){
            if (child.getID() == x) return true;
            else result = isLastChild(child, findPersonByID(x));
            if (result) return true;
        }
        for (int x : dad.getRatio().daughter){
            if (child.getID() == x) return true;
            else result = isLastChild(child, findPersonByID(x));
            if (result) return true;
        }
        return false;
    }

}
