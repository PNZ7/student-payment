package edu.javacourse.student.business;

import edu.javacourse.student.dao.*;
import edu.javacourse.student.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentOrderService {
    private static final Logger LOG = LoggerFactory.getLogger(StudentOrderService.class);

    @Autowired
    private StudentOrderRepository dao;
    @Autowired
    private StreetRepository daoStreet;
    @Autowired
    private StudentOrderStatusRepository daoStatus;
    @Autowired
    private PassportOfficeRepository daoPassport;
    @Autowired
    private RegisterOfficeRepository daoRegister;
    @Autowired
    private UniversityRepository daoUniversity;

    @Transactional
    public void testSave(){
        StudentOrder so = new StudentOrder();
        so.setStudentOrderDate(LocalDateTime.now());
        so.setStatus(daoStatus.getOne(1L));

        so.setHusband(buildPerson(false));
        so.setWife(buildPerson(true));

        so.setCertificateNumber("CERTIFICATE");
        so.setRegisterOffice(daoRegister.getOne(1L));
        so.setMarriageDate(LocalDate.now());
        
        dao.save(so);

    }
    @Transactional
    public void testGet(){
        List<StudentOrder> sos = dao.findAll();
        LOG.info(sos.get(0).getWife().getGivenName());
    }

    private Adult buildPerson(boolean wife){
        Adult p = new Adult();
        p.setDateOfBirth(LocalDate.now());
        Address a = new Address();
        a.setPostCode("190000");
        a.setBuilding("21");
        a.setExtension("8");
        Street one = daoStreet.getOne(1l);
        a.setStreet(one);
        a.setAppartment("199");

        p.setAddress(a);
        if (wife){
            p.setSurName("Рюрик");
            p.setGivenName("Марфа");
            p.setPatronymic("Васильевна");
            p.setPassportSeria("WIFE_S");
            p.setPassportNumber("WIFE_N");
            p.setPassportOffice(daoPassport.getOne(1L));
            p.setIssueDate(LocalDate.now());
            p.setStudentNumber("12345");
            p.setUniversity(daoUniversity.getOne(1L));

        }else {
            p.setSurName("Рюрик");
            p.setGivenName("Иван");
            p.setPatronymic("Васильевич");
            p.setPassportSeria("HUSB_S");
            p.setPassportNumber("HUSB_N");
            p.setPassportOffice(daoPassport.getOne(1L));
            p.setIssueDate(LocalDate.now());
            p.setStudentNumber("67890");
            p.setUniversity(daoUniversity.getOne(1L));
        }
        return p;
    }
}
