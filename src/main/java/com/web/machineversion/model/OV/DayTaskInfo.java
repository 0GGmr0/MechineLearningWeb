package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Property;
import lombok.Data;

import javax.xml.soap.Text;

@Data
public class DayTaskInfo {
    //日程时间
    @JsonProperty("time")
    private String time;
    //日程内容
    @JsonProperty("content")
    private Text content;
}
