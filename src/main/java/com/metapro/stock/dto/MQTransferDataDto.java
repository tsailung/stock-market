package com.metapro.stock.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MQTransferDataDto implements Serializable {

    private Date sendTime;
    private String data;
    private Integer count;

}
