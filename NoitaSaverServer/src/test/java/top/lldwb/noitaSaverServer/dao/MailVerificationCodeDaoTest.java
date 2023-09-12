package top.lldwb.noitaSaverServer.dao;

import org.junit.jupiter.api.Test;
import top.lldwb.noitaSaver.Entity.MailVerificationCode;

class MailVerificationCodeDaoTest {

    @Test
    void getMailVerificationCodeByCodeMail() {
        MailVerificationCodeDao dao = new MailVerificationCodeDaoImpl();
        System.out.println(dao.getMailVerificationCodeByCodeMail("1432","12dgss"));
    }

    @Test
    void setMailVerificationCode() {
//        System.out.println(123);
        MailVerificationCode mailVerificationCode = new MailVerificationCode();
        mailVerificationCode.setMailVerificationCodeEmail("12dgss");
        mailVerificationCode.setMailVerificationCodeCode("1432");

        MailVerificationCodeDao dao = new MailVerificationCodeDaoImpl();
//        dao.setMailVerificationCode(mailVerificationCode);
    }
}