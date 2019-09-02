package com.pojo.excel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDetail {

    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy")
    private Date orderDate;

    private String region;
    private String representative;
    private String item;
    private int units;
    private double unitCost;
    private double total;
}
