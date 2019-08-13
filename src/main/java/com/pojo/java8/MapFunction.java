package com.pojo.java8;

import com.pojo.java8.model.ResourceActionDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by sunitc on 8/2/17.
 */
public class MapFunction {

    public static void main(String[] args) {

        List<ResourceActionDTO> resourceActionList = createResourceActionList();

        Map<String, Map<String, ResourceActionDTO>> resourceActionMap =
                resourceActionList
                        .stream()
                        .collect(Collectors.groupingBy(ResourceActionDTO::getObject))
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                e -> e.getValue().stream().collect(
                                        Collectors.toMap(ResourceActionDTO::getPermission, Function.identity()))));



        Map<String, Map<String, Boolean>> resourceActionMeta =
                resourceActionMap
                        .entrySet()
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        Map.Entry::getKey,
                                        e -> e.getValue()
                                                .entrySet()
                                                .stream()
                                                .collect(
                                                        Collectors.toMap(
                                                                Map.Entry::getKey,
                                                                k -> k.getValue().getPermission().length() %2 == 0))));
        System.out.println(resourceActionMap);
        System.out.println(resourceActionMeta);


    }


    private static List<ResourceActionDTO> createResourceActionList() {
        List<ResourceActionDTO> resourceActionDTOList = new ArrayList<>();
        resourceActionDTOList.add((new ResourceActionDTO("E-FORM", "NEW", 1000, "com.liferay.ddmstructure", 2, "ADD_DDM_STRUCTURE")));
        resourceActionDTOList.add((new ResourceActionDTO("E-FORM", "EDIT", 1001, "com.liferay.ddmstructure", 2, "EDIT_DDM_STRUCTURE")));
        resourceActionDTOList.add((new ResourceActionDTO("E-FORM", "VIEW", 1002, "com.liferay.ddmstructure", 2, "VIEW_DDM_STRUCTURE")));
        resourceActionDTOList.add((new ResourceActionDTO("E-FORM", "ADD_TAG", 1003, "com.liferay.ddmstructure", 2, "ADD_TAG_TO_DDM_STRUCTURE")));
        resourceActionDTOList.add((new ResourceActionDTO("E-FORM", "ARCHIVE", 1004, "com.liferay.ddmstructure", 2, "ARCHIVE_DDM_STRUCTURE")));
        resourceActionDTOList.add((new ResourceActionDTO("E-FORM", "DELETE", 1005, "com.liferay.ddmstructure", 2, "DELETE_DDM_STRUCTURE")));


        resourceActionDTOList.add((new ResourceActionDTO("USER", "NEW", 2000, "com.liferay.model.user", 2, "ADD_USER")));
        resourceActionDTOList.add((new ResourceActionDTO("USER", "EDIT", 2001, "com.liferay.model.user", 2, "EDIT_USER")));
        resourceActionDTOList.add((new ResourceActionDTO("USER", "VIEW", 2002, "com.liferay.model.user", 2, "VIEW_USER")));
        resourceActionDTOList.add((new ResourceActionDTO("USER", "ARCHIVE", 2003, "com.liferay.model.user", 2, "ARCHIVE_USER")));
        resourceActionDTOList.add((new ResourceActionDTO("USER", "DELETE", 2004, "com.liferay.model.user", 2, "DELETE_USER")));

        resourceActionDTOList.add((new ResourceActionDTO("DOCUMENT", "NEW", 3000, "com.liferay.journal", 2, "ADD_JOURNAL_ARTICLE")));
        resourceActionDTOList.add((new ResourceActionDTO("DOCUMENT", "EDIT", 3001, "com.liferay.journal", 2, "EDIT_JOURNAL_ARTICLE")));
        resourceActionDTOList.add((new ResourceActionDTO("DOCUMENT", "VIEW", 3002, "com.liferay.journal", 2, "VIEW_JOURNAL_ARTICLE")));
        resourceActionDTOList.add((new ResourceActionDTO("DOCUMENT", "ADD_TAG", 3003, "com.liferay.journal", 2, "ADD_TAG_TO_JOURNAL_ARTICLE")));
        resourceActionDTOList.add((new ResourceActionDTO("DOCUMENT", "ARCHIVE", 3004, "com.liferay.journal", 2, "ARCHIVE_JOURNAL_ARTICLE")));
        resourceActionDTOList.add((new ResourceActionDTO("DOCUMENT", "DELETE", 3005, "com.liferay.journal", 2, "DELETE_JOURNAL_ARTICLE")));
        resourceActionDTOList.add((new ResourceActionDTO("DOCUMENT", "ANNOTATE", 3005, "com.liferay.journal", 2, "ADD_ANNOTATION_TO_JOURNAL_ARTICLE")));


        return resourceActionDTOList;
    }
}
