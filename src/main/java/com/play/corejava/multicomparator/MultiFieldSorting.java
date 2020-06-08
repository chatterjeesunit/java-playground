package com.play.corejava.multicomparator;

import java.util.Comparator;
import java.util.List;



public class MultiFieldSorting {

    //Sorts persons first by department, then by lastname, and then by firstname
    public List<Person> basicSort(List<Person> persons) {

        Comparator<Person> personComparator = (p1, p2) -> {
            if (p1 == null) return 1;

            int departmentCompare = p1.getDepartment().compareTo(p2.getDepartment());
            if (departmentCompare == 0) {
                int lastNameCompare = p1.getLastName().compareTo(p2.getLastName());
                if (lastNameCompare == 0)
                    return p1.getFirstName().compareTo(p2.getFirstName());
                else
                    return lastNameCompare;
            } else return departmentCompare;
        };

        persons.sort(personComparator);
        return persons;
    }



    //Sort in any order depending on the order in which comparators are passed
    public List<Person> sortUsingMultiComparators(List<Person> persons, List<Comparator<Person>> comparators) {

        PersonMultiComparator<Person> personComparator = new PersonMultiComparator<>(comparators);
        persons.sort(personComparator);
        return persons;
    }
}
