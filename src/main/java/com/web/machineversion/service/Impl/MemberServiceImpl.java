package com.web.machineversion.service.Impl;

import com.web.machineversion.dao.UserMapper;
import com.web.machineversion.model.OV.Member;
import com.web.machineversion.model.OV.MemberResult;
import com.web.machineversion.model.ResultTool;
import com.web.machineversion.model.entity.User;
import com.web.machineversion.model.entity.UserExample;
import com.web.machineversion.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private UserMapper userMapper;


    private String backgroundTypeIntegerToString(User user){
        Integer typeInt = user.getBackground();
        switch(typeInt){
            case 1 : return "本科生";
            case 2 : return "硕士生";
            case 3 : return "博士生";
        }
        return null;
    }

    private List<Member> getTeacherInfoList() {
        //获取所有rool为2即老师成员的信息
        UserExample userExample = new UserExample();
        userExample.createCriteria().andRoolEqualTo(2);
        //把获取到的所有老师信息存储到list里面
        List<User> userList = userMapper.selectByExample(userExample);
        //把存有老师信息的list转化成MemberList
        List<Member> teacherInfoList = new ArrayList<>();
        if(userList != null){
            for(User user : userList){
                Member member = new Member();
                member.setName(user.getUserName());
                member.setHeadshoturl(user.getAvatar());
                member.setContaction(user.getPhone());
                String[] position = new String[2];
                position[0] = new String(backgroundTypeIntegerToString(user));
                position[1] = new String(user.getSchool());
                member.getPosition(position);
                teacherInfoList.add(member);
            }
            return teacherInfoList;
        }
        else
            return null;
    }

    private List<Member> getMemberInfoList() {
        //获取所有rool为1即学生成员的信息
        UserExample userExample = new UserExample();
        userExample.createCriteria().andRoolEqualTo(1);
        //把获取到的所有学生信息存储到list里面
        List<User> userList = userMapper.selectByExample(userExample);
        //把存有学生信息的list转化成MemberList
        List<Member> memberInfoList = new ArrayList<>();
        if(userList != null){
            for(User user : userList){
                Member member = new Member();
                member.setName(user.getUserName());
                member.setHeadshoturl(user.getAvatar());
                member.setContaction(user.getPhone());
                String[] position = new String[2];
                position[0] = new String(backgroundTypeIntegerToString(user));
                position[1] = new String(user.getSchool());
                member.setPosition(position);
                memberInfoList.add(member);
            }
            return memberInfoList;
        }
        else
            return null;
    }

    @Override
    public com.web.machineversion.model.OV.Result getAllMember() {
        //获取所有的成员信息（包括老师成员的信息和学生成员的信息）
        MemberResult memberResult = new MemberResult();
        //获取学生成员的信息
        memberResult.setMemberList(getMemberInfoList());
        //获取老师成员的信息
        memberResult.setTeacherList(getTeacherInfoList());
        //若获取信息成功，返回success
        if(memberResult != null)
            return ResultTool.success(memberResult);
        else
            return ResultTool.error();
    }
}

