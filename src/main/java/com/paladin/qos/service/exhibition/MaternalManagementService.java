package com.paladin.qos.service.exhibition;

import com.paladin.common.util.DateFormatUtil;
import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.dynamic.mapper.exhibition.MaternalManagementMapper;
import com.paladin.qos.dynamic.model.exhibition.MaternalCheckup;
import com.paladin.qos.service.exhibition.vo.DataDemonstrationVO;
import com.paladin.qos.service.exhibition.vo.MaternalManagementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <孕产妇管理>
 * @author Huangguochen
 * @create 2019/8/28 9:18
 */
@Service
public class MaternalManagementService extends BaseExhibitionDataAcquire{

    @Autowired
    private SqlSessionContainer sqlSessionContainer;

    private static SimpleDateFormat format = DateFormatUtil.getThreadSafeFormat("yyyy-MM");


    public MaternalManagementVO searchAllNumberData(AnalysisRequest request) {
        MaternalManagementMapper mapper = sqlSessionContainer.getMapper(MaternalManagementMapper.class);
        sqlSessionContainer.setCurrentDataSource("sqlserver");
        Date startTime = request.getStartTime();
        Date endTime = request.getEndTime();
        List<Date> defaultDateList = getSearchTimeList(startTime,endTime,true);
        MaternalManagementVO vo = new MaternalManagementVO();
        long prepregnancyCheckTotal = getPrepregnancyCheckTotal(startTime, endTime, mapper);
        List<DataDemonstrationVO> deathTotal = getMaternalDeathTotal(defaultDateList, mapper);
        List<DataDemonstrationVO> highriskMaternalTotal = getHighriskMaternalTotal(defaultDateList, mapper);
        List<DataDemonstrationVO> cervicalCancerScreeningTotal = getCervicalCancerScreeningTotal(defaultDateList, mapper);
        List<DataDemonstrationVO> breastCancerScreeningTotal = getBreastCancerScreeningTotal(defaultDateList, mapper);
        List<DataDemonstrationVO> syphilisTestTotal = getSyphilisTestTotal(defaultDateList, mapper);
        List<DataDemonstrationVO> syphilisInfectionTotal = getSyphilisInfectionTotal(defaultDateList, mapper);
        List<DataDemonstrationVO> hepatitisBtestTotal = getHepatitisBtestTotal(defaultDateList, mapper);
        List<DataDemonstrationVO> hepatitisBdeterminesInfectionTotal = getHepatitisBdeterminesInfectionTotal(defaultDateList, mapper);
        List<DataDemonstrationVO> postpartumVisitTotal = getPostpartumVisitTotal(defaultDateList, mapper);
        long numberOfFolates = getNumberOfFolates(startTime, endTime, mapper);
        long folicAcidDispensingBottle = getFolicAcidDispensingBottle(startTime, endTime, mapper);
        vo.setPrepregnancyCheck(prepregnancyCheckTotal);
        vo.setMaternalDeath(deathTotal);
        vo.setHighriskMaternal(highriskMaternalTotal);
        vo.setCervicalCancerScreening(cervicalCancerScreeningTotal);
        vo.setBreastCancerScreening(breastCancerScreeningTotal);
        vo.setSyphilisTest(syphilisTestTotal);
        vo.setSyphilisInfection(syphilisInfectionTotal);
        vo.setHepatitisBtest(hepatitisBtestTotal);
        vo.setHepatitisBdeterminesInfection(hepatitisBdeterminesInfectionTotal);
        vo.setPostpartumVisit(postpartumVisitTotal);
        vo.setNumberOfFolates(numberOfFolates);
        vo.setFolicAcidDispensingBottle(folicAcidDispensingBottle);
        return vo;
    }


    public MaternalManagementVO searchPremaritalCheckTotal(AnalysisRequest request) {
        MaternalManagementMapper mapper = sqlSessionContainer.getMapper(MaternalManagementMapper.class);
        sqlSessionContainer.setCurrentDataSource("sqlserver");
        Date startTime = request.getStartTime();
        Date endTime = request.getEndTime();
        MaternalManagementVO vo = new MaternalManagementVO();
        long malePremaritalCheckTotal = getMalePremaritalCheckTotal(startTime, endTime, mapper);
        long getFemalePremaritalCheckTotal = getFemalePremaritalCheckTotal(startTime, endTime, mapper);
        vo.setMalePremaritalCheck(malePremaritalCheckTotal);
        vo.setFemalePremaritalCheck(getFemalePremaritalCheckTotal);
        return vo;
    }


    /**
     * 功能描述: <男性婚前检查>
     * @param startTime
     * @param endTime
     * @return  int
     */
    public long getMalePremaritalCheckTotal(Date startTime, Date endTime, MaternalManagementMapper mapper) {
        return mapper.getMalePremaritalCheckTotal(checkStartTime(startTime), endTime);
    }

    /**
     * 功能描述: <女性婚前检查>
     * @param startTime
     * @param endTime
     * @return  int
     */
    public long getFemalePremaritalCheckTotal(Date startTime, Date endTime, MaternalManagementMapper mapper) {
        return mapper.getFemalePremaritalCheckTotal(checkStartTime(startTime), endTime);
    }

    /**
     * 功能描述: <孕前检查人次数>
     * @param startTime
     * @param endTime
     * @return  int
     */
    public long getPrepregnancyCheckTotal(Date startTime, Date endTime, MaternalManagementMapper mapper) {
        return  mapper.getPrepregnancyCheckTotal(checkStartTime(startTime), endTime);
    }

    /**
     * 功能描述: <叶酸发放人次数>
     * @param startTime
     * @param endTime
     * @return  int
     */
    public long getNumberOfFolates(Date startTime, Date endTime, MaternalManagementMapper mapper) {
        return  mapper.getNumberOfFolates(checkStartTime(startTime), endTime);
    }

    /**
     * 功能描述: <叶酸发放瓶数>
     * @param startTime
     * @param endTime
     * @return  int
     */
    public long getFolicAcidDispensingBottle(Date startTime, Date endTime, MaternalManagementMapper mapper) {
        return  mapper.getFolicAcidDispensingBottle(checkStartTime(startTime), endTime);
    }

    /**
     * 功能描述: <产妇筛查数>
     * @param dateList
     * @return  List<DataDemonstrationVO>
     */
    public List<DataDemonstrationVO> getMaternalScreeningTotal(Date startTime, Date endTime) {
        sqlSessionContainer.setCurrentDataSource("sqlserver");
        List<Date> dateList = getSearchTimeList(startTime,endTime,false);
        MaternalManagementMapper mapper = sqlSessionContainer.getMapper(MaternalManagementMapper.class);
        List<DataDemonstrationVO> list = new ArrayList<>(12);
        DataDemonstrationVO dataDemonstrationVO;
        for (Date date : dateList) {
            long total = mapper.getMaternalScreeningTotal(date);
            dataDemonstrationVO = new DataDemonstrationVO();
            dataDemonstrationVO.setSearchTime(format.format(date));
            dataDemonstrationVO.setTotal(total);
            list.add(dataDemonstrationVO);
        }
        return list;
    }


    /**
     * 功能描述: <妇女病筛查>
     * @param dateList
     * @return  List<DataDemonstrationVO>
     */
    public List<DataDemonstrationVO> getWomenDiseaseScreeningTotal(Date startTime, Date endTime) {
        sqlSessionContainer.setCurrentDataSource("sqlserver");
        List<Date> dateList = getSearchTimeList(startTime,endTime,false);
        MaternalManagementMapper mapper = sqlSessionContainer.getMapper(MaternalManagementMapper.class);
        List<DataDemonstrationVO> list = new ArrayList<>(12);
        DataDemonstrationVO dataDemonstrationVO;
        for (Date date : dateList) {
            long total = mapper.getWomenDiseaseScreeningTotal(date);
            dataDemonstrationVO = new DataDemonstrationVO();
            dataDemonstrationVO.setSearchTime(format.format(date));
            dataDemonstrationVO.setTotal(total);
            list.add(dataDemonstrationVO);
        }
        return list;
    }

    /**
     * 功能描述: <高危孕产妇人数>
     * @param defaultDateList
     * @return  List<DataDemonstrationVO>
     */
    public List<DataDemonstrationVO> getHighriskMaternalTotal(List<Date> defaultDateList, MaternalManagementMapper mapper) {
        List<DataDemonstrationVO> list = new ArrayList<>(12);
        DataDemonstrationVO dataDemonstrationVO;
        for (Date date : defaultDateList) {
            long total = mapper.getHighriskMaternalTotal(date);
            dataDemonstrationVO = new DataDemonstrationVO();
            dataDemonstrationVO.setTotal(total);
            list.add(dataDemonstrationVO);
        }
        return list;
    }

    /**
     * 功能描述: <孕产妇死亡人数>
     * @param defaultDateList
     * @return  List<DataDemonstrationVO>
     */
    public List<DataDemonstrationVO> getMaternalDeathTotal(List<Date> defaultDateList, MaternalManagementMapper mapper) {
        List<DataDemonstrationVO> list = new ArrayList<>(12);
        DataDemonstrationVO dataDemonstrationVO;
        for (Date date : defaultDateList) {
            long total = mapper.getMaternalDeathTotal(date);
            dataDemonstrationVO = new DataDemonstrationVO();
            dataDemonstrationVO.setTotal(total);
            list.add(dataDemonstrationVO);
        }
        return list;
    }

    /**
     * 功能描述: <两癌筛查-宫颈癌筛查>
     * @param defaultDateList
     * @return  List<DataDemonstrationVO>
     */
    public List<DataDemonstrationVO> getCervicalCancerScreeningTotal(List<Date> defaultDateList, MaternalManagementMapper mapper) {
        List<DataDemonstrationVO> list = new ArrayList<>(12);
        DataDemonstrationVO dataDemonstrationVO;
        for (Date date : defaultDateList) {
            long total = mapper.getCervicalCancerScreeningTotal(date);
            dataDemonstrationVO = new DataDemonstrationVO();
            dataDemonstrationVO.setTotal(total);
            list.add(dataDemonstrationVO);
        }

        return list;
    }
    /**
     * 功能描述: <两癌筛查-乳腺癌筛查>
     * @param defaultDateList
     * @return  List<DataDemonstrationVO>
     */
    public List<DataDemonstrationVO> getBreastCancerScreeningTotal(List<Date> defaultDateList, MaternalManagementMapper mapper) {
        List<DataDemonstrationVO> list = new ArrayList<>(12);
        DataDemonstrationVO dataDemonstrationVO;
        for (Date date : defaultDateList) {
            long total = mapper.getBreastCancerScreeningTotal(date);
            dataDemonstrationVO = new DataDemonstrationVO();
            dataDemonstrationVO.setTotal(total);
            list.add(dataDemonstrationVO);
        }

        return list;
    }

    /**
     * 功能描述: <母婴阻断-梅毒检测人数>
     * @param defaultDateList
     * @return  List<DataDemonstrationVO>
     */
    public List<DataDemonstrationVO> getSyphilisTestTotal(List<Date> defaultDateList, MaternalManagementMapper mapper) {
        List<DataDemonstrationVO> list = new ArrayList<>(12);
        DataDemonstrationVO dataDemonstrationVO;
        for (Date date : defaultDateList) {
            long total = mapper.getSyphilisTestTotal(date);
            dataDemonstrationVO = new DataDemonstrationVO();
            dataDemonstrationVO.setTotal(total);
            list.add(dataDemonstrationVO);
        }

        return list;
    }

    /**
     * 功能描述: <母婴阻断-梅毒感染人数>
     * @param defaultDateList
     * @return  List<DataDemonstrationVO>
     */
    public List<DataDemonstrationVO> getSyphilisInfectionTotal(List<Date> defaultDateList, MaternalManagementMapper mapper) {
        List<DataDemonstrationVO> list = new ArrayList<>(12);
        DataDemonstrationVO dataDemonstrationVO;
        for (Date date : defaultDateList) {
            long total = mapper.getSyphilisInfectionTotal(date);
            dataDemonstrationVO = new DataDemonstrationVO();
            dataDemonstrationVO.setTotal(total);
            list.add(dataDemonstrationVO);
        }

        return list;
    }


    /**
     * 功能描述: <母婴阻断-乙肝检测人数>
     * @param defaultDateList
     * @return  List<DataDemonstrationVO>
     */
    public List<DataDemonstrationVO> getHepatitisBtestTotal(List<Date> defaultDateList, MaternalManagementMapper mapper) {
        List<DataDemonstrationVO> list = new ArrayList<>(12);
        DataDemonstrationVO dataDemonstrationVO;
        for (Date date : defaultDateList) {
            long total = mapper.getHepatitisBtestTotal(date);
            dataDemonstrationVO = new DataDemonstrationVO();
            dataDemonstrationVO.setTotal(total);
            list.add(dataDemonstrationVO);
        }

        return list;
    }

    /**
     * 功能描述: <母婴阻断-乙肝确定感染人次数>
     * @param defaultDateList
     * @return  List<DataDemonstrationVO>
     */
    public List<DataDemonstrationVO> getHepatitisBdeterminesInfectionTotal(List<Date> defaultDateList, MaternalManagementMapper mapper) {
        List<DataDemonstrationVO> list = new ArrayList<>(12);
        DataDemonstrationVO dataDemonstrationVO;
        for (Date date : defaultDateList) {
            long total = mapper.getHepatitisBdeterminesInfectionTotal(date);
            dataDemonstrationVO = new DataDemonstrationVO();
            dataDemonstrationVO.setTotal(total);
            list.add(dataDemonstrationVO);
        }

        return list;
    }



    /**
     * 功能描述: <产后访视>
     * @param defaultDateList
     * @return  List<DataDemonstrationVO>
     */
    public List<DataDemonstrationVO> getPostpartumVisitTotal(List<Date> defaultDateList, MaternalManagementMapper mapper) {
        List<DataDemonstrationVO> list = new ArrayList<>(12);
        DataDemonstrationVO dataDemonstrationVO;
        for (Date date : defaultDateList) {
            long total = mapper.getPostpartumVisitTotal(date);
            dataDemonstrationVO = new DataDemonstrationVO();
            dataDemonstrationVO.setTotal(total);
            list.add(dataDemonstrationVO);
        }

        return list;
    }

    /**
     * 功能描述: <孕产妇体检>
     * @param defaultDateList
     * @return  List<DataDemonstrationVO>
     */
    public List<MaternalCheckup> getMaternalCheckupData(Date startDate, Date endDate) {
        sqlSessionContainer.setCurrentDataSource("sqlserver");
        MaternalManagementMapper mapper = sqlSessionContainer.getMapper(MaternalManagementMapper.class);
        return mapper.getMaternalCheckupData(checkStartTime(startDate),endDate);
    }


}
