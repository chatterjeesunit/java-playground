package com.play.corejava.string;

/**
 * Created by sunitc on 3/12/18.
 */
public class ReplaceAllTest {

    public static void main(String[] args) {

        String text = "<div class=\"document-content mb-10 col-md-12\" contenteditable=\"true\" id=\"structure_html40601\"><div class=\"fr-basic fr-top\"><div style=\"overflow: auto;\"><table style=\"width: 100%; margin-left: calc(0%); margin-right: calc(0%);\"><tbody><tr><td style=\"width: 100.0000%;\"><img src=\"$TEMPLATE_HEADER_URL$\" style=\"width: 568px; height: 79.8102px;\" class=\"fr-fic fr-dib fr-fil\"></td></tr></tbody></table><table style=\"width: 100%; margin-left: calc(0%); margin-right: calc(0%);\"><tbody><tr><td style=\"width: 100.0000%;\"><h2><strong><span style=\"color: rgb(44, 130, 201);\">Legislative Update</span></strong></h2><span style=\"color: rgb(44, 130, 201);\">(for internal use only – do not distribute to clients without approval by ADP Legal)</span></td></tr></tbody></table><table style=\"width: 100%;\"><tbody><tr><td style=\"width: 50%; background-color: rgb(173, 216, 230);\">Date Announced By Jurisdiction:</td><td style=\"width: 50.0000%;\">{{whats_new_date_announced_by_jurisdiction}}</td></tr><tr><td style=\"width: 50%; background-color: rgb(173, 216, 230);\">Date Effective:</td><td style=\"width: 50.0000%;\">{{whats_new_date_effective}}</td></tr></tbody></table><div class=\"fr-element fr-view\"></div></div></div></div>";

        String replacedText = text.replaceAll("\\$TEMPLATE_HEADER_URL\\$", "http://www.google.com");


        System.out.println(replacedText);

        System.out.println("[%24WEBSITE_URL%24]".replaceAll("%24", "\\$"));

    }
}
