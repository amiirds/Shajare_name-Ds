package sample;

import java.util.ArrayList;

public class Person {

    private final int ID;
    private final String firstName;
    private final String lastName;
    private final PersonType type;
    private final String birth; // تولد
    private final String death; // وفات
    private final int dadID;
    private final int momID;
    private final ArrayList<Integer> partnerID = new ArrayList<>();
    private final PersonRatio ratio;

    public Person(int ID,
                  String firstName,
                  String lastName,
                  PersonType type,
                  String birth,
                  String death,
                  int dadID,
                  int momID,
                  int... partnerID){
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.birth = birth;
        if (death != null) this.death = death;
        else this.death = "00.00.00";
        this.dadID = dadID;
        this.momID = momID;
        if (!(partnerID.length >= 2 &&( partnerID[0] == partnerID[1] && partnerID[0] == -1))) {
            if (type == PersonType.MALE)
                for (int id : partnerID) {if (dadID != id && momID != id) this.partnerID.add(id);}
            else {if (dadID != partnerID[0] && momID != partnerID[0]) this.partnerID.add(partnerID[0]);}
        }
        this.ratio = new PersonRatio();

        try {
            Person dad = CounterPerson.findPersonByID(dadID);
            dad.getRatioFromChild(this);
        }catch (Exception ignored){}

        try {
            Person mom = CounterPerson.findPersonByID(momID);
            mom.getRatioFromChild(this);
        }catch (Exception ignored){}

        for (int id : this.partnerID) {
            try {
                Person part = CounterPerson.findPersonByID(id);
                part.getRatioFromPartner(this);
            }catch (Exception ignored){}
        }

        CounterPerson.add(this);
    }
    public Person(int ID,
                  String firstName,
                  String lastName,
                  PersonType type,
                  String birth,
                  int dadID,
                  int momID,
                  int... partnerID){
        this(ID, firstName, lastName, type, birth, null, dadID, momID, partnerID);
    }
    public Person(int ID,
                  String firstName,
                  String lastName,
                  PersonType type,
                  String birth,
                  int dadID,
                  int momID){
        this(ID, firstName, lastName, type, birth, null, dadID, momID, -1, -1);
    }
    public Person(int ID,
                  String firstName,
                  String lastName,
                  PersonType type,
                  String birth,
                  String death,
                  int dadID,
                  int momID){
        this(ID, firstName, lastName, type, birth, death, dadID, momID, -1, -1);
    }


    public int getID(){return ID;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getName() {return firstName + " " + lastName;}
    public String getBirth() {return birth;}
    public String getDeath() {return death;}
    public int getDadID() {return dadID;}
    public int getMomID() {return momID;}
    public PersonType getType() {return type;}
    public ArrayList<Integer> getPartnerID() {return partnerID;}
    public PersonRatio getRatio() {return ratio;}

    public void addPartner(int partnerID) {
        if (type != PersonType.FEMALE | this.partnerID.size() == 0) {
            Person partner = CounterPerson.findPersonByID(partnerID);
            partner.getRatioFromPartner(this);
            this.partnerID.add(partnerID);
        }
    }

    public void getRatioFromChild(Person child){
        if (type == PersonType.FEMALE && partnerID.size() != 0 && partnerID.get(0) != child.dadID)
            throw new IllegalArgumentException();
        else if (!(type == PersonType.FEMALE ? partnerID.contains(child.dadID) : partnerID.contains(child.momID))){
            if (type == PersonType.FEMALE){
                partnerID.add(child.dadID);
                CounterPerson.findPersonByID(child.dadID).getRatioFromPartner(this);
            }else {
                partnerID.add(child.momID);
                CounterPerson.findPersonByID(child.momID).getRatioFromPartner(this);
            }
        }

        if (type == PersonType.FEMALE){
            child.ratio.daye.addAll(ratio.brothers);
            child.ratio.khale.addAll(ratio.sisters);
            for (int x : ratio.son) {
                if(! child.ratio.brothers.contains(x)) child.ratio.brothers.add(x);
            }
            for (int x : ratio.daughter) {
                if(! child.ratio.sisters.contains(x)) child.ratio.sisters.add(x);
            }
        }
        else {
            child.ratio.amou.addAll(ratio.brothers);
            child.ratio.amme.addAll(ratio.sisters);
            child.ratio.sisters.addAll(ratio.daughter);
            child.ratio.brothers.addAll(ratio.son);
        }

        for (int x : ratio.son) CounterPerson.findPersonByID(x).getRatioFromOtherChild(child);
        for (int x : ratio.daughter) CounterPerson.findPersonByID(x).getRatioFromOtherChild(child);

        if(child.type == PersonType.FEMALE) ratio.daughter.add(child.ID);
        else ratio.son.add(child.ID);

    }
    public void getRatioFromPartner(Person partner){
        if (type == partner.type)
            throw new IllegalArgumentException();

        if (type == PersonType.FEMALE){
            for (int x : partner.partnerID){
                try {
                    Person hav = CounterPerson.findPersonByID(x);
                    if (x != ID) ratio.hawo.add(hav.ID);
                } catch (Exception ignored) { }
            }
            for (int x : partner.ratio.brothers) {
                for (int xx : CounterPerson.findPersonByID(x).partnerID){
                    try {
                        Person jar = CounterPerson.findPersonByID(xx);
                        ratio.jary.add(xx);
                        jar.ratio.jary.add(ID);
                    } catch (Exception ignored) { }
                }
            }
            for (int x : ratio.sisters){
                for (int xx : CounterPerson.findPersonByID(x).partnerID){
                    try {
                        Person baj = CounterPerson.findPersonByID(xx);
                        partner.ratio.bajenagh.add(xx);
                        baj.ratio.bajenagh.add(partner.ID);
                    }catch (Exception ignored) { }
                }
            }

        } else {
            for (int x : partnerID){
                try {
                    Person part = CounterPerson.findPersonByID(x);
                    part.ratio.hawo.add(partner.ID);
                    partner.ratio.hawo.add(part.ID);
                } catch (Exception ignored) { }
            }
            for (int x : partner.ratio.sisters) {
                for (int xx : CounterPerson.findPersonByID(x).partnerID){
                    try {
                        Person baj = CounterPerson.findPersonByID(xx);
                        ratio.bajenagh.add(xx);
                        baj.ratio.bajenagh.add(ID);
                    } catch (Exception ignored) { }
                }
            }
            for (int x : ratio.brothers){
                for (int xx : CounterPerson.findPersonByID(x).partnerID){
                    try {
                        Person jar = CounterPerson.findPersonByID(xx);
                        partner.ratio.jary.add(xx);
                        jar.ratio.jary.add(partner.ID);
                    } catch (Exception ignored) { }
                }
            }
        }

        partnerID.add(partner.ID);
    }
    public void getRatioFromOtherChild(Person otherChild){
        if (otherChild.type == PersonType.FEMALE){
            if (!ratio.sisters.contains(otherChild.ID)) ratio.sisters.add(otherChild.ID);
            if (type == PersonType.FEMALE){
                for (int c : ratio.son) CounterPerson.findPersonByID(c).ratio.khale.add(otherChild.ID);
                for (int c : ratio.daughter) CounterPerson.findPersonByID(c).ratio.khale.add(otherChild.ID);
            } else {
                for (int c : ratio.son) CounterPerson.findPersonByID(c).ratio.amme.add(otherChild.ID);
                for (int c : ratio.daughter) CounterPerson.findPersonByID(c).ratio.amme.add(otherChild.ID);
            }
        } else {
            if (!ratio.brothers.contains(otherChild.ID)) ratio.brothers.add(otherChild.ID);
            if (type == PersonType.FEMALE){
                for (int c : ratio.son) CounterPerson.findPersonByID(c).ratio.daye.add(otherChild.ID);
                for (int c : ratio.daughter) CounterPerson.findPersonByID(c).ratio.daye.add(otherChild.ID);
            } else {
                for (int c : ratio.son) CounterPerson.findPersonByID(c).ratio.amou.add(otherChild.ID);
                for (int c : ratio.daughter) CounterPerson.findPersonByID(c).ratio.amou.add(otherChild.ID);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("\t%d:   %s %s%n", ID, firstName, lastName);
    }
}
