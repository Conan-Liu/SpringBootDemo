package com.conan.demo.service;

import com.conan.demo.model.CourseClickCount;
import com.conan.demo.utils.HbaseUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Conan on 2019/5/4.
 */
@Component
public class CourseClickCountDAO {

    /**
     * 根据天查询课程的 PV 量
     *
     * @param day
     * @return
     */
    public List<CourseClickCount> query(String day) throws IOException {
        List<CourseClickCount> list = new ArrayList<>();

        // 去hbase表中获取day的访问量
        Map<String, Long> map = HbaseUtils.getInstance().query("course_clickcount", day);
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            CourseClickCount model = new CourseClickCount();
            model.setName(entry.getKey());
            model.setValue(entry.getValue());
            list.add(model);
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        CourseClickCountDAO dao = new CourseClickCountDAO();
        List<CourseClickCount> list = dao.query("20190503");
        for (CourseClickCount model : list) {
            System.out.println(model.getName() + " : " + model.getValue());
        }
    }
}