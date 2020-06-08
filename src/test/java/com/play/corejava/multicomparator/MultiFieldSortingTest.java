package com.play.corejava.multicomparator;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


class MultiFieldSortingTest {

    final List<Person> persons =  List.of(
            new Person("Abraham", "Zilger", "Sales"),
            new Person("Donald", "Duck", "Marketing"),
            new Person("John", "Doe", "Sales"),
            new Person("Aaron", "Hank", "Sales"),
            new Person("Jane", "Ace", "Sales"),
            new Person("Henry", "Adams", "Marketing"),
            new Person("Abigail", "Adams", "Sales"),
            new Person("Henry", "Adams", "Sales"),
            new Person("Samuel", "Adams", "Marketing"),
            new Person("Samuel", "Hank", "Sales"),
            new Person("Alfred", "Alder", "Sales")
    );

    final MultiFieldSorting sorter = new MultiFieldSorting();


    @Nested
    @DisplayName("Test For Basic Sorting")
    class BasicSortingTest {
        @Test
        void shouldSortByDeptThenByLastNameThenByFirstName() {

            final List<Person> sortedPersons = sorter.basicSort(new ArrayList<>(persons));

            Assertions.assertThat(sortedPersons)
                    .extracting("department", "lastName", "firstName")
                    .containsExactly(
                            Tuple.tuple("Marketing", "Adams", "Henry"),
                            Tuple.tuple("Marketing", "Adams", "Samuel"),
                            Tuple.tuple("Marketing", "Duck", "Donald"),
                            Tuple.tuple("Sales", "Ace", "Jane"),
                            Tuple.tuple("Sales", "Adams", "Abigail"),
                            Tuple.tuple("Sales", "Adams", "Henry"),
                            Tuple.tuple("Sales", "Alder", "Alfred"),
                            Tuple.tuple("Sales", "Doe", "John"),
                            Tuple.tuple("Sales", "Hank", "Aaron"),
                            Tuple.tuple("Sales", "Hank", "Samuel"),
                            Tuple.tuple("Sales", "Zilger", "Abraham")
                    );
        }
    }


    @Nested
    @DisplayName("Test For Multi Comparator Sorting")
    class PersonMultiComparatorTest {
        @Test
        void shouldSortByDepartmentThenByLastNameThenByFirstName() {

            final List<Comparator<Person>> comparators = List.of(
                    Comparator.comparing(Person::getDepartment),
                    Comparator.comparing(Person::getLastName),
                    Comparator.comparing(Person::getFirstName)
            );

            final List<Person> sortedPersons = sorter.sortUsingMultiComparators(new ArrayList<>(persons), comparators);

            Assertions.assertThat(sortedPersons)
                    .extracting("department", "lastName", "firstName")
                    .containsExactly(
                            Tuple.tuple("Marketing", "Adams", "Henry"),
                            Tuple.tuple("Marketing", "Adams", "Samuel"),
                            Tuple.tuple("Marketing", "Duck", "Donald"),
                            Tuple.tuple("Sales", "Ace", "Jane"),
                            Tuple.tuple("Sales", "Adams", "Abigail"),
                            Tuple.tuple("Sales", "Adams", "Henry"),
                            Tuple.tuple("Sales", "Alder", "Alfred"),
                            Tuple.tuple("Sales", "Doe", "John"),
                            Tuple.tuple("Sales", "Hank", "Aaron"),
                            Tuple.tuple("Sales", "Hank", "Samuel"),
                            Tuple.tuple("Sales", "Zilger", "Abraham")
                    );
        }


        @Test
        void shouldSortByLastNameThenByFirstNameThenByDepartment() {

            final List<Comparator<Person>> comparators = List.of(
                    Comparator.comparing(Person::getLastName),
                    Comparator.comparing(Person::getFirstName),
                    Comparator.comparing(Person::getDepartment)
            );

            final List<Person> sortedPersons = sorter.sortUsingMultiComparators(new ArrayList<>(persons), comparators);

            Assertions.assertThat(sortedPersons)
                    .extracting("department", "lastName", "firstName")
                    .containsExactly(
                            Tuple.tuple("Sales", "Ace", "Jane"),
                            Tuple.tuple("Sales", "Adams", "Abigail"),
                            Tuple.tuple("Marketing", "Adams", "Henry"),
                            Tuple.tuple("Sales", "Adams", "Henry"),
                            Tuple.tuple("Marketing", "Adams", "Samuel"),
                            Tuple.tuple("Sales", "Alder", "Alfred"),
                            Tuple.tuple("Sales", "Doe", "John"),
                            Tuple.tuple("Marketing", "Duck", "Donald"),
                            Tuple.tuple("Sales", "Hank", "Aaron"),
                            Tuple.tuple("Sales", "Hank", "Samuel"),
                            Tuple.tuple("Sales", "Zilger", "Abraham")
                    );
        }
    }


    @Nested
    @DisplayName("Test For Multiple Comparators using Java 8 thenComparing()")
    class PersonMultiComparatorTestUsingJava8Comparators {
        @Test
        void shouldSortByDepartmentThenByLastNameThenByFirstName() {

            final Comparator<Person> personComparator =
                    Comparator.comparing(Person::getDepartment)
                            .thenComparing(Person::getLastName)
                            .thenComparing(Person::getFirstName);

            List<Person> personList = new ArrayList<>(persons);
            Collections.sort(personList, personComparator);

            Assertions.assertThat(personList)
                    .extracting("department", "lastName", "firstName")
                    .containsExactly(
                            Tuple.tuple("Marketing", "Adams", "Henry"),
                            Tuple.tuple("Marketing", "Adams", "Samuel"),
                            Tuple.tuple("Marketing", "Duck", "Donald"),
                            Tuple.tuple("Sales", "Ace", "Jane"),
                            Tuple.tuple("Sales", "Adams", "Abigail"),
                            Tuple.tuple("Sales", "Adams", "Henry"),
                            Tuple.tuple("Sales", "Alder", "Alfred"),
                            Tuple.tuple("Sales", "Doe", "John"),
                            Tuple.tuple("Sales", "Hank", "Aaron"),
                            Tuple.tuple("Sales", "Hank", "Samuel"),
                            Tuple.tuple("Sales", "Zilger", "Abraham")
                    );
        }


        @Test
        void shouldSortByLastNameThenByFirstNameThenByDepartment() {

            final Comparator<Person> personComparator =
                    Comparator.comparing(Person::getLastName)
                            .thenComparing(Person::getFirstName)
                            .thenComparing(Person::getDepartment);

            List<Person> personList = new ArrayList<>(persons);
            Collections.sort(personList, personComparator);

            Assertions.assertThat(personList)
                    .extracting("department", "lastName", "firstName")
                    .containsExactly(
                            Tuple.tuple("Sales", "Ace", "Jane"),
                            Tuple.tuple("Sales", "Adams", "Abigail"),
                            Tuple.tuple("Marketing", "Adams", "Henry"),
                            Tuple.tuple("Sales", "Adams", "Henry"),
                            Tuple.tuple("Marketing", "Adams", "Samuel"),
                            Tuple.tuple("Sales", "Alder", "Alfred"),
                            Tuple.tuple("Sales", "Doe", "John"),
                            Tuple.tuple("Marketing", "Duck", "Donald"),
                            Tuple.tuple("Sales", "Hank", "Aaron"),
                            Tuple.tuple("Sales", "Hank", "Samuel"),
                            Tuple.tuple("Sales", "Zilger", "Abraham")
                    );
        }
    }



}
