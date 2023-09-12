package top.lldwb.noitaSaverServer.dao;

import org.apache.ibatis.session.SqlSession;
import top.lldwb.noitaSaver.Entity.MailVerificationCode;
import top.lldwb.noitaSaverServer.utils.MybatisUtils;

public class MailVerificationCodeDaoImpl implements MailVerificationCodeDao{
    @Override
    public MailVerificationCode getMailVerificationCodeByCodeMail(String code, String mail) {
        SqlSession session = MybatisUtils.getSqlSession(true);
        return session.getMapper(MailVerificationCodeDao.class).getMailVerificationCodeByCodeMail(code,mail);
    }

    @Override
    public void setMailVerificationCode(String mail, String code) {
        SqlSession session = MybatisUtils.getSqlSession(true);
        session.getMapper(MailVerificationCodeDao.class).setMailVerificationCode(mail, code);
    }
}
