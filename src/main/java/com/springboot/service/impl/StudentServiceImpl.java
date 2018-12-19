package com.springboot.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;

import com.springboot.dynamicquery.DynamicQuery;
import com.springboot.model.AppStudent;
import com.springboot.model.Student;
import com.springboot.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	final static String startTime = sdf.format(new Date());
	
	@Autowired
	private DynamicQuery dynamicQuery;
	
	private NamedParameterJdbcTemplate nameParamerterJdbcTemplate;//批量插入

    @Override
    public List<Object[]> listStudent() {
        // TODO Auto-generated method stub
        String nativeSql = "SELECT s.id AS studentId,c.id AS classId,c.class_name AS className,c.teacher_name AS teacherName,s.name,s.age FROM app_student s,app_class c";
        List<Object[]> list = dynamicQuery.nativeQueryList(nativeSql);
        return list;
    }

    @Override
    public List<Student> listStudentModel() {
        // TODO Auto-generated method stub
        String nativeSql = "SELECT s.id AS studentId,c.id AS classId,c.class_name AS className,c.teacher_name AS teacherName,s.name,s.age FROM app_student s,app_class c";
        List<Student> list = dynamicQuery.nativeQuetyListModel(Student.class, nativeSql);
        return list;
    }

    @Override
    public List<Map<Object, Object>> listStudentMap() {
        // TODO Auto-generated method stub
        String nativeSql = "SELECT s.id AS studentId,c.id AS classId,c.class_name AS className,c.teacher_name AS teacherName,s.name,s.age FROM app_student s,app_class c";
        List<Map<Object, Object>> list = dynamicQuery.nativeQueryListMap(nativeSql);
        return list;
    }
    
    public void batchSave() {
        List<AppStudent> list = new ArrayList<AppStudent>();
        list.add(new AppStudent(1, "张三", 21));
        list.add(new AppStudent(2, "李四", 21));
        list.add(new AppStudent(2, "王五", 21));
        SqlParameterSource[] beanSources = SqlParameterSourceUtils.createBatch(list.toArray());
        String sql  = "INSERT INTO app_student(class_id,name,age) VALUES (:classId,:name,:age)";
        nameParamerterJdbcTemplate.batchUpdate(sql, beanSources);
    }
    
    public void statsDemo() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        Stats stats1 = new Stats("任务A", 1000, latch);
        Stats stats2 = new Stats("任务B", 2000, latch);
        Stats stats3 = new Stats("任务C", 2000, latch);
        Stats stats4 = new Stats("任务D", 2000, latch);
        Stats stats5 = new Stats("任务E", 2000, latch);

        stats1.start();//任务A开始执行
        stats2.start();//任务B开始执行
        stats3.start();//任务C开始执行
        stats4.start();//任务D开始执行
        stats5.start();//任务E开始执行
        latch.await();// 等待所有人任务结束
        
        System.out.println("所有的统计任务执行完成:" + sdf.format(new Date()));
    }
    
    public class Stats extends Thread{
        String statsName;
        int runTime;
        CountDownLatch latch;
        
        public Stats(String statsName, int runTime, CountDownLatch latch) {
            this.statsName = statsName;
            this.runTime = runTime;
            this.latch = latch;
        }
        
        public void run() {
            try {
                System.out.println(statsName + "do stats begin at " + startTime);
                Thread.sleep(runTime);
                System.out.println(statsName + "do stats completeat " + sdf.format(new Date()));
                latch.countDown();//单次任务结束，计数器减一
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    

}