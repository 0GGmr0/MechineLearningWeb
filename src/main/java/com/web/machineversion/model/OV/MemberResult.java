package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MemberResult {
    @JsonProperty("teachers")
    private List<Member> teacherList;
    @JsonProperty("member")
    private List<Member> memberList;
}
