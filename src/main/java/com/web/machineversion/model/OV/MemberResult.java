package com.web.machineversion.model.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class MemberResult<T> {

    @JsonProperty("teachers")
    private List<Member> teacherList;
    @JsonProperty("member")
    private List<Member> memberList;


    public void getMemberList(List<Member> memberInfoList) {
    }

    public void getTeacherList(List<Member> teacherInfoList) {
    }
}
