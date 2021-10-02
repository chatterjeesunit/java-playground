package com.corejava.collection;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DiffSet {

    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalDate TODAY_PLUS_10 = TODAY.plus(10, ChronoUnit.DAYS);
    private static final LocalDate TODAY_PLUS_20 = TODAY.plus(20, ChronoUnit.DAYS);
    private static final LocalDate TODAY_PLUS_30 = TODAY.plus(30, ChronoUnit.DAYS);


    public static void main(String[] args) {

        Set<InterestRate> oldInterestRates = Arrays.asList(
                new InterestRate(1, TODAY, BigDecimal.valueOf(5)),
                new InterestRate(2, TODAY_PLUS_10, BigDecimal.valueOf(7)),
                new InterestRate(3, TODAY_PLUS_20, BigDecimal.valueOf(9))
        ).stream().collect(Collectors.toSet());

        Set<InterestRate> newInterestRates = Arrays.asList(
                new InterestRate(null, TODAY, BigDecimal.valueOf(5)),  //Deleted and re-added back same date and same rate
                new InterestRate(2, TODAY_PLUS_10, BigDecimal.valueOf(7)),  // No Change
                new InterestRate(null, TODAY_PLUS_20, BigDecimal.valueOf(9.5)),  // Deleted and changed the rate
                new InterestRate(null, TODAY_PLUS_30, BigDecimal.valueOf(11.5))  // Newly added
        ).stream().collect(Collectors.toSet());


//        System.out.println("\n\n\n********************* GAUVA : Sets Difference *********************");
        Sets.SetView<InterestRate> removedSet = Sets.difference(oldInterestRates, newInterestRates);
        Sets.SetView<InterestRate> newAddedSet = Sets.difference(newInterestRates, oldInterestRates);


//        System.out.println("\n\n\n******************* GAUVA : Maps Difference **********************");
        Map<InterestRate, Object> oldInterestRateMap = oldInterestRates.stream().collect(Collectors.toMap(Function.identity(), InterestRate::getDate));
        Map<InterestRate, Object> newInterestRateMap = newInterestRates.stream().collect(Collectors.toMap(Function.identity(), InterestRate::getDate));

        MapDifference<InterestRate, Object> mapDifference = Maps.difference(oldInterestRateMap, newInterestRateMap);



//        System.out.println("\n\n\n******************* CommonUtils Difference **********************");
        Collection<InterestRate> disjunction = CollectionUtils.disjunction(oldInterestRates, newInterestRates);
        List<InterestRate> entriesAdded = disjunction.stream().filter(InterestRate::isNew).collect(Collectors.toList());
        List<InterestRate> entriesDeleted = disjunction.stream().filter(i -> !i.isNew()).collect(Collectors.toList());

        System.out.println("GAUVA Sets Difference : Entries Deleted = \t" + removedSet);
        System.out.println("GAUVA Maps Difference : Entries Deleted = \t" + mapDifference.entriesOnlyOnLeft().keySet());
        System.out.println("Apache CommonUtils : Entries Deleted = \t\t" + entriesDeleted);


        System.out.println("\n\n\nGAUVA Sets Difference : Entries Added = \t" + newAddedSet);
        System.out.println("GAUVA Maps Difference : Entries Added = \t" + mapDifference.entriesOnlyOnRight().keySet());
        System.out.println("Apache CommonUtils : Entries Added = \t\t" + entriesAdded);
    }




}


@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id"}
)
@ToString
class InterestRate {
    private Integer id;
    private LocalDate date;
    private BigDecimal rate;

    public boolean isNew() {
        return id == null;
    }
}